package luvs.values;

import java.util.Objects;

/**
 * A concrete implementation of CssUnit representing a length.
 */
public final class Length implements CssUnit {
    private final Number value;
    private final LengthUnit unit;

    public Length(Number value, LengthUnit unit) {
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