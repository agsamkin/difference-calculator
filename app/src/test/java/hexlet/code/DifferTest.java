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
        expected.add(new DiffElement(DiffElement.DiffElementType.NOT_CHANGED, "chars1", List.of("a", "b", "c")));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "chars2", List.of("d", "e", "f")));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "chars2", false));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "checked", false));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "checked", true));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "default", null));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "default", List.of("value1", "value2")));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "id", 45));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "id", null));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "key1", "value1"));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "key2", "value2"));
        expected.add(new DiffElement(DiffElement.DiffElementType.NOT_CHANGED, "numbers1", List.of(1, 2, 3, 4)));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "numbers2", List.of(2, 3, 4, 5)));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "numbers2", List.of(22, 33, 44, 55)));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "numbers3", List.of(3, 4, 5)));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "numbers4", List.of(4, 5, 6)));

        Map<String, Object> obj1 = new LinkedHashMap<>();
        obj1.put("nestedKey", "value");
        obj1.put("isNested", true);
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "obj1", obj1));

        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "setting1", "Some value"));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "setting1", "Another value"));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "setting2", 200));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "setting2", 300));
        expected.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "setting3", true));
        expected.add(new DiffElement(DiffElement.DiffElementType.ADDED, "setting3", "none"));
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

