package luvs;

import luvx.NamedEnumCharSeq;

/**
 * Marker interface for CSS class name enums.
 * Enums implementing this become reusable, type-safe CSS class names.
 * Define just the names in enum, define rules separately using .____ or chaining.
 */
public interface CssClass extends NamedEnumCharSeq {

    /**
     * Returns CSS class name (enum name).
     */
    default String getClassName() {
        return ((Enum<?>) this).name();
    }

    /**
     * Returns CSS selector (.className).
     */
    default String getSelector() {
        return "." + getClassName();
    }

    /**
     * For use in class_() attribute.
     */
    @Override
    default String delegatedCharSeqVal() {
        return getClassName();
    }

    // ========== Fluent Chaining DSL Methods ==========

    /**
     * Creates a Selector starting with this class.
     * Usage: center.asSelector()
     */
    default Selector asSelector() {
        return Selector.selector(this);
    }

    /**
     * Quick rule creation: .className { props }
     * Usage: center.____(color("red"), ...)
     */
    default CssRule ____(CssProperty... properties) {
        return new CssRule(getSelector(), properties);
    }

    /**
     * Child combinator: .className > child
     * Usage: center.child(div)
     * Usage: center.child(div, highlight) for .center > div.highlight
     */
    default Selector child(CharSequence... childElements) {
        CharSequence[] parts = new CharSequence[childElements.length + 2];
        parts[0] = this;
        parts[1] = ">";
        System.arraycopy(childElements, 0, parts, 2, childElements.length);
        return Selector.selector(parts);
    }

    /**
     * Descendant combinator: .className descendant
     */
    default Selector descendant(CharSequence descendantElement) {
        return Selector.selector(this, descendantElement);
    }

    /**
     * Adjacent sibling combinator: .className + sibling
     */
    default Selector adjacent(CharSequence siblingElement) {
        return Selector.selector(this, "+", siblingElement);
    }

    /**
     * General sibling combinator: .className ~ sibling
     */
    default Selector sibling(CharSequence siblingElement) {
        return Selector.selector(this, "~", siblingElement);
    }

    /**
     * Pseudo-class :hover
     */
    default Selector hover() {
        return Selector.selector(getSelector() + ":hover");
    }

    /**
     * Pseudo-class :focus
     */
    default Selector focus() {
        return Selector.selector(getSelector() + ":focus");
    }

    /**
     * Pseudo-class :active
     */
    default Selector active() {
        return Selector.selector(getSelector() + ":active");
    }

    /**
     * Pseudo-class :first-child
     */
    default Selector firstChild() {
        return Selector.selector(getSelector() + ":first-child");
    }

    /**
     * Pseudo-class :last-child
     */
    default Selector lastChild() {
        return Selector.selector(getSelector() + ":last-child");
    }

    /**
     * Pseudo-class :nth-child(n)
     */
    default Selector nthChild(CharSequence n) {
        return Selector.selector(getSelector() + ":nth-child(" + n + ")");
    }

    /**
     * Pseudo-class :nth-of-type(n)
     */
    default Selector nthOfType(CharSequence n) {
        return Selector.selector(getSelector() + ":nth-of-type(" + n + ")");
    }

    /**
     * Pseudo-class :not(selector)
     * Auto-detects CssClass and adds dot prefix
     */
    default Selector not(CharSequence selector) {
        if (selector instanceof CssClass) {
            return Selector.selector(getSelector() + ":not(" + ((CssClass) selector).getSelector() + ")");
        }
        return Selector.selector(getSelector() + ":not(" + selector + ")");
    }

    // ========== Pseudo-elements ==========

    /**
     * Pseudo-element ::before
     */
    default Selector before() {
        return Selector.selector(getSelector() + "::before");
    }

    /**
     * Pseudo-element ::after
     */
    default Selector after() {
        return Selector.selector(getSelector() + "::after");
    }

    /**
     * Pseudo-element ::first-line
     */
    default Selector firstLine() {
        return Selector.selector(getSelector() + "::first-line");
    }

    /**
     * Pseudo-element ::first-letter
     */
    default Selector firstLetter() {
        return Selector.selector(getSelector() + "::first-letter");
    }

    /**
     * Pseudo-element ::selection
     */
    default Selector selection() {
        return Selector.selector(getSelector() + "::selection");
    }

    /**
     * Pseudo-element ::placeholder
     */
    default Selector placeholder() {
        return Selector.selector(getSelector() + "::placeholder");
    }
}
