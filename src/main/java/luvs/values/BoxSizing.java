package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe box-sizing property values.
 */
public enum BoxSizing implements DelegatedCharSeq {
    CONTENT_BOX("content-box"),
    BORDER_BOX("border-box");

    private final String value;

    BoxSizing(String value) {
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
