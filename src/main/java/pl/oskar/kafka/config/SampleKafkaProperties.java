package pl.oskar.kafka.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "pl.oskar.kafka")
public class SampleKafkaProperties {

    private String bootstrapServers;
    private String securityProtocol;
    private String saslMechanism;
    private String saslJaasConfig;
}
