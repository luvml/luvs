package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe display property values.
 */
public enum Display implements DelegatedCharSeq {
    BLOCK("block"),
    INLINE("inline"),
    INLINE_BLOCK("inline-block"),
    FLEX("flex"),
    INLINE_FLEX("inline-flex"),
    GRID("grid"),
    INLINE_GRID("inline-grid"),
    TABLE("table"),
    TABLE_ROW("table-row"),
    TABLE_CELL("table-cell"),
    NONE("none"),
    CONTENTS("contents");

    private final String value;

    Display(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
