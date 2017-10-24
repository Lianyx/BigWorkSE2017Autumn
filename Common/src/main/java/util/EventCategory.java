package util;

public enum EventCategory {
    InsertUser,DeleteUser;//未完

    private final int value;

    private EventCategory() {
        this.value = ordinal();
    }
}
