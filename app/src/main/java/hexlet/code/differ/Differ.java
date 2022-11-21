package hexlet.code.differ;

import hexlet.code.FilesUtil;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.ParserFactory;

import java.util.Map;

public final class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = parse(filePath1);
        Map<String, Object> map2 = parse(filePath2);

        Map<String, DiffElement> diff = DiffCreator.getDiff(map1, map2);

        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(diff);
    }

    private static Map<String, Object> parse(String filePath) throws Exception {
        String content = FilesUtil.readFile(filePath);
        String fileExtension = FilesUtil.getFileExtension(filePath);

        Parser parser = ParserFactory.getParser(fileExtension);
        return parser.parse(content);
    }
}

