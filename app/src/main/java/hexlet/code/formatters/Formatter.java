package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;

public interface Formatter {
    String format(List<DiffElement> diff);

    static Formatter create(String format) {
        if ("stylish".equals(format)) {
            return new StylishFormatter();
        } else if ("plain".equals(format)) {
            return new PlainFormatter();
        } else if ("json".equals(format)) {
            return new JsonFormatter();
        } else {
            throw new RuntimeException("Unsupported output format");
        }
    }
}
