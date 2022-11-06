package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;
import java.util.TreeMap;

public final class Parser {
    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = FilesUtil.readFile(filePath);
        if (content.equals("")) {
            return new TreeMap<>();
        }

        Map<String, Object> map;
        if (FilesUtil.getFileExtension(filePath).equals("json")) {
            map = parseJson(content);
        } else if (FilesUtil.getFileExtension(filePath).equals("yml")) {
            map = parseYml(content);
        } else {
            throw new Exception("Incorrect file extension");
        }
        return map;
    }

    public static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, TreeMap.class);
    }

    public static Map<String, Object> parseYml(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, TreeMap.class);
    }
}
