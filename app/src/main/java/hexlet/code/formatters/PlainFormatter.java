package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.ListIterator;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(List<DiffElement> diff) {
        String updatedStringTemplate = "Property '%s' was updated. From %s to %s";
        String addedStringTemplate = "Property '%s' was added with value: %s";
        String removedStringTemplate = "Property '%s' was removed";

        StringBuilder sb = new StringBuilder();
        ListIterator<DiffElement> itr = diff.listIterator();

        while (itr.hasNext()) {
            DiffElement current = itr.next();
            if (current.diffElementType() == DiffElement.Type.NOT_CHANGED) {
                continue;
            }

            DiffElement next = null;
            if (itr.hasNext()) {
                next = itr.next();
            }

            boolean isUpdated = false;
            if (current.diffElementType() == DiffElement.Type.REMOVED) {

                if (next != null && current.key() == next.key()) {
                    String valueFrom = getValue(current);
                    String valueTo = getValue(next);
                    String reportRow = String.format(updatedStringTemplate, next.key(), valueFrom, valueTo);
                    sb.append(reportRow);
                    isUpdated = true;
                } else {
                    String reportRow = String.format(removedStringTemplate, current.key());
                    sb.append(reportRow);
                }

            } else if (current.diffElementType() == DiffElement.Type.ADDED) {
                String value = getValue(current);
                String reportRow = String.format(addedStringTemplate, current.key(), value);
                sb.append(reportRow);
            }

            if (!isUpdated) {
                itr.previous();
            }

            sb.append("\n");
        }

        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private String getValue(DiffElement element) {
        if (element.value() == null) {
            return null;
        }

        if (element.value() instanceof String) {
            return "'" + element.value() + "'";
        } else if (element.value() instanceof Integer
                || element.value() instanceof Boolean) {
            return element.value().toString();
        } else {
            return "[complex value]";
        }
    }
}

