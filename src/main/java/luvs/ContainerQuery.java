package luvs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a CSS {@code @container} rule for container queries.
 *
 * <p>Usage:
 * <pre>
 * import static luvs.ContainerQuery.*;
 *
 * // Simple container query
 * var responsiveCard = container(minWidth(px(400)),
 *     card.____(display(GRID), grid_template_columns(fr(1), fr(1)))
 * );
 *
 * // Named container
 * var namedContainer = container("sidebar", minWidth(px(300)),
 *     nav.____(display(BLOCK))
 * );
 *
 * // Combined conditions
 * var rangeQuery = container(minWidth(px(400)).and(maxWidth(px(800))),
 *     card.____(padding(rem(2)))
 * );
 * </pre>
 *
 * <p>Note: Elements must use {@code container-type: inline-size | size} CSS property
 * to become query containers.
 */
public non-sealed class ContainerQuery implements CssRuleFrag {

    private final String containerName; // null for unnamed
    private final ContainerCondition condition;
    private final CssRule[] rules;

    private ContainerQuery(String containerName, ContainerCondition condition, CssRule[] rules) {
        this.containerName = containerName;
        this.condition = condition;
        this.rules = rules;
    }

    /**
     * Creates a {@code @container} rule without a container name.
     * Queries the nearest ancestor container.
     */
    public static ContainerQuery container(ContainerCondition condition, CssRuleFrag... content) {
        return createContainer(null, condition, content);
    }

    /**
     * Creates a {@code @container} rule with a specific container name.
     * Queries the nearest ancestor container with matching name.
     */
    public static ContainerQuery container(String containerName, ContainerCondition condition, CssRuleFrag... content) {
        return createContainer(containerName, condition, content);
    }

    private static ContainerQuery createContainer(String name, ContainerCondition condition, CssRuleFrag[] content) {
        List<CssRule> rulesList = new ArrayList<>();
        flattenContent(content, rulesList);
        return new ContainerQuery(name, condition, rulesList.toArray(CssRule[]::new));
    }

    private static void flattenContent(CssRuleFrag[] fragments, List<CssRule> out) {
        for (CssRuleFrag frag : fragments) {
            switch (frag) {
                case CssRule rule -> out.add(rule);
                case CssRules cssRules -> Collections.addAll(out, cssRules.getRules());
                case ContainerQuery ignored -> throw new IllegalArgumentException(
                    "@container cannot be nested inside other @container");
                case MediaQuery ignored -> throw new IllegalArgumentException(
                    "@media cannot be nested inside @container");
                case Supports ignored -> throw new IllegalArgumentException(
                    "@supports cannot be nested inside @container");
                case FontFace ignored -> throw new IllegalArgumentException(
                    "@font-face cannot be nested inside @container");
                case Layer ignored -> throw new IllegalArgumentException(
                    "@layer cannot be nested inside @container");
                case Layer.LayerOrder ignored -> throw new IllegalArgumentException(
                    "@layer ordering cannot be nested inside @container");
                case CssComment ignored -> {}
                case CssEmptyLine ignored -> {}
            }
        }
    }

    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append("@container ");
        if (containerName != null) {
            sb.append(containerName).append(" ");
        }
        sb.append(condition).append(" {\n");

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
     * Represents a container query condition.
     */
    public static class ContainerCondition {
        private final String condition;

        private ContainerCondition(String condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return condition;
        }

        public ContainerCondition and(ContainerCondition other) {
            return new ContainerCondition("(" + this.condition + ") and (" + other.condition + ")");
        }

        public ContainerCondition or(ContainerCondition other) {
            return new ContainerCondition("(" + this.condition + ") or (" + other.condition + ")");
        }

        public static ContainerCondition not(ContainerCondition condition) {
            return new ContainerCondition("not (" + condition.condition + ")");
        }
    }

    // ========== Condition Factory Methods ==========

    public static ContainerCondition minWidth(CharSequence value) {
        return new ContainerCondition("(min-width: " + value + ")");
    }

    public static ContainerCondition maxWidth(CharSequence value) {
        return new ContainerCondition("(max-width: " + value + ")");
    }

    public static ContainerCondition minHeight(CharSequence value) {
        return new ContainerCondition("(min-height: " + value + ")");
    }

    public static ContainerCondition maxHeight(CharSequence value) {
        return new ContainerCondition("(max-height: " + value + ")");
    }

    public static ContainerCondition minInlineSize(CharSequence value) {
        return new ContainerCondition("(min-inline-size: " + value + ")");
    }

    public static ContainerCondition maxInlineSize(CharSequence value) {
        return new ContainerCondition("(max-inline-size: " + value + ")");
    }

    public static ContainerCondition minBlockSize(CharSequence value) {
        return new ContainerCondition("(min-block-size: " + value + ")");
    }

    public static ContainerCondition maxBlockSize(CharSequence value) {
        return new ContainerCondition("(max-block-size: " + value + ")");
    }

    public static ContainerCondition aspectRatio(String ratio) {
        return new ContainerCondition("(aspect-ratio: " + ratio + ")");
    }

    public static ContainerCondition orientation(CharSequence value) {
        return new ContainerCondition("(orientation: " + value + ")");
    }

    /**
     * Raw condition string (escape hatch).
     */
    public static ContainerCondition condition(String rawCondition) {
        return new ContainerCondition(rawCondition);
    }
}
