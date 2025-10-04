package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Represents a CSS calc() expression.
 * Automatically wraps arithmetic operations in calc().
 *
 * Usage:
 * <pre>
 * width(percent(100).minus(px(20)))  // calc(100% - 20px)
 * height(em(2).plus(px(10)))         // calc(2em + 10px)
 * </pre>
 */
public final class CalcExpression implements CssUnit {
    private final String expression;

    private CalcExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Creates a calc expression from a string.
     * Usage: calc("100% - 20px")
     */
    public static CalcExpression calc(String expression) {
        return new CalcExpression(expression);
    }

    /**
     * Creates a calc expression from parts (for type-safe composition).
     */
    static CalcExpression of(CharSequence left, String operator, CharSequence right) {
        return new CalcExpression(left + " " + operator + " " + right);
    }

    @Override
    public Number getValue() {
        return 0; // Not applicable for calc expressions
    }

    @Override
    public String getUnit() {
        return ""; // calc() has no unit suffix
    }

    @Override
    public String delegatedCharSeqVal() {
        return "calc(" + expression + ")";
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }

    // Chainable arithmetic for nested calc expressions
    @Override
    public CalcExpression plus(CharSequence other) {
        return of(this.expression, "+", other);
    }

    @Override
    public CalcExpression minus(CharSequence other) {
        return of(this.expression, "-", other);
    }

    @Override
    public CalcExpression times(CharSequence other) {
        return of(this.expression, "*", other);
    }

    @Override
    public CalcExpression divide(CharSequence other) {
        return of(this.expression, "/", other);
    }
}
