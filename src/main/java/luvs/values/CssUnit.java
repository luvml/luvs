package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Represents a CSS value with a specific unit (e.g., "10px", "1.5rem", "90deg").
 * Implements DelegatedCharSeq to be seamlessly used as a value in CSS properties.
 */
public interface CssUnit extends DelegatedCharSeq {
    /**
     * @return The numerical part of the value.
     */
    Number getValue();

    /**
     * @return The string representation of the unit (e.g., "px", "rem").
     */
    String getUnit();

    @Override
    public default String delegatedCharSeqVal() {
        // Avoid scientific notation for doubles like 1.0E-4
        if (getValue() instanceof Double || getValue() instanceof Float) {
             if (getValue().doubleValue() == 0) return "0";
             return String.format("%s%s", getValue(), getUnit());
        }
        return getValue() + getUnit();
    }

    // ========== Chainable Arithmetic (produces calc() expressions) ==========

    /**
     * Addition: this + other
     * Usage: percent(100).plus(px(20)) → calc(100% + 20px)
     */
    default CalcExpression plus(CharSequence other) {
        return CalcExpression.of(this, "+", other);
    }

    /**
     * Subtraction: this - other
     * Usage: percent(100).minus(px(20)) → calc(100% - 20px)
     */
    default CalcExpression minus(CharSequence other) {
        return CalcExpression.of(this, "-", other);
    }

    /**
     * Multiplication: this * other
     * Usage: em(2).times(1.5) → calc(2em * 1.5)
     */
    default CalcExpression times(CharSequence other) {
        return CalcExpression.of(this, "*", other);
    }

    /**
     * Division: this / other
     * Usage: percent(100).divide(3) → calc(100% / 3)
     */
    default CalcExpression divide(CharSequence other) {
        return CalcExpression.of(this, "/", other);
    }
}