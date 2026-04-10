package luvs;

import java.util.stream.Stream;

/**
 * Fluent API for building CSS selectors and rules.
 * Implements CharSequence so Selector can be used anywhere a CharSequence is expected.
 *
 * Usage:
 *   selector(center, ">", div).rule(color("blue"), margin(px(10)))
 */
public final class Selector implements CharSequence,
                                        AttributeSelector,
                                        PseudoClassMixin,
                                        NegatedPseudoClassMixin,
                                        PseudoElementMixin {

    private final CharSequence[] parts;

    private Selector(CharSequence... parts) {
        this.parts = parts;
    }

    /**
     * Creates a selector from mixed enums and strings.
     * Only accepts CharSequence - strings or enum constants implementing DelegatedCharSeq.
     */
    public static Selector selector(CharSequence... parts) {
        return new Selector(parts);
    }

    public CssRule ____(CssProperty... properties) {
        return rule(properties);
    }

    /**
     * Creates a CSS rule from this selector with properties.
     */
    public CssRule rule(CssProperty... properties) {
        return new CssRule(build(), properties);
    }

    /**
     * Returns the built selector string for attribute selectors.
     */
    @Override
    public CharSequence getSelectorString() {
        return build();
    }

    // ========== Chaining DSL Methods ==========

    /**
     * Child combinator: current > child
     * Usage: selector(container).child(div)
     * Usage: selector(container).child(div, highlight) for .container > div.highlight
     */
    public Selector child(CharSequence... childElements) {
        CharSequence[] newParts = Stream.concat(
            Stream.of(parts),
            Stream.concat(Stream.of(">"), Stream.of(childElements))
        ).toArray(CharSequence[]::new);
        return new Selector(newParts);
    }

    /**
     * Descendant combinator: current descendant
     */
    public Selector descendant(CharSequence descendantElement) {
        return new Selector(Stream.concat(
            Stream.of(parts),
            Stream.of(descendantElement)
        ).toArray(CharSequence[]::new));
    }

    /**
     * Adjacent sibling combinator: current + sibling
     */
    public Selector adjacent(CharSequence siblingElement) {
        return new Selector(Stream.concat(
            Stream.of(parts),
            Stream.of("+", siblingElement)
        ).toArray(CharSequence[]::new));
    }

    /**
     * General sibling combinator: current ~ sibling
     */
    public Selector sibling(CharSequence siblingElement) {
        return new Selector(Stream.concat(
            Stream.of(parts),
            Stream.of("~", siblingElement)
        ).toArray(CharSequence[]::new));
    }

    // Pseudo-class, negated pseudo-class, and pseudo-element methods inherited from mixin interfaces

    /**
     * Builds the CSS selector string.
     * CssClass constants get dot prefix, plain strings used as-is.
     */
    public String build() {
        return Stream.of(parts)
            .map(part -> {
                if (part instanceof CssClass) {
                    return "." + ((CssClass) part).getClassName();
                }
                return part.toString();
            })
            .reduce("", (a, b) -> a.isEmpty() ? b : a + " " + b)
            .trim();
    }

    @Override
    public String toString() {
        return build();
    }

    // ========== CharSequence Implementation ==========

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

}
