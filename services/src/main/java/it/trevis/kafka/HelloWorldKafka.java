package it.trevis.kafka;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldKafka {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private final static String TOPIC = "test";
	private final static String BOOTSTRAP_SERVERS = "localhost:9092";
	// "localhost:9092,localhost:9093,localhost:9094";

	private static Consumer<Long, String> createConsumer() {
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
		
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 305000);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 180000);
		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 60000);
		props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, 50000);
		props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 2000);
		
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		// Create the consumer using props.
		final Consumer<Long, String> consumer = new KafkaConsumer<>(props);

		// Subscribe to the topic.
		consumer.subscribe(Collections.singletonList(TOPIC));
		return consumer;
	}

	static void runConsumer() throws InterruptedException {
		final Consumer<Long, String> consumer = createConsumer();

//		final int giveUp = 100;
//		int noRecordsCount = 0;
		try {
		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
//			if (consumerRecords.count() == 0) {
//				noRecordsCount++;
//				if (noRecordsCount > giveUp)
//					break;
//				else
//					continue;
//			}
			System.out.println("Totale record: " + consumerRecords.count());
			consumerRecords.forEach(record -> {
//				System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
//				System.out.println("RECORD: " + record.toString());
				logger.info("Consumer Record:(%d, %s, %d, %d)", record.key(), record.value(),
						record.partition(), record.offset());
			});
			System.out.println("Fine");
			consumer.commitAsync();
		}
		} finally {
			consumer.close();
			logger.info("DONE");
		}
	}
	
	public static void main(String[] args) {
		logger.info("start consumer...");
		try {
			runConsumer();
		} catch (InterruptedException e) {
			logger.error("errore nel runConsumer..", e);
		}
		logger.info("end consumer...");
	}
	
}
