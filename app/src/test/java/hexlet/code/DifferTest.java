package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifferTest {
    private static final String JSON_FILE_1 = "src/test/resources/file1.json";
    private static final String JSON_FILE_2 = "src/test/resources/file2.json";

    private static final String YML_FILE_1 = "src/test/resources/file1.yml";
    private static final String YML_FILE_2 = "src/test/resources/file2.yml";

    private static final String STYLISH_EXPECTED = "src/test/resources/stylish_expected";
    private static final String PLAIN_EXPECTED = "src/test/resources/plain_expected";
    private static final String JSON_EXPECTED = "src/test/resources/json_expected";

    @Test
    void generateStylishTest() throws Exception {
        String actual1 = Differ.generate(JSON_FILE_1, JSON_FILE_2, "stylish");

        String expected1;
        try {
            expected1 = FilesUtil.readFile(STYLISH_EXPECTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(expected1, actual1);


        String actual2 = Differ.generate(YML_FILE_1, YML_FILE_2, "stylish");

        String expected2;
        try {
            expected2 = FilesUtil.readFile(STYLISH_EXPECTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void generatePlainTest() throws Exception {
        String actual1 = Differ.generate(JSON_FILE_1, JSON_FILE_2, "plain");

        String expected1;
        try {
            expected1 = FilesUtil.readFile(PLAIN_EXPECTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(expected1, actual1);


        String actual2 = Differ.generate(YML_FILE_1, YML_FILE_2, "plain");

        String expected2;
        try {
            expected2 = FilesUtil.readFile(PLAIN_EXPECTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void generateJsonTest() throws Exception {
        String actual1 = Differ.generate(JSON_FILE_1, JSON_FILE_2, "json");

        String expected1;
        try {
            expected1 = FilesUtil.readFile(JSON_EXPECTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(expected1, actual1);


        String actual2 = Differ.generate(YML_FILE_1, YML_FILE_2, "json");

        String expected2;
        try {
            expected2 = FilesUtil.readFile(JSON_EXPECTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(expected2, actual2);
    }
}


