package hexlet.code.formatters;

import hexlet.code.differ.DiffElement;

import java.util.Map;

public interface Formatter {
    String format(Map<String, DiffElement> diff);
}
