package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);
        return getDiff(map1, map2);
    }

    public static String getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));

        StringBuilder diff = new StringBuilder("{\n");
        for (String key : keys) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    diff.append("  ").append(key).append(": ").append(map1.get(key)).append("\n");
                } else {
                    diff.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
                    diff.append("+ ").append(key).append(": ").append(map2.get(key)).append("\n");
                }
            } else if (map1.containsKey(key)) {
                diff.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else {
                diff.append("+ ").append(key).append(": ").append(map2.get(key)).append("\n");
            }
        }
        diff.append("}");
        return diff.toString();
    }
}
