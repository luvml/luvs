package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import luvml.HtmlAttribute;

/**
 * Main entry point for the CSS DSL.
 * Provides factory methods for creating CSS rules and styles.
 */
public final class Css {

    private Css() {} // Utility class

    /**
     * Creates a new CSS rule.
     * @param selector The CSS selector.
     * @param properties The CSS properties for this rule.
     */
    public static CssRule rule(CharSequence selector, CssProperty... properties) {
        return new CssRule(selector, properties);
    }

    /**
     * Creates a Style from CSS rules.
     * @param rules The CSS rules to include.
     */
    public static Style style(CssRule... rules) {
        return new Style(rules);
    }

    /**
     * Creates an luvml HtmlAttribute for inline styles.
     * @param properties The CSS properties to apply inline.
     */
    public static HtmlAttribute styleAttr(CssProperty... properties) {
        String styleValue = Arrays.stream(properties)
            .map(CssProperty::toString)
            .collect(Collectors.joining(" "));
        return new HtmlAttribute("style", styleValue);
    }
}
