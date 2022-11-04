package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }

        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(content1, TreeMap.class);
        Map<String, Object> map2 = mapper.readValue(content2, TreeMap.class);
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
