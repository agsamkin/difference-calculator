package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.Map;

public interface Formatter {
    String format(Map<String, List<DiffElement>> diff);
}
