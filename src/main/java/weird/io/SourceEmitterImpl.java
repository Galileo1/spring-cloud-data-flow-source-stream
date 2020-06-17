package weird.io;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.reactivestreams.Publisher;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/**
 * Stream emitter class put data onto output stream.
 */
@Slf4j
@Service
@AllArgsConstructor
@EnableConfigurationProperties(SourceProperties.class)
@EnableBinding(Source.class)
public class SourceEmitterImpl implements SourceEmitter {

    private final HttpRequest httpRequest;

    private final ConfigurationReader configurationReader;

    private final Mapper mapper;

    private SourceProperties sourceProperties;

    /**
     * Method simulating the event emitter and polls from the exchange api
     * Represent an Integration Flow as a Reactive Streams Publisher bean.
     *
     * @return the Reactive Streams {@link Publisher}
     */
    @Override
    @StreamEmitter
    @Output(Source.OUTPUT)
    @Bean
    public Publisher<Message<List<QuotesDto>>> publisher() {
        return IntegrationFlows.from(() -> {
            try {
                String endpoint = configurationReader.getBaseUrl() + configurationReader.getApiVersion()
                        + configurationReader.getQuotes() + "?symbol=" + sourceProperties.getSymbol() + "&token="
                        + sourceProperties.getTokens();

                final StringBuilder responseStrBuilder = httpRequest.sendRequest(endpoint);

                System.out.println("Response : " + responseStrBuilder.toString());

                final QuotesDto quotesDto = mapper.stringToJsonObject(responseStrBuilder.toString(), QuotesDto.class);

                return new GenericMessage<>(quotesDto);

            } catch (Throwable e) {

                log.error("Error", e);
                return null;
            }
        }, e -> e.poller(p -> p.fixedDelay(this.sourceProperties.getPollInterval(), TimeUnit.SECONDS)))
                .toReactivePublisher();
    }

}

