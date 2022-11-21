package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.Map;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, DiffElement> diff) {
        String updatedStringTemplate = "Property '%s' was updated. From %s to %s";
        String addedStringTemplate = "Property '%s' was added with value: %s";
        String removedStringTemplate = "Property '%s' was removed";

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, DiffElement> element : diff.entrySet()) {
            String key = element.getKey();
            DiffElement diffElement = element.getValue();
            DiffElement.Type type = diffElement.diffElementType();
            Object value = diffElement.value();

            if (DiffElement.isAdded(type)) {
                String addedValue = getValue(value);
                String reportRow = String.format(addedStringTemplate, key, addedValue);
                sb.append(reportRow).append("\n");
            } else if (DiffElement.isRemoved(type)) {
                String reportRow = String.format(removedStringTemplate, key);
                sb.append(reportRow).append("\n");
            } else if (DiffElement.isUpdated(type)) {
                Map<String, Object> updateElement = (Map<String, Object>) value;
                Object oldValue = updateElement.get("old");
                Object newValue = updateElement.get("new");

                String oldValueString = getValue(oldValue);
                String newValueString = getValue(newValue);

                String reportRow = String.format(updatedStringTemplate, key, oldValueString, newValueString);
                sb.append(reportRow).append("\n");
            } else {
                continue;
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

