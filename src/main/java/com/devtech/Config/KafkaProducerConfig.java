package com.devtech.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

	@Value("${producer.topic}")
	private String topic;
	
	@Bean
	public NewTopic createTopic() {
		return new NewTopic(topic, 3, (short) 1);
		
	}

}
