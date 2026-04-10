package luvs;

/**
 * Composable interface providing negated CSS pseudo-class methods (:not(...)).
 * Can be mixed into HtmlTag, CssClass, Selector, or any other selector-like type.
 *
 * Implementing classes must provide getSelectorString() which returns the base selector.
 */
public interface NegatedPseudoClassMixin {

    /**
     * Returns the base selector string for this element.
     */
    CharSequence getSelectorString();

    // ========== Negated Pseudo-classes (:not(...)) ==========
    // Convenience methods for common :not() combinations

    /**
     * Pseudo-class :not(:hover)
     */
    default Selector notHover() {
        return Selector.selector(getSelectorString() + ":not(:hover)");
    }

    /**
     * Pseudo-class :not(:focus)
     */
    default Selector notFocus() {
        return Selector.selector(getSelectorString() + ":not(:focus)");
    }

    /**
     * Pseudo-class :not(:active)
     */
    default Selector notActive() {
        return Selector.selector(getSelectorString() + ":not(:active)");
    }

    /**
     * Pseudo-class :not(:disabled)
     */
    default Selector notDisabled() {
        return Selector.selector(getSelectorString() + ":not(:disabled)");
    }

    /**
     * Pseudo-class :not(:enabled)
     */
    default Selector notEnabled() {
        return Selector.selector(getSelectorString() + ":not(:enabled)");
    }

    /**
     * Pseudo-class :not(:checked)
     */
    default Selector notChecked() {
        return Selector.selector(getSelectorString() + ":not(:checked)");
    }

    /**
     * Pseudo-class :not(:required)
     */
    default Selector notRequired() {
        return Selector.selector(getSelectorString() + ":not(:required)");
    }

    /**
     * Pseudo-class :not(:optional)
     */
    default Selector notOptional() {
        return Selector.selector(getSelectorString() + ":not(:optional)");
    }

    /**
     * Pseudo-class :not(:valid)
     */
    default Selector notValid() {
        return Selector.selector(getSelectorString() + ":not(:valid)");
    }

    /**
     * Pseudo-class :not(:invalid)
     */
    default Selector notInvalid() {
        return Selector.selector(getSelectorString() + ":not(:invalid)");
    }

    /**
     * Pseudo-class :not(:read-only)
     */
    default Selector notReadOnly() {
        return Selector.selector(getSelectorString() + ":not(:read-only)");
    }

    /**
     * Pseudo-class :not(:read-write)
     */
    default Selector notReadWrite() {
        return Selector.selector(getSelectorString() + ":not(:read-write)");
    }

    /**
     * Pseudo-class :not(:empty)
     */
    default Selector notEmpty() {
        return Selector.selector(getSelectorString() + ":not(:empty)");
    }
}
