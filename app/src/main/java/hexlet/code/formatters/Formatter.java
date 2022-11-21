package hexlet.code.formatters;

import hexlet.code.differs.DiffElement;

import java.util.Map;

public interface Formatter {
    String format(Map<String, DiffElement> diff);
}
