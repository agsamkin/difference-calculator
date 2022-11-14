package hexlet.code.formatters;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        switch (format) {
            case "stylish":
                return new StylishFormatter();
            case "plain":
                return new PlainFormatter();
            case "json":
                return new JsonFormatter();
            default:
                throw new RuntimeException("Unsupported output format");
        }
    }
}
