package luvs;

/**
 * Composable interface providing CSS pseudo-class methods.
 * Can be mixed into HtmlTag, CssClass, Selector, or any other selector-like type.
 *
 * Implementing classes must provide getSelectorString() which returns the base selector.
 */
public interface PseudoClassMixin {

    /**
     * Returns the base selector string for this element.
     */
    CharSequence getSelectorString();

    // ========== Interactive Pseudo-classes ==========

    /**
     * Pseudo-class :hover
     */
    default Selector hover() {
        return Selector.selector(getSelectorString() + ":hover");
    }

    /**
     * Pseudo-class :focus
     */
    default Selector focus() {
        return Selector.selector(getSelectorString() + ":focus");
    }

    /**
     * Pseudo-class :active
     */
    default Selector active() {
        return Selector.selector(getSelectorString() + ":active");
    }

    // ========== Structural Pseudo-classes ==========

    /**
     * Pseudo-class :first-child
     */
    default Selector firstChild() {
        return Selector.selector(getSelectorString() + ":first-child");
    }

    /**
     * Pseudo-class :last-child
     */
    default Selector lastChild() {
        return Selector.selector(getSelectorString() + ":last-child");
    }

    /**
     * Pseudo-class :nth-child(n)
     */
    default Selector nthChild(CharSequence n) {
        return Selector.selector(getSelectorString() + ":nth-child(" + n + ")");
    }

    /**
     * Pseudo-class :nth-of-type(n)
     */
    default Selector nthOfType(CharSequence n) {
        return Selector.selector(getSelectorString() + ":nth-of-type(" + n + ")");
    }

    /**
     * Pseudo-class :not(selector)
     * Auto-detects CssClass and adds dot prefix
     */
    default Selector not(CharSequence selector) {
        if (selector instanceof CssClass) {
            return Selector.selector(getSelectorString() + ":not(" + ((CssClass) selector).getSelector() + ")");
        }
        return Selector.selector(getSelectorString() + ":not(" + selector + ")");
    }

    // ========== Form/Input State Pseudo-classes ==========

    /**
     * Pseudo-class :disabled
     */
    default Selector disabled() {
        return Selector.selector(getSelectorString() + ":disabled");
    }

    /**
     * Pseudo-class :enabled
     */
    default Selector enabled() {
        return Selector.selector(getSelectorString() + ":enabled");
    }

    /**
     * Pseudo-class :checked
     */
    default Selector checked() {
        return Selector.selector(getSelectorString() + ":checked");
    }

    /**
     * Pseudo-class :required
     */
    default Selector required() {
        return Selector.selector(getSelectorString() + ":required");
    }

    /**
     * Pseudo-class :optional
     */
    default Selector optional() {
        return Selector.selector(getSelectorString() + ":optional");
    }

    /**
     * Pseudo-class :valid
     */
    default Selector valid() {
        return Selector.selector(getSelectorString() + ":valid");
    }

    /**
     * Pseudo-class :invalid
     */
    default Selector invalid() {
        return Selector.selector(getSelectorString() + ":invalid");
    }

    /**
     * Pseudo-class :read-only
     */
    default Selector readOnly() {
        return Selector.selector(getSelectorString() + ":read-only");
    }

    /**
     * Pseudo-class :read-write
     */
    default Selector readWrite() {
        return Selector.selector(getSelectorString() + ":read-write");
    }

    /**
     * Pseudo-class :empty
     */
    default Selector empty() {
        return Selector.selector(getSelectorString() + ":empty");
    }
}
