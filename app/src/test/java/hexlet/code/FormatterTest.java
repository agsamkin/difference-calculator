package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class FormatterTest {
    private static final String JSON_FILE = "src/test/resources/formatter_test/file1.json";
    private static List<DiffElement> diff;

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
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String actual = Formatter.create("stylish").format(diff);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void formatPlainTest() {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        String actual = Formatter.create("plain").format(diff);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void formatJsonTest() {
        String expected = "{"
                + "\"  chars1\":[\"a\",\"b\",\"c\"],"
                + "\"- chars2\":[\"d\",\"e\",\"f\"],"
                + "\"+ chars2\":false,"
                + "\"- checked\":false,"
                + "\"+ checked\":true,"
                + "\"- default\":null,"
                + "\"+ default\":[\"value1\",\"value2\"],"
                + "\"- id\":45,"
                + "\"+ id\":null,"
                + "\"- key1\":\"value1\","
                + "\"+ key2\":\"value2\","
                + "\"  numbers1\":[1,2,3,4],"
                + "\"- numbers2\":[2,3,4,5],"
                + "\"+ numbers2\":[22,33,44,55],"
                + "\"- numbers3\":[3,4,5],"
                + "\"+ numbers4\":[4,5,6],"
                + "\"+ obj1\":{" + "\"nestedKey\":\"value\",\"isNested\":true},"
                + "\"- setting1\":\"Some value\","
                + "\"+ setting1\":\"Another value\","
                + "\"- setting2\":200,"
                + "\"+ setting2\":300,"
                + "\"- setting3\":true,"
                + "\"+ setting3\":\"none\"}";

        String actual = Formatter.create("json").format(diff);
        Assertions.assertEquals(expected, actual);
    }
}

