package weird.io;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void contextLoads() {
    }

    @Test
    public void serealizeToJson() throws Exception {

        final Map<String, Double> currencies = new HashMap<>();
        currencies.put("CAD", 1.5187);
        currencies.put("HKD", 8.753);

        final Map<String, Map<String, Double>> exchangeRates = new HashMap<String, Map<String, Double>>();
        exchangeRates.put("2020-06-09", currencies);

        final RatesDto ratesDto = new RatesDto();
        ratesDto.setBase("EUR");
        ratesDto.setStartAt("2020-06-09");
        ratesDto.setEndAt("2020-06-10");
        ratesDto.setRates(exchangeRates);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/response.json"), RatesDto.class));

        assertThat(MAPPER.writeValueAsString(ratesDto)).isEqualTo(expected);

    }

}
