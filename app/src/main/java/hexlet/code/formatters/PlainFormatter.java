package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter {
    private static final int UPDATED_COUNT = 2;
    private static final int REMOVED_INDEX = 0;
    private static final int ADDED_INDEX = 1;

    @Override
    public String format(Map<String, List<DiffElement>> diff) {
        String updatedStringTemplate = "Property '%s' was updated. From %s to %s";
        String addedStringTemplate = "Property '%s' was added with value: %s";
        String removedStringTemplate = "Property '%s' was removed";

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<DiffElement>> element : diff.entrySet()) {
            String key = element.getKey();
            List<DiffElement> values = element.getValue();

            if (values.size() == UPDATED_COUNT) {
                DiffElement removed = values.get(REMOVED_INDEX);
                String valueFrom = getValue(removed.value());

                DiffElement added = values.get(ADDED_INDEX);
                String valueTo = getValue(added.value());

                String reportRow = String.format(updatedStringTemplate, key, valueFrom, valueTo);
                sb.append(reportRow).append("\n");
                continue;
            }

            for (DiffElement value : values) {
                DiffElement.Type type = value.diffElementType();
                if (DiffElement.isAdded(type)) {
                    String addedValue = getValue(value.value());
                    String reportRow = String.format(addedStringTemplate, key, addedValue);
                    sb.append(reportRow).append("\n");
                } else if (DiffElement.isRemoved(type)) {
                    String reportRow = String.format(removedStringTemplate, key);
                    sb.append(reportRow).append("\n");
                } else {
                    continue;
                }
            }
        }

        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private String getValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Integer
                || value instanceof Boolean) {
            return value.toString();
        } else {
            return "[complex value]";
        }
    }
}

