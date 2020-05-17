package edu.knoldus.kafka

import java.util.Properties
import java.util.concurrent.Executors

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService, Future}

object FutureUtils {
  implicit val ec: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(ConfigConstant.ecThreads))

  def shutdown = ec.shutdown()
}

object Producer extends App {

  println("\n\n")
  println(s"Publishing for topic ${ConfigConstant.topicProduce}\n")
  println(s"Publishing for bootstrap server ${ConfigConstant.kafkaBootStrapServer}\n")
  println(s"Publishing message \n\n${ConfigConstant.message}\n")
  println(s"Publishing number of records ${ConfigConstant.numberOfRecords}\n")
  println("\n\n")


  private val props = new Properties()

  import FutureUtils._
  val recPerThread: Int = ConfigConstant.numberOfRecords / ConfigConstant.ecThreads
  props.put("bootstrap.servers", ConfigConstant.kafkaBootStrapServer)
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  private val producer = new KafkaProducer[String, String](props)
  val json = ConfigConstant.message
  val proc = () => Future {
    (1 to recPerThread).foreach { _ =>
      val uuid = java.util.UUID.randomUUID().toString
      producer.send(new ProducerRecord(ConfigConstant.topicProduce, uuid, json)).get()
    }
  }
  val res = Future.sequence((1 to ConfigConstant.ecThreads).toList.map(_ => proc()))

  Await.ready(res, Duration.Inf)
  shutdown
}
