package pl.oskar.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.oskar.utils.SampleKafkaUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SampleKafkaTestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send() throws ExecutionException, InterruptedException {
        List<Header> headers = Arrays.asList(
                new RecordHeader("header", "value".getBytes())
        );
        ProducerRecord<String, String> record = new ProducerRecord<>(SampleKafkaUtils.SAMPLE_TOPIC, null, "key", "value", headers);
        kafkaTemplate.send(record);
        log.info("Msg: {} sent", record);
    }
}
