val kafka          = "org.apache.kafka" % "kafka-clients" % "2.3.0"
val typeSafeConfig = "com.typesafe"     % "config"        % "1.3.4"


libraryDependencies ++= Seq(
  kafka,
  typeSafeConfig
)

lazy val root = (project in file(".")).
  settings(
    name := "kafka-bulk-producer",
    version := "0.1",
    scalaVersion := "2.12.8",
    mainClass in (Compile, run) := Some("edu.knoldus.kafka.Producer")
  )
