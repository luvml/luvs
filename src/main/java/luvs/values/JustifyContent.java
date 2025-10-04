package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe justify-content property values.
 */
public enum JustifyContent implements DelegatedCharSeq {
    FLEX_START("flex-start"),
    FLEX_END("flex-end"),
    CENTER("center"),
    SPACE_BETWEEN("space-between"),
    SPACE_AROUND("space-around"),
    SPACE_EVENLY("space-evenly");

    private final String value;

    JustifyContent(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
