package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe flex-wrap property values.
 */
public enum FlexWrap implements DelegatedCharSeq {
    NOWRAP("nowrap"),
    WRAP("wrap"),
    WRAP_REVERSE("wrap-reverse");

    private final String value;

    FlexWrap(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
