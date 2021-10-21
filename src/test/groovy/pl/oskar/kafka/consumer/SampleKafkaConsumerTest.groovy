package pl.oskar.kafka.consumer

import org.spockframework.spring.SpringBean
import pl.oskar.kafka.AbstractKafkaTest
import spock.util.concurrent.PollingConditions

import java.util.concurrent.atomic.AtomicBoolean

class SampleKafkaConsumerTest extends AbstractKafkaTest {

    private AtomicBoolean invoked = new AtomicBoolean(false)

    @SpringBean
    private SampleService sampleService = new SampleService() {
        @Override
        void start() {
            invoked.set(true)
        }
    }

    def "test"() {
        when:
        kafkaTestProducer.send()

        then:
        new PollingConditions(timeout: 15, initialDelay: 2, factor: 1.25).eventually {
            assert invoked.get()
        }
    }
}
