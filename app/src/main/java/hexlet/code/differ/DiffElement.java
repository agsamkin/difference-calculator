package hexlet.code.differ;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DiffElement(Type diffElementType, Object value) {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Type {
        ADDED("added"),
        REMOVED("removed"),
        UPDATED("updated"),
        NOT_CHANGED("not_changed");

        private String type;

        Type(String formattedType) {
            this.type = formattedType;
        }

        public String getType() {
            return type;
        }
    }

    public static boolean isAdded(Type type) {
        return Type.ADDED.equals(type);
    }

    public static boolean isRemoved(Type type) {
        return Type.REMOVED.equals(type);
    }

    public static boolean isUpdated(Type type) {
        return Type.UPDATED.equals(type);
    }

    public static boolean isNotChanged(Type type) {
        return Type.NOT_CHANGED.equals(type);
    }
}
