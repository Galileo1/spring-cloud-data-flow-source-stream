package weird.io;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class defining the properties for the source.
 * The override for these properties will be provided during the
 * stream deployment process.
 *
 * @author Varun Gaur
 *
 */
@ConfigurationProperties("exchange")
@Data
public class SourceProperties {

    /**
     * Company listed in the stock exchange Symbol.
     * To get the list of all company https://finnhub.io/api/v1/stock/symbol?exchange=US&token
     */
    private String symbol = "FB";

    /**
     * Poll interval.
     */
    private int pollInterval = 1;

    /**
     * Http tokens for if necessary.
     */
    private String tokens = "brkrm9frh5r8d4o965rg";

}
