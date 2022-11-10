package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(List<DiffElement> diff) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (DiffElement diffElement : diff) {
            result.put(getKey(diffElement), diffElement.value());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error format to json", e);
        }
        return json;
    }

    private String getKey(DiffElement element) {
        if (element.diffElementType() == DiffElement.Type.ADDED) {
            return "+ " + element.key();
        } else if (element.diffElementType() == DiffElement.Type.REMOVED) {
            return "- " + element.key();
        } else {
            return " ".repeat(2) + element.key();
        }
    }
}
