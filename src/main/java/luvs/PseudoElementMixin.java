package luvs;

/**
 * Composable interface providing CSS pseudo-element methods.
 * Can be mixed into HtmlTag, CssClass, Selector, or any other selector-like type.
 *
 * Implementing classes must provide getSelectorString() which returns the base selector.
 */
public interface PseudoElementMixin {

    /**
     * Returns the base selector string for this element.
     */
    CharSequence getSelectorString();

    // ========== Pseudo-elements ==========

    /**
     * Pseudo-element ::before
     */
    default Selector before() {
        return Selector.selector(getSelectorString() + "::before");
    }

    /**
     * Pseudo-element ::after
     */
    default Selector after() {
        return Selector.selector(getSelectorString() + "::after");
    }

    /**
     * Pseudo-element ::first-line
     */
    default Selector firstLine() {
        return Selector.selector(getSelectorString() + "::first-line");
    }

    /**
     * Pseudo-element ::first-letter
     */
    default Selector firstLetter() {
        return Selector.selector(getSelectorString() + "::first-letter");
    }

    /**
     * Pseudo-element ::selection
     */
    default Selector selection() {
        return Selector.selector(getSelectorString() + "::selection");
    }

    /**
     * Pseudo-element ::placeholder
     */
    default Selector placeholder() {
        return Selector.selector(getSelectorString() + "::placeholder");
    }
}
