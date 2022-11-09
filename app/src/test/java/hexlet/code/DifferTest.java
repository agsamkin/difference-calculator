package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class DifferTest {
    private static List<DiffElement> expected;

    @BeforeAll
    public static void setUp() {
        expected = new LinkedList<>();
        expected.add(new DiffElement(DiffElement.Type.NOT_CHANGED, "chars1", List.of("a", "b", "c")));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "chars2", List.of("d", "e", "f")));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "chars2", false));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "checked", false));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "checked", true));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "default", null));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "default", List.of("value1", "value2")));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "id", 45));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "id", null));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "key1", "value1"));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "key2", "value2"));
        expected.add(new DiffElement(DiffElement.Type.NOT_CHANGED, "numbers1", List.of(1, 2, 3, 4)));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "numbers2", List.of(2, 3, 4, 5)));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "numbers2", List.of(22, 33, 44, 55)));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "numbers3", List.of(3, 4, 5)));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "numbers4", List.of(4, 5, 6)));

        Map<String, Object> obj1 = new LinkedHashMap<>();
        obj1.put("nestedKey", "value");
        obj1.put("isNested", true);
        expected.add(new DiffElement(DiffElement.Type.ADDED, "obj1", obj1));

        expected.add(new DiffElement(DiffElement.Type.REMOVED, "setting1", "Some value"));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "setting1", "Another value"));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "setting2", 200));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "setting2", 300));
        expected.add(new DiffElement(DiffElement.Type.REMOVED, "setting3", true));
        expected.add(new DiffElement(DiffElement.Type.ADDED, "setting3", "none"));
    }

    @Test
    void getDiffJsonTest() throws Exception {
        Map<String, Object> map1 = Parser.parse("src/test/resources/file1.json");
        Map<String, Object> map2 = Parser.parse("src/test/resources/file2.json");
        List<DiffElement> actual = Differ.getDiff(map1, map2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getDiffYmlTest() throws Exception {
        Map<String, Object> map1 = Parser.parse("src/test/resources/file1.yml");
        Map<String, Object> map2 = Parser.parse("src/test/resources/file2.yml");
        List<DiffElement> actual = Differ.getDiff(map1, map2);

        Assertions.assertEquals(expected, actual);
    }
}

