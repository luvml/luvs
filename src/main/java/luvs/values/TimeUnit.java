package luvs.values;

/**
 * Time unit types for CSS duration values.
 */
public enum TimeUnit {
    S("s"),    // seconds
    MS("ms");  // milliseconds

    private final String unit;

    TimeUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}
