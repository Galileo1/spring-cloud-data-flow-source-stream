package weird.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

/**
 * Class for reactive stream outbound channel.
 * <p>
 * EnableBindings annotation takes one of the interface classes and defines a destination binding.
 * These destination binding are typically message channels for binders such as RabbitMQ,Kafka eec.
 * </p>
 * Source interface defines the contract for the message producer by providing the destination to which the
 * produced message is sent.
 * @author varun gaur
 *
 */
@Slf4j
@EnableConfigurationProperties(SourceProperties.class)
@EnableBinding(Source.class)
@SpringBootApplication
public class App {

    /**
     * Main method to initiate app execution.
     * @param args cli parameters if any
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private SourceProperties sourceProperties;

    /**
     * Method simulating the event emitter and polls from the exchange api
     * Represent an Integration Flow as a Reactive Streams Publisher bean.
     *
     * @return the Reactive Streams {@link Publisher}
     */
    @StreamEmitter
    @Output(Source.OUTPUT)
    @Bean
    public Publisher<Message<List<RatesDto>>> simulateEventEmitter() {
        return IntegrationFlows.from(() -> {
            try {

                final String exchangeRateApi = "https://api.exchangeratesapi.io/history?start_at="
                        + sourceProperties.getStartDate() + "&end_at=" + sourceProperties.getEndDate();
                System.out.println("Url : " + exchangeRateApi);
                final URL url = new URL(exchangeRateApi);

                final BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(
                                url.openStream(), "UTF-8"));

                final StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }

                System.out.println("Response : " + responseStrBuilder.toString());
                final ObjectMapper objectMapper = new ObjectMapper();
                final RatesDto ratesDto = objectMapper.readValue(responseStrBuilder.toString(), RatesDto.class);

                return new GenericMessage<>(ratesDto);
            } catch (Throwable e) {
                log.error("Error", e);
                return null;
            }
        }, e -> e.poller(p -> p.fixedDelay(this.sourceProperties.getTriggerWithDelay(), TimeUnit.SECONDS)))
                .toReactivePublisher();
    }
}
