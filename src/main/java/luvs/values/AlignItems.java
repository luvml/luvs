package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe align-items property values.
 */
public enum AlignItems implements DelegatedCharSeq {
    FLEX_START("flex-start"),
    FLEX_END("flex-end"),
    CENTER("center"),
    BASELINE("baseline"),
    STRETCH("stretch");

    private final String value;

    AlignItems(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
