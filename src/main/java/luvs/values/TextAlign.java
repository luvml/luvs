package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe text-align property values.
 */
public enum TextAlign implements DelegatedCharSeq {
    LEFT("left"),
    RIGHT("right"),
    CENTER("center"),
    JUSTIFY("justify"),
    START("start"),
    END("end");

    private final String value;

    TextAlign(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
