package luvs;

/**
 * Composable interface providing CSS combinator methods.
 * Can be mixed into HtmlTag, CssClass, Selector, or any other selector-like type.
 *
 * Implementing classes must provide getSelectorString() which returns the base selector.
 */
public interface CombinatorMixin {

    /**
     * Returns the base selector string for this element.
     */
    CharSequence getSelectorString();

    /**
     * Child combinator: current > child
     * Usage: container.child(div)
     * Usage: container.child(div, highlight) for .container > div.highlight
     */
    default Selector child(CharSequence... childElements) {
        CharSequence[] parts = new CharSequence[childElements.length + 2];
        parts[0] = getSelectorString();
        parts[1] = ">";
        System.arraycopy(childElements, 0, parts, 2, childElements.length);
        return Selector.selector(parts);
    }

    /**
     * Descendant combinator: current descendant
     */
    default Selector descendant(CharSequence descendantElement) {
        return Selector.selector(getSelectorString(), descendantElement);
    }

    /**
     * Adjacent sibling combinator: current + sibling
     */
    default Selector adjacent(CharSequence siblingElement) {
        return Selector.selector(getSelectorString(), "+", siblingElement);
    }

    /**
     * General sibling combinator: current ~ sibling
     */
    default Selector sibling(CharSequence siblingElement) {
        return Selector.selector(getSelectorString(), "~", siblingElement);
    }
}
