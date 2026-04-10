package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe resize property values.
 */
public enum Resize implements DelegatedCharSeq {
    NONE("none"),
    BOTH("both"),
    HORIZONTAL("horizontal"),
    VERTICAL("vertical");

    private final String value;

    Resize(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
