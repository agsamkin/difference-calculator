package hexlet.code;

import java.util.List;

public final class Formatter {
    public static String format(String format, List<DiffElement> diff) {
        if ("stylish".equals(format)) {
            return formatToStylish(diff);
        } else {
            throw new RuntimeException("Unsupported output format");
        }
    }

    private static String formatToStylish(List<DiffElement> diff) {
        StringBuilder sb = new StringBuilder("{\n");
        for (DiffElement diffElement : diff) {
            if (DiffElement.DiffElementType.ADDED.equals(diffElement.diffElementType())) {
                sb.append("+ ");
            } else if (DiffElement.DiffElementType.REMOVED.equals(diffElement.diffElementType())) {
                sb.append("- ");
            } else {
                sb.append(" ".repeat(2));
            }
            sb.append(diffElement.key()).append(": ").append(diffElement.value()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
