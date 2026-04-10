package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe border-collapse property values.
 */
public enum BorderCollapse implements DelegatedCharSeq {
    COLLAPSE("collapse"),
    SEPARATE("separate");

    private final String value;

    BorderCollapse(String value) {
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
