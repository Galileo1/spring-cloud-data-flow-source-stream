package weird.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * Http request sender service class.
 *
 * @author Varun Gaur
 */
@Slf4j
@Service
@AllArgsConstructor
public class HttpRequestImpl implements HttpRequest {

    @Override
    public StringBuilder sendRequest(final String endpoint) {

        try {
            final URL url = new URL(endpoint);
            log.info("Url:: " + url);
            final BufferedReader streamReader = new BufferedReader(
                    new InputStreamReader(
                            url.openStream(), "UTF-8"));

            final StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;

            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            return responseStrBuilder;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
