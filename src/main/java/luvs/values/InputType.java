package luvs.values;

import luvx.NamedEnumCharSeq;

/**
 * HTML input type attribute values.
 * Used with input[type="..."] selectors.
 */
public enum InputType implements NamedEnumCharSeq {
    // Text inputs
    TEXT("text"),
    NUMBER("number"),
    EMAIL("email"),
    PASSWORD("password"),
    SEARCH("search"),
    TEL("tel"),
    URL("url"),

    // Choice inputs
    CHECKBOX("checkbox"),
    RADIO("radio"),

    // Date/Time inputs
    DATE("date"),
    TIME("time"),
    DATETIME_LOCAL("datetime-local"),
    MONTH("month"),
    WEEK("week"),

    // Other inputs
    FILE("file"),
    HIDDEN("hidden"),
    RANGE("range"),
    COLOR("color"),

    // Buttons
    SUBMIT("submit"),
    BUTTON("button"),
    RESET("reset"),

    // HTML5
    IMAGE("image");

    private final String value;

    InputType(String value) {
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
