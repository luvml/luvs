package luvs;

import luvx.NamedEnumCharSeq;

/**
 * Marker interface for CSS class name enums.
 * Enums implementing this become reusable, type-safe CSS class names.
 * Define just the names in enum, define rules separately using .____ or chaining.
 */
public interface CssClass extends NamedEnumCharSeq,
                                   AttributeSelector,
                                   PseudoClassMixin,
                                   NegatedPseudoClassMixin,
                                   PseudoElementMixin,
                                   CombinatorMixin {

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

    /**
     * Returns the CSS selector string for attribute selectors.
     * For CssClass, this is the selector with the dot prefix.
     */
    @Override
    default CharSequence getSelectorString() {
        return getSelector();
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
     * Compound selector: .class1.class2 (no space)
     * Usage: primary.and(active).hover() → ".primary.active:hover"
     */
    default Selector and(CssClass other) {
        return Selector.selector(getSelector() + other.getSelector());
    }

    /**
     * Compound selector with multiple classes: .class1.class2.class3 (no spaces)
     * Usage: button.and(primary, active, large) → ".button.primary.active.large"
     */
    default Selector and(CssClass... others) {
        StringBuilder sb = new StringBuilder(getSelector());
        for (CssClass cls : others) {
            sb.append(cls.getSelector());
        }
        return Selector.selector(sb.toString());
    }

    /**
     * Quick rule creation: .className { props }
     * Usage: center.____(color("red"), ...)
     */
    default CssRule ____(CssProperty... properties) {
        return new CssRule(getSelector(), properties);
    }
}
