package hexlet.code;

import hexlet.code.formatters.Formatter;
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
        diff.add(new DiffElement(DiffElement.Type.NOT_CHANGED, "chars1", List.of("a", "b", "c")));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "chars2", List.of("d", "e", "f")));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "chars2", false));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "checked", false));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "checked", true));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "default", null));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "default", List.of("value1", "value2")));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "id", 45));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "id", null));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "key1", "value1"));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "key2", "value2"));
        diff.add(new DiffElement(DiffElement.Type.NOT_CHANGED, "numbers1", List.of(1, 2, 3, 4)));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "numbers2", List.of(2, 3, 4, 5)));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "numbers2", List.of(22, 33, 44, 55)));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "numbers3", List.of(3, 4, 5)));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "numbers4", List.of(4, 5, 6)));

        Map<String, Object> obj1 = new LinkedHashMap<>();
        obj1.put("nestedKey", "value");
        obj1.put("isNested", true);
        diff.add(new DiffElement(DiffElement.Type.ADDED, "obj1", obj1));

        diff.add(new DiffElement(DiffElement.Type.REMOVED, "setting1", "Some value"));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "setting1", "Another value"));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "setting2", 200));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "setting2", 300));
        diff.add(new DiffElement(DiffElement.Type.REMOVED, "setting3", true));
        diff.add(new DiffElement(DiffElement.Type.ADDED, "setting3", "none"));
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
        System.out.println(actual);
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
        System.out.println(actual);
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
        System.out.println(actual);
    }
}

