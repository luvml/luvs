package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe object-fit property values.
 */
public enum ObjectFit implements DelegatedCharSeq {
    FILL("fill"),
    CONTAIN("contain"),
    COVER("cover"),
    NONE("none"),
    SCALE_DOWN("scale-down");

    private final String value;

    ObjectFit(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
