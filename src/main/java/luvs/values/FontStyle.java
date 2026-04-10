package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe font-style property values.
 */
public enum FontStyle implements DelegatedCharSeq {
    NORMAL("normal"),
    ITALIC("italic"),
    OBLIQUE("oblique");

    private final String value;

    FontStyle(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
