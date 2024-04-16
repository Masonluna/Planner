package edu.apsu.planner.data;

public enum Tag {
    CLASS(0),
    WORK(1),
    ASSIGNMENT(2),
    BILL(3),
    CUSTOM(4);

    private final int value;

    Tag (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Tag valueOf(int value) {
        for (Tag tag : Tag.values()) {
            if (tag.value == value)
                return tag;
        }

        throw new IllegalArgumentException();
    }
}
