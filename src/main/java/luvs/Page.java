package luvs;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CSS @page rule for print stylesheets.
 * 
 * Examples:
 * <pre>
 * // Default page styles
 * Page.page(
 *     size("A4 portrait"),
 *     margin(cm(2))
 * )
 * 
 * // Left/right pages
 * Page.leftPage(margin_left(cm(3)))
 * Page.rightPage(margin_right(cm(3)))
 * 
 * // First page
 * Page.firstPage(margin_top(cm(5)))
 * 
 * // Named page
 * Page.namedPage("chapter",
 *     size("A4 landscape")
 * )
 * </pre>
 */
public non-sealed class Page implements CssRuleFrag {

    private final String selector;  // null, ":left", ":right", ":first", ":blank", or "name"
    private final CssProperty[] properties;

    private Page(String selector, CssProperty... properties) {
        this.selector = selector;
        this.properties = properties;
    }

    // Factory methods for common page selectors
    
    /** Creates a default @page rule (applies to all pages) */
    public static Page page(CssProperty... properties) {
        return new Page(null, properties);
    }
    
    /** Creates a @page :left rule (left pages in double-sided printing) */
    public static Page leftPage(CssProperty... properties) {
        return new Page(":left", properties);
    }
    
    /** Creates a @page :right rule (right pages in double-sided printing) */
    public static Page rightPage(CssProperty... properties) {
        return new Page(":right", properties);
    }
    
    /** Creates a @page :first rule (first page) */
    public static Page firstPage(CssProperty... properties) {
        return new Page(":first", properties);
    }
    
    /** Creates a @page :blank rule (blank pages) */
    public static Page blankPage(CssProperty... properties) {
        return new Page(":blank", properties);
    }
    
    /** Creates a named @page rule */
    public static Page namedPage(String name, CssProperty... properties) {
        return new Page(name, properties);
    }

    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append("@page");

        if (selector != null && !selector.isEmpty()) {
            if (selector.startsWith(":")) {
                sb.append(selector);  // Pseudo-class
            } else {
                sb.append(" ").append(selector);  // Named page
            }
        }

        sb.append(" {\n");
        for (CssProperty prop : properties) {
            sb.append("    ").append(prop.delegatedCharSeqVal()).append("\n");
        }
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}
