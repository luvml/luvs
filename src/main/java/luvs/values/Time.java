package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe time/duration value for CSS.
 * Usage: s(0.3), ms(200)
 */
public class Time implements DelegatedCharSeq, CssUnit {
    private final Number value;
    private final TimeUnit unit;

    public Time(Number value, TimeUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public String getUnit() {
        return unit.toString();
    }

    @Override
    public String delegatedCharSeqVal() {
        // Format numbers cleanly (remove unnecessary decimals)
        if (value instanceof Double || value instanceof Float) {
            double d = value.doubleValue();
            if (d == (long) d) {
                return String.format("%d%s", (long) d, unit);
            }
        }
        return value + unit.toString();
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}
