package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);

        List<DiffElement> diff = getDiff(map1, map2);
        return Formatter.create(format).format(diff);
    }

    public static List<DiffElement> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        List<DiffElement> diff = new LinkedList<>();

        Set<String> keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));

        for (String key : keys) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    diff.add(new DiffElement(DiffElement.Type.NOT_CHANGED, key, map1.get(key)));
                } else {
                    diff.add(new DiffElement(DiffElement.Type.REMOVED, key, map1.get(key)));
                    diff.add(new DiffElement(DiffElement.Type.ADDED, key, map2.get(key)));
                }
            } else if (map1.containsKey(key)) {
                diff.add(new DiffElement(DiffElement.Type.REMOVED, key, map1.get(key)));
            } else {
                diff.add(new DiffElement(DiffElement.Type.ADDED, key, map2.get(key)));
            }
        }
        return diff;
    }
}
