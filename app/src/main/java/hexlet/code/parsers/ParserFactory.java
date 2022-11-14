package hexlet.code.parsers;

public final class ParserFactory {
    public static Parser getParser(String fileExtension) {
        switch (fileExtension) {
            case "json":
                return new JsonParser();
            case "yml":
                return new YmlParser();
            default:
                throw new RuntimeException("Unknown file extension");
        }
    }
}
