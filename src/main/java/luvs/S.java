package luvs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Static factory for creating CSS Selectors (S).
 */
public final class S {

    private S() {} // Utility class

    // --- Basic Selectors ---
    /*public static String starSelector() { return "*"; }
    public static String id(String idName) { return "#" + idName.trim(); }*/
    
    public static String tag(String tagName) { return tagName; }
    public static String cls(String className) { return "." + className.trim(); }
    
    
    // --- Attribute Selectors ---
    public static String attr(String attributeName) { return "[" + attributeName + "]"; }
    public static String attr_val(String attributeName, String value) { return "[" + attributeName + "=\"" + value + "\"]"; }
    public static String attr_contains(String attributeName, String value) { return "[" + attributeName + "*=\"" + value + "\"]"; }
    public static String attr_starts_with(String attributeName, String value) { return "[" + attributeName + "^=\"" + value + "\"]"; }
    public static String attr_ends_with(String attributeName, String value) { return "[" + attributeName + "$=\"" + value + "\"]"; }

    // --- Combinators ---
    private static String join(String delimiter, CharSequence... parts) {
        return Stream.of(parts).map(CharSequence::toString).collect(Collectors.joining(delimiter));
    }

    public static String descendant(CharSequence... selectors) { return join(" ", selectors); }
    public static String child(CharSequence... selectors) { return join(" > ", selectors); }
    public static String adjacent(CharSequence... selectors) { return join(" + ", selectors); }
    public static String sibling(CharSequence... selectors) { return join(" ~ ", selectors); }
    public static String grouping(CharSequence... selectors) { return join(", ", selectors); }

    /**
     * Compound selector: combines selectors without space, returns a Selector for chaining.
     * Usage: compound(div, active).hover() → "div.active:hover"
     * Usage: compound(button, primary, disabled).__(...) → "button.primary.disabled { ... }"
     *
     * Handles CssClass instances by using their selector form (with dot prefix).
     * HtmlTag and other CharSequence types are used as-is.
     *
     * Returns a Selector so you can chain pseudo-classes, attribute selectors, etc.
     */
    public static Selector compound(CharSequence... selectors) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence sel : selectors) {
            if (sel instanceof CssClass) {
                sb.append(((CssClass) sel).getSelector());
            } else {
                sb.append(sel.toString());
            }
        }
        return Selector.selector(sb.toString());
    }
    
    // --- Pseudo-classes ---
    public static String hover(CharSequence selector) { return selector + ":hover"; }
    public static String focus(CharSequence selector) { return selector + ":focus"; }
    public static String active(CharSequence selector) { return selector + ":active"; }
    public static String first_child(CharSequence selector) { return selector + ":first-child"; }
    public static String last_child(CharSequence selector) { return selector + ":last-child"; }
    public static String nth_child(CharSequence selector, String n) { return selector + ":nth-child(" + n + ")"; }
    public static String not(CharSequence selector, CharSequence negation) { return selector + ":not(" + negation + ")"; }
    
    // --- Pseudo-elements ---
    public static String before(CharSequence selector) { return selector + "::before"; }
    public static String after(CharSequence selector) { return selector + "::after"; }
}