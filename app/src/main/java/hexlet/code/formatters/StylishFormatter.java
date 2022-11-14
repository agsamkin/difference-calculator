package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.Map;

public final class StylishFormatter implements Formatter {
    public static final int SPACE_COUNT = 4;

    @Override
    public String format(Map<String, List<DiffElement>> diff) {
        StringBuilder sb = new StringBuilder("{\n");

        for (Map.Entry<String, List<DiffElement>> element : diff.entrySet()) {
            List<DiffElement> values = element.getValue();
            for (DiffElement value : values) {
                DiffElement.Type type = value.diffElementType();
                if (DiffElement.isAdded(type)) {
                    sb.append(" ".repeat(2)).append("+ ");
                } else if (DiffElement.isRemoved(type)) {
                    sb.append(" ".repeat(2)).append("- ");
                } else {
                    sb.append(" ".repeat(SPACE_COUNT));
                }
                sb.append(element.getKey()).append(": ").append(value.value()).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}

