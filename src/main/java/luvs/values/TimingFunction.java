package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * CSS timing function values for transitions and animations.
 */
public enum TimingFunction implements DelegatedCharSeq {
    EASE("ease"),
    LINEAR("linear"),
    EASE_IN("ease-in"),
    EASE_OUT("ease-out"),
    EASE_IN_OUT("ease-in-out"),
    STEP_START("step-start"),
    STEP_END("step-end");

    private final String value;

    TimingFunction(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * CSS cubic-bezier() function.
     * Usage: cubicBezier(0.4, 0, 0.2, 1)
     */
    public static String cubicBezier(double x1, double y1, double x2, double y2) {
        return "cubic-bezier(" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ")";
    }

    /**
     * CSS steps() function.
     * Usage: steps(4, "jump-start")
     */
    public static String steps(int count, String jumpTerm) {
        return "steps(" + count + ", " + jumpTerm + ")";
    }

    /**
     * CSS steps() function with default jump term.
     * Usage: steps(4)
     */
    public static String steps(int count) {
        return "steps(" + count + ")";
    }
}
