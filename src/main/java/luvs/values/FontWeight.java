package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe font-weight property values.
 */
public enum FontWeight implements DelegatedCharSeq {
    NORMAL("normal"),
    BOLD("bold"),
    BOLDER("bolder"),
    LIGHTER("lighter"),
    W100("100"),
    W200("200"),
    W300("300"),
    W400("400"),
    W500("500"),
    W600("600"),
    W700("700"),
    W800("800"),
    W900("900");

    private final String value;

    FontWeight(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
