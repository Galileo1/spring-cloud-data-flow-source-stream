package weird.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

/**
 * Data transfer object/Serializer for rates.
 *
 * @author Varun Gaur
 */
@Data
class RatesDto {
    private Map<String, Map<String, Double>> rates;
    @JsonProperty("c")
    private String startAt;
    @JsonProperty("end_at")
    private String endAt;
    private String base;
}

