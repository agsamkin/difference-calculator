package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

class ParserTest {
    public static final int TIMEOUT_BEFORE = 50;
    public static final int TIMEOUT_AFTER = 20;

    @Test
    void parseJson1() throws Exception {
        String content = """
                {
                    "host": "hexlet.io",
                    "timeout": 50,
                    "proxy": "123.234.53.22",
                    "follow": false
                }""";

        Map<String, Object> expected = new TreeMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", TIMEOUT_BEFORE);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        Map<String, Object> actual = Parser.parseJson(content);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void parseJson2() throws Exception {
        String content = """
                {
                    "timeout": 20,
                    "verbose": true,
                    "host": "hexlet.io"
                }""";

        Map<String, Object> expected = new TreeMap<>();
        expected.put("timeout", TIMEOUT_AFTER);
        expected.put("verbose", true);
        expected.put("host", "hexlet.io");

        Map<String, Object> actual = Parser.parseJson(content);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void parseYml1() throws Exception {
        String content = """
                "host": "hexlet.io"
                "timeout": 50
                "proxy": "123.234.53.22"
                "follow": false""";

        Map<String, Object> expected = new TreeMap<>();
        expected.put("host", "hexlet.io");
        expected.put("timeout", TIMEOUT_BEFORE);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);

        Map<String, Object> actual = Parser.parseYml(content);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void parseYml2() throws Exception {
        String content = """
                "timeout": 20
                "verbose": true
                "host": "hexlet.io" """;

        Map<String, Object> expected = new TreeMap<>();
        expected.put("timeout", TIMEOUT_AFTER);
        expected.put("verbose", true);
        expected.put("host", "hexlet.io");

        Map<String, Object> actual = Parser.parseYml(content);
        Assertions.assertEquals(actual, expected);
    }
}
