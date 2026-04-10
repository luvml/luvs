package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe white-space property values.
 */
public enum WhiteSpace implements DelegatedCharSeq {
    NORMAL("normal"),
    NOWRAP("nowrap"),
    PRE("pre"),
    PRE_WRAP("pre-wrap"),
    PRE_LINE("pre-line");

    private final String value;

    WhiteSpace(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
