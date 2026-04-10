package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Represents CSS grid fractional unit (fr).
 * Used in grid-template-columns and grid-template-rows.
 *
 * Example: fr(1) → "1fr", fr(2) → "2fr"
 */
public record GridUnit(double value) implements CssUnit, DelegatedCharSeq {

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public String getUnit() {
        return "fr";
    }

    @Override
    public String delegatedCharSeqVal() {
        // Remove unnecessary decimal for whole numbers
        if (value == (long) value) {
            return String.format("%dfr", (long) value);
        }
        return String.format("%sfr", value);
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}
