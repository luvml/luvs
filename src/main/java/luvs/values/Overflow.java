package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe overflow property values.
 */
public enum Overflow implements DelegatedCharSeq {
    VISIBLE("visible"),
    HIDDEN("hidden"),
    SCROLL("scroll"),
    AUTO("auto"),
    CLIP("clip");

    private final String value;

    Overflow(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
