# Kafka Bulk Publisher

### Description:
* A kafka bulk publisher capable of publishing upto `10000000` messages in 2 mins
* Could be used for load testing of a consumer service
   
### Exports
##### The publisher takes some configurable args as env vars which are as follows
* `TOPIC_PRODUCE` `//Topic in which message shall be produced`
* `BOOTSTRAP_SERVER` `//Kafka endpoint its optional if kafka is reachable on  localhost:9092`
* `NUM_RECORDS` `// Number of records shall be a number between 10000 and 10000000`
* `MESSAGE` `A string message you want to publish you could leave if you just want to see it in action`
#####
```shell script
export TOPIC_PRODUCE="Test_Topic"
export BOOTSTRAP_SERVER="localhost:9092"
export NUM_RECORDS="10000000"
export MESSAGE='{"userId" : "4c64ecac-921f-48f6-a4ba-2b27a82141a3", "department" : "finance", "salary" : 1000000}'
```
### Running it
Its little tedious setting up the kafka, so I got you guys covered you could pull `shuhamknoldus/kafka-container:latest` from [docker hub](https://hub.docker.com/r/shuhamknoldus) (a docker installation is necessary) a custom kafka container which setup a single node kafka cluster(could also be used in integration tests), once pulling it you could simply run

```shell script
docker run --name kafka -d shuhamknoldus/kafka-container:latest
```

After that you could get the value for bootstrap server for your consumers and producers like this

```shell script
echo $(docker exec kafka hostname -i)
```
And there you go that's how you go about it, simply run 
`sbt assembly` to get the JAR at your disposal and try to test it against your consumers

Feels free to create any issues/suggestions/feature request
 
