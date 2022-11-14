package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Map<String, List<DiffElement>> diff) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, List<DiffElement>> element : diff.entrySet()) {
            String key = element.getKey();
            List<DiffElement> values = element.getValue();
            for (DiffElement value : values) {
                DiffElement.Type type = value.diffElementType();
                result.put(getKey(key, type), value.value());
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error format to json", e);
        }
    }

    private String getKey(String key, DiffElement.Type type) {
        if (DiffElement.isAdded(type)) {
            return "+ " + key;
        } else if (DiffElement.isRemoved(type)) {
            return "- " + key;
        } else {
            return " ".repeat(2) + key;
        }
    }
}
