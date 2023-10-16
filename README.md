# KafkfaProducer
Kafka Producer Project


Steps to setup zookeeper:
1)Copy and Rename “zoo_sample.cfg” to “zoo.cfg” in C:\your workdir\zookeeper-3.4.9\conf
2)Create data directory in zookeeper folder.
3)Find & edit dataDir=/tmp/zookeeper to C:\\your workdir\\zookeeper\\zookeeper-3.4.9\\data in your zookeeper.properties file under kafka folder.
4)Add entries in System Environment Variables.Add in System Variables ZOOKEEPER_HOME = C:\your Workdir\zookeeper-3.4.9
5)Edit System Variable named “Path” and append this in the last ;%ZOOKEEPER_HOME%\bin;
6)Open command prompt and type "zkserver".

zkserver will start the zookeeper on the defualt port which is 2181, you can change the default port in zoo.cfg file
------------------------------------------------------------------------------------------------------------------------------------------------------
Steps to setup Kafka Server:
1) Extract kafka in a folder without spaces or you will get following error when running the .bat file for server: Error: Could not find or load main class files\kafka_2.12-2.4.0\libs\activation-1.1.1.jar;

2)Go to config folder in Apache Kafka and edit “server.properties”
3)Find log.dirs and replace after “=/tmp/kafka-logs” to “=C:\\your workdir\\kafka_2.10–0.10.1.1\\kafka-logs” (change your version number).
4)Leave other setting as is. If your Apache Zookeeper on different server then change the “zookeeper.connect” property.
5) RUN kafka server in other cmd window using .\bin\windows\kafka-server-start.bat .\config\server.properties
6)Create a topic using "./kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4
" as Newer versions(2.2+) of Kafka no longer requires ZooKeeper connection string. 
7)you will get a message "Created topic test-topic." on successful topic creation.
-------------------------------------------------------------------------------------------------------------------------------------------------------
Using Consumer and producers:
For Producer:
1) go to windows folder under bin directory, run cmd there and put in "kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic".
2) this should give you a ">" sign which allows you to produce messages.

For Consumer:
1) Open a new CMD from windows under bin directory, run "kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning"
2) If nothing appears on next line, it means your channel is now open. you can write something in producer channel and you will see it in consumer channel.


###############################
START KAFKA ZOOKEEPER : zkserver( in cmd)
START KAFKA SERVER : .\bin\windows\kafka-server-start.bat .\config\server.properties
###############################
