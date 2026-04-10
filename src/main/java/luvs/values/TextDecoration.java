package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe text-decoration property values.
 */
public enum TextDecoration implements DelegatedCharSeq {
    NONE("none"),
    UNDERLINE("underline"),
    OVERLINE("overline"),
    LINE_THROUGH("line-through");

    private final String value;

    TextDecoration(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
