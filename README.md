## Update
The issue described below is now resolved by the latest commit, which does two things:
1. Upgrade to latest snapshot release of Spring Cloud Stream.
1. Sets the application ID on each of the bindings, so that each `@StreamListener` method has a unique ID.

For more details see [here](https://github.com/spring-cloud/spring-cloud-stream/issues/1508).

---

## Overview
This repository tries to demonstrate a problem with Spring Cloud Stream and Kafka. Follow the instructions below to set
up the system, then run the Spring Boot app. It manages to run, but after a few seconds an exception like the following
is shown in the console:
```
Exception in thread "spring-cloud-stream-kafka-12427e22-82cc-4368-b07c-09573ada6670-StreamThread-2" java.lang.IllegalArgumentException: Assigned partition t3-0 for non-subscribed topic regex pattern; subscription pattern is t1|t2
	at org.apache.kafka.clients.consumer.internals.SubscriptionState.assignFromSubscribed(SubscriptionState.java:195)
	at org.apache.kafka.clients.consumer.internals.ConsumerCoordinator.onJoinComplete(ConsumerCoordinator.java:225)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator.joinGroupIfNeeded(AbstractCoordinator.java:367)
	at org.apache.kafka.clients.consumer.internals.AbstractCoordinator.ensureActiveGroup(AbstractCoordinator.java:316)
	at org.apache.kafka.clients.consumer.internals.ConsumerCoordinator.poll(ConsumerCoordinator.java:295)
	at org.apache.kafka.clients.consumer.KafkaConsumer.pollOnce(KafkaConsumer.java:1146)
	at org.apache.kafka.clients.consumer.KafkaConsumer.poll(KafkaConsumer.java:1111)
	at org.apache.kafka.streams.processor.internals.StreamThread.pollRequests(StreamThread.java:848)
	at org.apache.kafka.streams.processor.internals.StreamThread.runOnce(StreamThread.java:805)
	at org.apache.kafka.streams.processor.internals.StreamThread.runLoop(StreamThread.java:771)
	at org.apache.kafka.streams.processor.internals.StreamThread.run(StreamThread.java:741)
```
The exact details of the error can vary: sometimes it's assigning partition `t3-0` with a subscription pattern of `t1|t2`;
sometimes it's assigning partition `t1-0` with a subscription pattern of `t3`.

Also, the error occasionally does not occur: a restart of the Spring Boot app might be required.

### Setup Instructions
Run the following command from the folder in which this readme resides:

```
> cd kafka
> ./setup.sh
```

Note: you might need to manually make "setup.sh" executable.

You can re-run the above whenever required to reset the system.