package luvs.values;

/**
 * An enumeration of standard CSS length units.
 */
public enum LengthUnit {
    PX("px"),
    REM("rem"),
    EM("em"),
    PERCENT("%"),
    VH("vh"),
    VW("vw"),
    VMIN("vmin"),
    VMAX("vmax"),
    CM("cm"),
    MM("mm"),
    IN("in"),
    PT("pt"),
    PC("pc");

    private final String suffix;

    LengthUnit(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}