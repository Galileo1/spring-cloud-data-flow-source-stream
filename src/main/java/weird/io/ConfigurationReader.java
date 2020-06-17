package weird.io;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Reads the configuration from application properties file.
 *
 * @author Varun Gaur
 */
@Configuration
@Data
class ConfigurationReader {

    @Value("${properties.baseurl}")
    String baseUrl;

    @Value("${properties.resources.version}")
    String apiVersion;

    @Value("${properties.resources.quotes}")
    String quotes;

}
