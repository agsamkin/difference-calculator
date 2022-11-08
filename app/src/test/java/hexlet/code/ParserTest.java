package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class ParserTest {
    private static Map<String, Object> expected;

    @BeforeAll
    public static void setUp() {
        expected = new TreeMap<>();
        expected.put("setting1", "Some value");
        expected.put("setting2", 200);
        expected.put("setting3", true);
        expected.put("key1", "value1");
        expected.put("numbers1", List.of(1, 2, 3, 4));
        expected.put("numbers2", List.of(2, 3, 4, 5));
        expected.put("id", 45);
        expected.put("default", null);
        expected.put("checked", false);
        expected.put("numbers3", List.of(3, 4, 5));
        expected.put("chars1", List.of("a", "b", "c"));
        expected.put("chars2", List.of("d", "e", "f"));
    }

    @Test
    void parseJsonTest() throws Exception {
        Map<String, Object> actual = Parser.parse("src/test/resources/file1.json");
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void parseYml() throws Exception {
        Map<String, Object> actual = Parser.parse("src/test/resources/file1.yml");
        Assertions.assertEquals(actual, expected);
    }
}
