package luvs;

import java.util.stream.Stream;

/**
 * Fluent API for building CSS selectors and rules.
 *
 * Usage:
 *   selector(center, ">", div).rule(color("blue"), margin(px(10)))
 */
public final class Selector {

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

    /**
     * Pseudo-class :hover
     */
    public Selector hover() {
        return appendPseudo(":hover");
    }

    /**
     * Pseudo-class :focus
     */
    public Selector focus() {
        return appendPseudo(":focus");
    }

    /**
     * Pseudo-class :active
     */
    public Selector active() {
        return appendPseudo(":active");
    }

    /**
     * Pseudo-class :first-child
     */
    public Selector firstChild() {
        return appendPseudo(":first-child");
    }

    /**
     * Pseudo-class :last-child
     */
    public Selector lastChild() {
        return appendPseudo(":last-child");
    }

    /**
     * Pseudo-class :nth-child(n)
     */
    public Selector nthChild(CharSequence n) {
        return appendPseudo(":nth-child(" + n + ")");
    }

    /**
     * Pseudo-class :nth-of-type(n)
     */
    public Selector nthOfType(CharSequence n) {
        return appendPseudo(":nth-of-type(" + n + ")");
    }

    /**
     * Pseudo-class :not(selector)
     * Auto-detects CssClass and adds dot prefix
     */
    public Selector not(CharSequence selector) {
        if (selector instanceof CssClass) {
            return appendPseudo(":not(" + ((CssClass) selector).getSelector() + ")");
        }
        return appendPseudo(":not(" + selector + ")");
    }

    // ========== Pseudo-elements ==========

    /**
     * Pseudo-element ::before
     */
    public Selector before() {
        return appendPseudo("::before");
    }

    /**
     * Pseudo-element ::after
     */
    public Selector after() {
        return appendPseudo("::after");
    }

    /**
     * Pseudo-element ::first-line
     */
    public Selector firstLine() {
        return appendPseudo("::first-line");
    }

    /**
     * Pseudo-element ::first-letter
     */
    public Selector firstLetter() {
        return appendPseudo("::first-letter");
    }

    /**
     * Pseudo-element ::selection
     */
    public Selector selection() {
        return appendPseudo("::selection");
    }

    /**
     * Pseudo-element ::placeholder
     */
    public Selector placeholder() {
        return appendPseudo("::placeholder");
    }

    private Selector appendPseudo(String pseudo) {
        CharSequence[] newParts = parts.clone();
        int lastIdx = newParts.length - 1;
        newParts[lastIdx] = newParts[lastIdx].toString() + pseudo;
        return new Selector(newParts);
    }

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

}
