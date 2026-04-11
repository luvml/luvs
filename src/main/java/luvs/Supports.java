package luvs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a CSS {@code @supports} rule for feature queries.
 *
 * <p>Usage:
 * <pre>
 * import static luvs.Supports.*;
 *
 * var gridSupport = supports(property("display", "grid"),
 *     container.____(display(GRID))
 * );
 *
 * var backdropSupport = supports(property("backdrop-filter", "blur(10px)"),
 *     modal.____(backdrop_filter(blur(px(10))))
 * );
 *
 * // Logical operators
 * var combined = supports(
 *     property("display", "flex").and(property("gap", "1rem")),
 *     container.____(display(FLEX), gap(rem(1)))
 * );
 * </pre>
 */
public non-sealed class Supports implements CssRuleFrag {

    private final SupportsCondition condition;
    private final CssRule[] rules;

    private Supports(SupportsCondition condition, CssRule[] rules) {
        this.condition = condition;
        this.rules = rules;
    }

    /**
     * Creates a CSS {@code @supports} rule.
     *
     * @param condition The feature query condition.
     * @param content   CSS rule fragments to include. Accepts {@link CssRule} and {@link CssRules}.
     */
    public static Supports supports(SupportsCondition condition, CssRuleFrag... content) {
        List<CssRule> rulesList = new ArrayList<>();
        flattenContent(content, rulesList);
        return new Supports(condition, rulesList.toArray(CssRule[]::new));
    }

    private static void flattenContent(CssRuleFrag[] fragments, List<CssRule> out) {
        for (CssRuleFrag frag : fragments) {
            switch (frag) {
                case CssRule rule -> out.add(rule);
                case CssRules cssRules -> Collections.addAll(out, cssRules.getRules());
                case Supports ignored -> throw new IllegalArgumentException(
                    "@supports cannot be nested inside other @supports");
                case MediaQuery ignored -> throw new IllegalArgumentException(
                    "@media cannot be nested inside @supports");
                case ContainerQuery ignored -> throw new IllegalArgumentException(
                    "@container cannot be nested inside @supports");
                case FontFace ignored -> throw new IllegalArgumentException(
                    "@font-face cannot be nested inside @supports");
                case Layer ignored -> throw new IllegalArgumentException(
                    "@layer cannot be nested inside @supports");
                case Layer.LayerOrder ignored -> throw new IllegalArgumentException(
                    "@layer ordering cannot be nested inside @supports");
                case CssComment ignored -> {} // comments not preserved
                case CssEmptyLine ignored -> {} // empty lines not preserved
            }
        }
    }

    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append("@supports ").append(condition).append(" {\n");

        for (CssRule rule : rules) {
            String[] lines = rule.toString().split("\n");
            for (String line : lines) {
                sb.append("    ").append(line).append("\n");
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Represents a @supports condition.
     */
    public static class SupportsCondition {
        private final String condition;

        private SupportsCondition(String condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return condition;
        }

        /**
         * AND operator: condition1 and condition2
         */
        public SupportsCondition and(SupportsCondition other) {
            return new SupportsCondition("(" + this.condition + ") and (" + other.condition + ")");
        }

        /**
         * OR operator: condition1 or condition2
         */
        public SupportsCondition or(SupportsCondition other) {
            return new SupportsCondition("(" + this.condition + ") or (" + other.condition + ")");
        }

        /**
         * NOT operator: not condition
         */
        public static SupportsCondition not(SupportsCondition condition) {
            return new SupportsCondition("not (" + condition.condition + ")");
        }
    }

    // ========== Condition Factory Methods ==========

    /**
     * Property-value feature query.
     * Example: property("display", "grid")
     * Produces: (display: grid)
     */
    public static SupportsCondition property(String propertyName, CharSequence value) {
        return new SupportsCondition("(" + propertyName + ": " + value + ")");
    }

    /**
     * Selector feature query.
     * Example: selector(":has(> img)")
     * Produces: selector(:has(> img))
     */
    public static SupportsCondition selector(String selectorSyntax) {
        return new SupportsCondition("selector(" + selectorSyntax + ")");
    }

    /**
     * Raw condition string (escape hatch).
     * Example: condition("(display: flex) and (gap: 1rem)")
     */
    public static SupportsCondition condition(String rawCondition) {
        return new SupportsCondition(rawCondition);
    }
}
