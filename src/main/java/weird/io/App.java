package weird.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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
@ComponentScan(basePackages = "weird.io")
@SpringBootApplication
public class App {

    /**
     * Main method to initiate app execution.
     * @param args cli parameters if any
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(App.class, args);
        SourceEmitterImpl service = applicationContext.getBean(SourceEmitterImpl.class);
        service.publisher();
    }
}
