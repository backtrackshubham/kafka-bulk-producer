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
}

