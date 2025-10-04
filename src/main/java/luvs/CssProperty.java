package luvs;

import java.util.Objects;
import luvx.DelegatedCharSeq;

/**
 * Represents a single, immutable CSS property-value pair (e.g., "color: blue;").
 * Implements DelegatedCharSeq to be used in varargs with Strings.
 */
public final class CssProperty implements DelegatedCharSeq {

    private final String name;
    private final CharSequence value;

    public CssProperty(String name, CharSequence value) {
        this.name = Objects.requireNonNull(name, "Property name cannot be null").trim();
        this.value = Objects.requireNonNull(value, "Property value cannot be null");
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Property name cannot be empty.");
        }
    }

    public String getName() {
        return name;
    }

    public CharSequence getValue() {
        return value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return name + ": " + value + ";";
    }
}