package hexlet.code.parsers;

public final class ParserFactory {
    private static final String JSON = "json";
    private static final String YML = "yml";

    public static Parser getParser(String fileExtension) {
        switch (fileExtension) {
            case JSON:
                return new JsonParser();
            case YML:
                return new YmlParser();
            default:
                throw new RuntimeException("Unknown file extension");
        }
    }
}
