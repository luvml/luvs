package luvs.values;

import java.util.Objects;

/**
 * A concrete implementation of CssUnit representing an angle.
 */
public final class Angle implements CssUnit {
    private final Number value;
    private final AngleUnit unit;

    public Angle(Number value, AngleUnit unit) {
        this.value = Objects.requireNonNull(value, "Value cannot be null");
        this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
    }

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public String getUnit() {
        return unit.getSuffix();
    }
}
