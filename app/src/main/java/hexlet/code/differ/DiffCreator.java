package hexlet.code.differ;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DiffCreator {
    public static Map<String, DiffElement> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, DiffElement> diff = new LinkedHashMap<>();

        Set<String> keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));

        for (String key : keys) {
            DiffElement diffElement;
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    diffElement = new DiffElement(DiffElement.Type.NOT_CHANGED, map1.get(key));
                } else {
                    Map<String, Object> updatedValue = new LinkedHashMap<>();
                    updatedValue.put("old", map1.get(key));
                    updatedValue.put("new", map2.get(key));
                    diffElement = new DiffElement(DiffElement.Type.UPDATED, updatedValue);
                }
            } else if (map1.containsKey(key)) {
                diffElement = new DiffElement(DiffElement.Type.REMOVED, map1.get(key));
            } else {
                diffElement = new DiffElement(DiffElement.Type.ADDED, map2.get(key));
            }
            diff.put(key, diffElement);
        }

        return diff;
    }
}

