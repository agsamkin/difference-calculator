package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.differ.DiffElement;

import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Map<String, DiffElement> diff) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error format to json", e);
        }
    }
}
