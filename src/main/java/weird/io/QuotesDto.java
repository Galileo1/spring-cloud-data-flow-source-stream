package weird.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data transfer object/Serializer for quotes.
 *
 * @author Varun Gaur
 */
@Data
class QuotesDto {

    @JsonProperty("o")
    private double openingPrice;
    @JsonProperty("h")
    private double highPrice;
    @JsonProperty("l")
    private double lowPrice;
    @JsonProperty("c")
    private double currentPrice;
    @JsonProperty("pc")
    private double previousClose;
    @JsonProperty("t")
    private int timestamp;
}

