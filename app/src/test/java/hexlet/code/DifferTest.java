package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class DifferTest {
    private static final String JSON_FILE = "src/test/resources/differ_test/file1.json";
    private static Map<String, List<DiffElement>> expected;

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
    void getDiffJsonTest() throws Exception {
        Map<String, Object> map1 = Differ.parse("src/test/resources/file1.json");
        Map<String, Object> map2 = Differ.parse("src/test/resources/file2.json");
        Map<String, List<DiffElement>> actual = Differ.getDiff(map1, map2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getDiffYmlTest() throws Exception {
        Map<String, Object> map1 = Differ.parse("src/test/resources/file1.yml");
        Map<String, Object> map2 = Differ.parse("src/test/resources/file2.yml");
        Map<String, List<DiffElement>> actual = Differ.getDiff(map1, map2);

        Assertions.assertEquals(expected, actual);
    }
}

