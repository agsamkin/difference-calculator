package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

public final class JsonParser implements Parser {
    @Override
    public Map<String, Object> parse(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, TreeMap.class);
    }
}
