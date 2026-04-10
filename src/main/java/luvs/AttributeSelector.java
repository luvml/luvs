package luvs;

/**
 * Composable interface providing attribute selector methods.
 * Can be mixed into HtmlTag, CssClass, Selector, or any other selector-like type.
 *
 * Implementing classes must provide getSelectorString() which returns the base selector.
 */
public interface AttributeSelector {

    /**
     * Returns the base selector string for this element.
     * - HtmlTag: returns the tag name (e.g., "div", "input")
     * - CssClass: returns .className (e.g., ".container")
     * - Selector: returns the built selector (e.g., ".container > div")
     */
    CharSequence getSelectorString();

    // ========== Attribute Selectors ==========

    /**
     * Attribute selector [attr]
     * Usage: input.withAttr("disabled") → input[disabled]
     */
    default Selector withAttr(CharSequence attr) {
        return Selector.selector(getSelectorString() + "[" + attr + "]");
    }

    /**
     * Attribute selector [attr="value"]
     * Usage: input.withAttr("type", "checkbox") → input[type="checkbox"]
     */
    default Selector withAttr(CharSequence attr, CharSequence value) {
        return Selector.selector(getSelectorString() + "[" + attr + "=\"" + value + "\"]");
    }

    /**
     * Shorthand for withAttr(attr)
     * Usage: input.__("disabled") → input[disabled]
     */
    default Selector __(CharSequence attr) {
        return withAttr(attr);
    }

    /**
     * Shorthand for withAttr(attr, value)
     * Usage: input.__("type", "checkbox") → input[type="checkbox"]
     */
    default Selector __(CharSequence attr, CharSequence value) {
        return withAttr(attr, value);
    }

    /**
     * Shorthand for type attribute selector
     * Usage: input.__type("checkbox") → input[type="checkbox"]
     */
    default Selector __type(CharSequence value) {
        return withAttr("type", value);
    }
    
    default Selector __data(String dataVarName, CharSequence value) {
        return withAttr("data-"+dataVarName, value);
    }

    // ========== Additional Attribute Selector Variants ==========

    /**
     * Attribute selector [attr~="value"] - contains word
     * Usage: div.attrContains("class", "active") → div[class~="active"]
     */
    default Selector attrContains(CharSequence attr, CharSequence value) {
        return Selector.selector(getSelectorString() + "[" + attr + "~=\"" + value + "\"]");
    }

    /**
     * Attribute selector [attr^="value"] - starts with
     * Usage: a.attrStartsWith("href", "https") → a[href^="https"]
     */
    default Selector attrStartsWith(CharSequence attr, CharSequence value) {
        return Selector.selector(getSelectorString() + "[" + attr + "^=\"" + value + "\"]");
    }

    /**
     * Attribute selector [attr$="value"] - ends with
     * Usage: a.attrEndsWith("href", ".pdf") → a[href$=".pdf"]
     */
    default Selector attrEndsWith(CharSequence attr, CharSequence value) {
        return Selector.selector(getSelectorString() + "[" + attr + "$=\"" + value + "\"]");
    }

    /**
     * Attribute selector [attr*="value"] - contains substring
     * Usage: a.attrSubstring("href", "example") → a[href*="example"]
     */
    default Selector attrSubstring(CharSequence attr, CharSequence value) {
        return Selector.selector(getSelectorString() + "[" + attr + "*=\"" + value + "\"]");
    }

    /**
     * Attribute selector [attr|="value"] - starts with value or value-
     * Usage: div.attrDashMatch("lang", "en") → div[lang|="en"]
     */
    default Selector attrDashMatch(CharSequence attr, CharSequence value) {
        return Selector.selector(getSelectorString() + "[" + attr + "|=\"" + value + "\"]");
    }
}
