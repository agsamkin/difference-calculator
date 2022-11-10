package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;

public interface Formatter {
    String format(List<DiffElement> diff);

    static Formatter create(Format format) {
        if (format == Format.STYLISH) {
            return new StylishFormatter();
        } else if (format == Format.PLAIN) {
            return new PlainFormatter();
        } else if (format == Format.JSON) {
            return new JsonFormatter();
        } else {
            throw new RuntimeException("Unsupported output format");
        }
    }
}
