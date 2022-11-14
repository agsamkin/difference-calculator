package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.ParserFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = parse(filePath1);
        Map<String, Object> map2 = parse(filePath2);

        Map<String, List<DiffElement>> diff = getDiff(map1, map2);

        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(diff);
    }

    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = FilesUtil.readFile(filePath);
        String fileExtension = FilesUtil.getFileExtension(filePath);

        Parser parser = ParserFactory.getParser(fileExtension);
        return parser.parse(content);
    }

    public static Map<String, List<DiffElement>> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, List<DiffElement>> diff = new LinkedHashMap<>();

        Set<String> keys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .collect(Collectors.toCollection(TreeSet::new));

        for (String key : keys) {
            List<DiffElement> values = diff.getOrDefault(key, new ArrayList<>());
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    DiffElement diffElement = new DiffElement(DiffElement.Type.NOT_CHANGED, map1.get(key));
                    values.add(diffElement);
                } else {
                    DiffElement diffElement1 = new DiffElement(DiffElement.Type.REMOVED, map1.get(key));
                    values.add(diffElement1);

                    DiffElement diffElement2 = new DiffElement(DiffElement.Type.ADDED, map2.get(key));
                    values.add(diffElement2);
                }
            } else if (map1.containsKey(key)) {
                DiffElement diffElement = new DiffElement(DiffElement.Type.REMOVED, map1.get(key));
                values.add(diffElement);
            } else {
                DiffElement diffElement2 = new DiffElement(DiffElement.Type.ADDED, map2.get(key));
                values.add(diffElement2);
            }
            diff.put(key, values);
        }
        return diff;
    }

}
