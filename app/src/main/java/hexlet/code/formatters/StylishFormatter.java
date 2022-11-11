package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;

public final class StylishFormatter implements Formatter {
    public static final int SPACE_COUNT = 4;
    @Override
    public String format(List<DiffElement> diff) {
        StringBuilder sb = new StringBuilder("{\n");
        for (DiffElement diffElement : diff) {
            if (DiffElement.Type.ADDED.equals(diffElement.diffElementType())) {
                sb.append(" ".repeat(2)).append("+ ");
            } else if (DiffElement.Type.REMOVED.equals(diffElement.diffElementType())) {
                sb.append(" ".repeat(2)).append("- ");
            } else {
                sb.append(" ".repeat(SPACE_COUNT));
            }
            sb.append(diffElement.key()).append(": ").append(diffElement.value()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}

