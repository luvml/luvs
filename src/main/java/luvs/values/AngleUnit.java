package luvs.values;

/**
 * An enumeration of standard CSS angle units.
 */
public enum AngleUnit {
    DEG("deg"),
    RAD("rad"),
    GRAD("grad"),
    TURN("turn");

    private final String suffix;

    AngleUnit(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
