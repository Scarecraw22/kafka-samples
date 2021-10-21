package pl.oskar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SampleKafkaApp {

    public static void main(String[] args) {
        SpringApplication.run(SampleKafkaApp.class, args);
    }
}
