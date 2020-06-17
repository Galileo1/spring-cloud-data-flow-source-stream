package weird.io;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ConfigurationReader configurationReader;

    @Test
    public void contextLoads() {
    }

    @Test
    public void serealizeToJson() throws Exception {

        final QuotesDto quotesDto = new QuotesDto();
        quotesDto.setCurrentPrice(352.08);
        quotesDto.setHighPrice(353.2);
        quotesDto.setLowPrice(344.72);
        quotesDto.setPreviousClose(342.99);
        quotesDto.setOpeningPrice(351.46);
        quotesDto.setTimestamp(1592376293);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/response.json"), QuotesDto.class));

        assertThat(MAPPER.writeValueAsString(quotesDto)).isEqualTo(expected);

    }

    @Test
    public void configurationFileISLoaded() throws Exception {

        assertThat(configurationReader.getBaseUrl()).isEqualTo("https://finnhub.io/");
        assertThat(configurationReader.getApiVersion()).isEqualTo("api/v1/");
        assertThat(configurationReader.getQuotes()).isEqualTo("quote");
    }


}
