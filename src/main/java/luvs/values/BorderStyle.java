package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe border-style property values.
 */
public enum BorderStyle implements DelegatedCharSeq {
    NONE("none"),
    SOLID("solid"),
    DASHED("dashed"),
    DOTTED("dotted"),
    DOUBLE("double"),
    GROOVE("groove"),
    RIDGE("ridge"),
    INSET("inset"),
    OUTSET("outset");

    private final String value;

    BorderStyle(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
