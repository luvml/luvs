package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe position property values.
 */
public enum Position implements DelegatedCharSeq {
    STATIC("static"),
    RELATIVE("relative"),
    ABSOLUTE("absolute"),
    FIXED("fixed"),
    STICKY("sticky");

    private final String value;

    Position(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
