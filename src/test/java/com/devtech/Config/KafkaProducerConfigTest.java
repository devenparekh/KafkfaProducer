package com.devtech.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaProducerConfigTest {

    private KafkaProducerConfig kafkaProducerConfigUnderTest;

    @BeforeEach
    void setUp() {
        kafkaProducerConfigUnderTest = new KafkaProducerConfig();
        ReflectionTestUtils.setField(kafkaProducerConfigUnderTest, "topic", "name");
    }

    @Test
    void testCreateTopic() {
        // Setup
        final NewTopic expectedResult = new NewTopic("name", 3, (short) 1);

        // Run the test
        final NewTopic result = kafkaProducerConfigUnderTest.createTopic();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
