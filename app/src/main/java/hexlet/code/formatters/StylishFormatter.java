package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.Map;

public final class StylishFormatter implements Formatter {
    public static final int SPACE_COUNT = 4;

    @Override
    public String format(Map<String, DiffElement> diff) {
        StringBuilder sb = new StringBuilder("{\n");

        for (Map.Entry<String, DiffElement> element : diff.entrySet()) {
            String key = element.getKey();
            DiffElement diffElement = element.getValue();
            DiffElement.Type type = diffElement.diffElementType();
            Object value = diffElement.value();

            if (DiffElement.isAdded(type)) {
                sb.append(" ".repeat(2)).append("+ ").append(key).append(": ").append(value);
            } else if (DiffElement.isRemoved(type)) {
                sb.append(" ".repeat(2)).append("- ").append(key).append(": ").append(value);
            } else if (DiffElement.isUpdated(type)) {
                Map<String, Object> updateElement = (Map<String, Object>) value;
                Object oldValue = updateElement.get("old");
                Object newValue = updateElement.get("new");

                sb.append(" ".repeat(2)).append("- ").append(key).append(": ").append(oldValue).append("\n");
                sb.append(" ".repeat(2)).append("+ ").append(key).append(": ").append(newValue);
            } else {
                sb.append(" ".repeat(SPACE_COUNT)).append(key).append(": ").append(value);
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }
}

