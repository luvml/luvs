package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import luvx.DelegatedCharSeq;

/**
 * Represents an immutable CSS rule, containing a selector and a set of properties.
 * e.g., ".container { color: blue; font-size: 16px; }"
 */
public class CssRule implements DelegatedCharSeq {

    private final CharSequence selector;
    private final CssProperty[] properties;

    public CssRule(CharSequence selector, CssProperty... properties) {
        this.selector = selector;
        this.properties = properties;
    }

    /**
     * Renders the rule into a formatted CSS string with indentation.
     */
    
    
    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append(selector).append(" {\n");
        String propertiesStr = Arrays.stream(properties)
                .map(CssProperty::toString)
                .collect(Collectors.joining("\n"));
        // Indent properties for readability
        for (String line : propertiesStr.split("\n")) {
            sb.append("    ").append(line).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}