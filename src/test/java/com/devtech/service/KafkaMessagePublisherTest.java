package com.devtech.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaMessagePublisherTest {

    @Mock
    private KafkaTemplate<String, Object> mockKafkaTemplate;

    @InjectMocks
    private KafkaMessagePublisher kafkaMessagePublisherUnderTest;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(kafkaMessagePublisherUnderTest, "topic", "topic");
    }

    @Test
    void testSendMessageToTopic() {
        // Setup
        // Configure KafkaTemplate.send(...).
        final CompletableFuture<SendResult<String, Object>> sendResultCompletableFuture = CompletableFuture.completedFuture(
                new SendResult<>(new ProducerRecord<>("topic", "key", "value"),
                        new RecordMetadata(new TopicPartition("topic", 0), 0L, 0, 0L, 0, 0)));
        when(mockKafkaTemplate.send("topic", "message")).thenReturn(sendResultCompletableFuture);

        // Run the test
        kafkaMessagePublisherUnderTest.sendMessageToTopic("message");

        // Verify the results
    }

    @Test
    void testSendMessageToTopic_KafkaTemplateReturnsFailure() {
        // Setup
        // Configure KafkaTemplate.send(...).
        final CompletableFuture<SendResult<String, Object>> sendResultCompletableFuture = CompletableFuture.failedFuture(
                new Exception("message"));
        when(mockKafkaTemplate.send("topic", "message")).thenReturn(sendResultCompletableFuture);

        // Run the test
        kafkaMessagePublisherUnderTest.sendMessageToTopic("message");

        // Verify the results
    }
}
