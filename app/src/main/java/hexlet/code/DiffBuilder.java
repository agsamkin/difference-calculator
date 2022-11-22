package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DiffBuilder {
    public static Map<String, DiffElement> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, DiffElement> diff = new LinkedHashMap<>();

        Set<String> keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));

        for (String key : keys) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    DiffElement diffElement = new DiffElement(DiffElement.Type.NOT_CHANGED, map1.get(key));
                    diff.put(key, diffElement);
                } else {
                    Map<String, Object> updatedValue = new LinkedHashMap<>();
                    updatedValue.put("old", map1.get(key));
                    updatedValue.put("new", map2.get(key));
                    DiffElement diffElement = new DiffElement(DiffElement.Type.UPDATED, updatedValue);
                    diff.put(key, diffElement);
                }
            } else if (map1.containsKey(key)) {
                DiffElement diffElement = new DiffElement(DiffElement.Type.REMOVED, map1.get(key));
                diff.put(key, diffElement);
            } else if (map2.containsKey(key)) {
                DiffElement diffElement = new DiffElement(DiffElement.Type.ADDED, map2.get(key));
                diff.put(key, diffElement);
            }
        }
        return diff;
    }
}

