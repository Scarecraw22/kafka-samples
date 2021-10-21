package pl.oskar.kafka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import pl.oskar.kafka.producer.SampleKafkaTestProducer
import spock.lang.Specification

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@EmbeddedKafka(partitions = 1, brokerProperties = [ "listeners=PLAINTEXT://localhost:9092", "port=9092" ])
abstract class AbstractKafkaTest extends Specification {

    @Autowired
    protected SampleKafkaTestProducer kafkaTestProducer

    def setup() {
        // Sleep for 1s to wait for consumer to join
        sleep(1000)
    }
}
