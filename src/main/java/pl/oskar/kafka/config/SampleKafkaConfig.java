package pl.oskar.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
@EnableConfigurationProperties
public class SampleKafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(SampleKafkaProperties kafkaProperties) {
        return new DefaultKafkaProducerFactory<>(producerProperties(kafkaProperties));
    }

    @Bean
    public DefaultKafkaConsumerFactory<String, String> consumerFactory(SampleKafkaProperties kafkaProperties) {
        return new DefaultKafkaConsumerFactory<>(consumerProperties(kafkaProperties));
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    private Map<String, Object> producerProperties(SampleKafkaProperties kafkaProperties) {
        final Map<String, Object> props = connectionProperties(kafkaProperties);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        log.info("Producer config: {}", props);
        return props;
    }

    private Map<String, Object> consumerProperties(SampleKafkaProperties kafkaProperties) {
        final Map<String, Object> props = connectionProperties(kafkaProperties);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        log.info("Consumer config: {}", props);

        return props;
    }

    private Map<String, Object> connectionProperties(SampleKafkaProperties kafkaProperties) {
        final Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put("security.protocol", kafkaProperties.getSecurityProtocol());
        props.put("sasl.mechanism", kafkaProperties.getSaslMechanism());
        props.put("sasl.jaas.config", kafkaProperties.getSaslJaasConfig());

        return props;
    }
}
