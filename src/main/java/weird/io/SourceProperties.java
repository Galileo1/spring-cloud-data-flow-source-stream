package weird.io;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Class defining the properties for the source.
 * The override for these properties will be provided during the
 * stream deployment process.
 *
 * @author Varun Gaur
 *
 */
@ConfigurationProperties("exchange")
@Validated
@Data
public class SourceProperties {

    /**
     * Start date for the historic exchange data.
     * Note: ("Exchange API shows data no earlier than 1999-01-01 ")
     */
    private String startDate = "2019-12-31";

    /**
     * End date for the historic exchange data.
     * Note: ("Exchange API shows data no later than current date ")
     */
    private String endDate = "2020-06-07";

    /**
     * Delay the trigger to let application boot.
     */
    private long triggerWithDelay = 150L;

}
