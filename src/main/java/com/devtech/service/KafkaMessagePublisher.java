package com.devtech.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher {
	
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
	
	@Value("${producer.topic}")
	private String topic;
	
	public void sendMessageToTopic(String message) {
	CompletableFuture<SendResult<String, Object>> future=	kafkaTemplate.send(topic, message);
		future.whenComplete((result,ex)->{
			if(ex==null) {
				System.out.println("Send Message=[" +message+"] with offset=[" + result.getRecordMetadata().offset()+"]");
			}
			else {
				System.out.println("Unable to Send Message=["+message+"] due to :" + ex.getMessage());
			}
		});
	}

}
