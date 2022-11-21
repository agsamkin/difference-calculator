package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.Map;

public interface Formatter {
    String format(Map<String, DiffElement> diff);
}
