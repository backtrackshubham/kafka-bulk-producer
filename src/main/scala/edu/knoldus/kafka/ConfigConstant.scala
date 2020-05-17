package edu.knoldus.kafka

object ConfigConstant {
  val ecThreads = 500
  val kafkaBootStrapServer: String = ConfigProvider.getEnvString("kafka-config.bootstrap-server")
  val topicProduce: String = ConfigProvider.getEnvString("kafka-config.topics.produce")
  val numberOfRecords: Int = ConfigProvider.getEnvInt("num-records")
  val message: String = ConfigProvider.getEnvString("message")
  if (numberOfRecords > 10000000 || numberOfRecords < 10000) {
    println("\n\n")
    println("Max number of records shall not be greater than 10000000")
    println("Min number of records shall not be smaller than 10000")
    println("\n\n")
    sys.exit(0)
  }

  if(message == ""){
    println("\n\n")
    println("Please specify a message to be produced using \n export MESSAGE='{\"userId\" : \"4c64ecac-921f-48f6-a4ba-2b27a82141a3\", \"department\" : \"finance\", \"salary\" : 1000000}'\n")
    println("\n\n")
    sys.exit(0)
  }

  if(topicProduce == ""){
    println("\n\n")
    println("Please provide a kafka topic name using \n export TOPIC_PRODUCE=\"TopicName\"\n")
    println("\n\n")
    sys.exit(0)
  }
}

