package hexlet.code;

public record DiffElement(Type diffElementType, Object value) {
    public enum Type {
        ADDED, REMOVED, NOT_CHANGED
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
