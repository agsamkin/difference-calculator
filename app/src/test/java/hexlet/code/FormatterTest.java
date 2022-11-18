package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class FormatterTest {
    private static final String JSON_FILE = "src/test/resources/formatter_test/file1.json";
    private static Map<String, List<DiffElement>> diff;

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
            diff = mapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void formatStylishTest() {
        String filepath = "src/test/resources/formatter_test/stylish_expected";
        String expected;
        try {
            expected = FilesUtil.readFile(filepath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Formatter formatter = FormatterFactory.getFormatter("stylish");
        String actual = formatter.format(diff);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void formatPlainTest() {
        String filepath = "src/test/resources/formatter_test/plain_expected";
        String expected;
        try {
            expected = FilesUtil.readFile(filepath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Formatter formatter = FormatterFactory.getFormatter("plain");
        String actual = formatter.format(diff);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void formatJsonTest() {
        String filepath = "src/test/resources/formatter_test/json_expected";
        String expected;
        try {
            expected = FilesUtil.readFile(filepath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Formatter formatter = FormatterFactory.getFormatter("json");
        String actual = formatter.format(diff);
        Assertions.assertEquals(expected, actual);
    }
}

