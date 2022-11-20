package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ParserTest {
    private static final String JSON_FILE = "src/test/resources/parser_test/file1.json";
    private static Map<String, Object> expected;

    @BeforeAll
    public static void setUp() {
        String content;
        try {
            content = FilesUtil.readFile(JSON_FILE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            expected = mapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseJsonTest() throws Exception {
        Map<String, Object> actual = Differ.parse("src/test/resources/file1.json");
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void parseYmlTest() throws Exception {
        Map<String, Object> actual = Differ.parse("src/test/resources/file1.yml");
        Assertions.assertEquals(actual, expected);
    }
}
