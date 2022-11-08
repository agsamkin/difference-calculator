package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class FormatterTest {
    private static List<DiffElement> diff;

    @BeforeAll
    public static void setUp() {
        diff = new LinkedList<>();
        diff.add(new DiffElement(DiffElement.DiffElementType.NOT_CHANGED, "chars1", List.of("a", "b", "c")));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "chars2", List.of("d", "e", "f")));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "chars2", false));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "checked", false));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "checked", true));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "default", null));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "default", List.of("value1", "value2")));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "id", 45));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "id", null));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "key1", "value1"));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "key2", "value2"));
        diff.add(new DiffElement(DiffElement.DiffElementType.NOT_CHANGED, "numbers1", List.of(1, 2, 3, 4)));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "numbers2", List.of(2, 3, 4, 5)));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "numbers2", List.of(22, 33, 44, 55)));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "numbers3", List.of(3, 4, 5)));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "numbers4", List.of(4, 5, 6)));

        Map<String, Object> obj1 = new LinkedHashMap<>();
        obj1.put("nestedKey", "value");
        obj1.put("isNested", true);
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "obj1", obj1));

        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "setting1", "Some value"));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "setting1", "Another value"));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "setting2", 200));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "setting2", 300));
        diff.add(new DiffElement(DiffElement.DiffElementType.REMOVED, "setting3", true));
        diff.add(new DiffElement(DiffElement.DiffElementType.ADDED, "setting3", "none"));
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

        String actual = Formatter.format("stylish", diff);
        Assertions.assertEquals(expected, actual);
        System.out.println(actual);
    }
}