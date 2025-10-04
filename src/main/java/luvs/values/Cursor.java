package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe cursor property values.
 */
public enum Cursor implements DelegatedCharSeq {
    AUTO("auto"),
    DEFAULT("default"),
    POINTER("pointer"),
    MOVE("move"),
    TEXT("text"),
    WAIT("wait"),
    HELP("help"),
    NOT_ALLOWED("not-allowed"),
    GRAB("grab"),
    GRABBING("grabbing"),
    CROSSHAIR("crosshair"),
    ZOOM_IN("zoom-in"),
    ZOOM_OUT("zoom-out");

    private final String value;

    Cursor(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }
}
