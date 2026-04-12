package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import luvx.DelegatedCharSeq;

/**
 * Represents an immutable CSS rule, containing a selector and a set of properties.
 * e.g., ".container { color: blue; font-size: 16px; }"
 */
public non-sealed class CssRule implements CssRuleFrag {

    private final CharSequence selector;
    private final CssPropertyFrag[] properties;

    public CssRule(CharSequence selector, CssPropertyFrag... properties) {
        this.selector = selector;
        this.properties = properties;
    }
    
  /**
     * Creates a new CSS rule.
     * @param selector The CSS selector.
     * @param properties The CSS properties for this rule.
     */
    public static CssRule rule(CharSequence selector, CssPropertyFrag... properties) {
        return new CssRule(selector, properties);
    }   

    /**
     * Renders the rule into a formatted CSS string with indentation.
     */


    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append(selector).append(" {\n");
        String propertiesStr = Arrays.stream(properties)
                .map(CssPropertyFrag::toString)
                .collect(Collectors.joining("\n"));
        // Indent properties for readability
        for (String line : propertiesStr.split("\n")) {
            sb.append("    ").append(line).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}