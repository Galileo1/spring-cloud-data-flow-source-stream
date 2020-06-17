package weird.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.springframework.stereotype.Service;

/**
 * Object Mapper class to convert string to object.
 *
 * @author Varun Gaur
 */
@Service
@AllArgsConstructor
class Mapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T stringToJsonObject(@NonNull final String str, final Class<T> clazz) {

        T mappedObject = null;
        try {
            mappedObject = objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mappedObject;

    }
}
