package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

class DifferTest {

    public static final int TIMEOUT_BEFORE = 50;
    public static final int TIMEOUT_AFTER = 20;

    private static Map<String, Object> json1;
    private static Map<String, Object> json2;
    private static String expected;

    @BeforeAll
    public static void setUp() {
        json1 = new TreeMap<>();
        json1.put("host", "hexlet.io");
        json1.put("timeout", TIMEOUT_BEFORE);
        json1.put("proxy", "123.234.53.22");
        json1.put("follow", false);

        json2 = new TreeMap<>();
        json2.put("timeout", TIMEOUT_AFTER);
        json2.put("verbose", true);
        json2.put("host", "hexlet.io");

        expected = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
    }

    @Test
    void getDiffTest() {
        String diff = Differ.getDiff(json1, json2);
        Assertions.assertEquals(diff, expected);
    }
}

