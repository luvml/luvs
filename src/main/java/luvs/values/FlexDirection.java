package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe flex-direction property values.
 */
public enum FlexDirection implements DelegatedCharSeq {
    ROW("row"),
    ROW_REVERSE("row-reverse"),
    COLUMN("column"),
    COLUMN_REVERSE("column-reverse");

    private final String value;

    FlexDirection(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
