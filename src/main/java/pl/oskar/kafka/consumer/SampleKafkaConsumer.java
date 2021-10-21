package pl.oskar.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pl.oskar.utils.SampleKafkaUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class SampleKafkaConsumer {

    private final SampleService sampleService;

    @KafkaListener(
            groupId = SampleKafkaUtils.SAMPLE_GROUP,
            topics = SampleKafkaUtils.SAMPLE_TOPIC,
            containerFactory = SampleKafkaUtils.CONTAINER_FACTORY_BEAN_NAME,
            concurrency = "1")
    public void listen(@Payload String payload, @Headers MessageHeaders messageHeaders) {
        log.info("Received payload: {}", payload);
        sampleService.start();
    }
}
