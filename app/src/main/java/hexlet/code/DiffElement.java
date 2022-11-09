package hexlet.code;

public record DiffElement(Type diffElementType, String key, Object value) {
    public enum Type {
        ADDED, REMOVED, NOT_CHANGED
    }
}
