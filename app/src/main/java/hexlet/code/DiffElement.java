package hexlet.code;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DiffElement(Type diffElementType, Object value) {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Type {
        ADDED("added"),
        REMOVED("removed"),
        NOT_CHANGED("not_changed");

        private String formatted;

        Type(String formattedValue) {
            this.formatted = formattedValue;
        }

        public String getFormatted() {
            return formatted;
        }
    }

    public static boolean isAdded(Type type) {
        return Type.ADDED.equals(type);
    }

    public static boolean isRemoved(Type type) {
        return Type.REMOVED.equals(type);
    }

    public static boolean isNotChanged(Type type) {
        return Type.NOT_CHANGED.equals(type);
    }
}
