echo === Shutting down ===
docker-compose down
echo

echo === Starting up ===
docker-compose up -d
echo

echo === Waiting a bit ===
# Slight hack: just wait a bit to give Kafka a chance to wake up
sleep 5
echo

echo === Creating topics ===
docker-compose exec kafka /usr/bin/kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t1
docker-compose exec kafka /usr/bin/kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t2
docker-compose exec kafka /usr/bin/kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic t3
echo

echo === Ready ===