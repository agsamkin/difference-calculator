package hexlet.code;

public record DiffElement(hexlet.code.DiffElement.DiffElementType diffElementType, String key, Object value) {
    public enum DiffElementType {
        ADDED, REMOVED, NOT_CHANGED
    }
}
