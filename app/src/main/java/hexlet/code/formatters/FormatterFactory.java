package hexlet.code.formatters;

public final class FormatterFactory {
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static Formatter getFormatter(String format) {
        switch (format) {
            case STYLISH:
                return new StylishFormatter();
            case PLAIN:
                return new PlainFormatter();
            case JSON:
                return new JsonFormatter();
            default:
                throw new RuntimeException("Unsupported output format");
        }
    }
}
