package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe flex shorthand property values.
 * Common values for the flex property (flex-grow flex-shrink flex-basis).
 */
public enum Flex implements DelegatedCharSeq {
    /**
     * flex: 1 - Common default for flex items that should grow/shrink equally
     */
    ONE("1"),

    /**
     * flex: 0 - Item won't grow or shrink
     */
    ZERO("0"),

    /**
     * flex: auto - Equivalent to "1 1 auto" (can grow and shrink, basis is auto)
     */
    AUTO("auto"),

    /**
     * flex: none - Equivalent to "0 0 auto" (won't grow or shrink)
     */
    NONE("none"),

    /**
     * flex: 1 1 0% - Common pattern for equal distribution
     */
    ONE_ONE_ZERO("1 1 0%"),

    /**
     * flex: 0 0 auto - Item with intrinsic size that won't grow/shrink
     */
    ZERO_ZERO_AUTO("0 0 auto");

    private final String value;

    Flex(String value) {
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
}
