package luvs.values;

import luvx.NamedEnumCharSeq;

/**
 * Grid repeat modes for grid-template-columns/rows.
 * Used with repeat() function.
 */
public enum GridRepeatMode implements NamedEnumCharSeq {
    /**
     * auto-fit: fits as many columns as possible, collapsing empty tracks
     */
    AUTO_FIT("auto-fit"),

    /**
     * auto-fill: fits as many columns as possible, preserving empty tracks
     */
    AUTO_FILL("auto-fill");

    private final String value;

    GridRepeatMode(String value) {
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
