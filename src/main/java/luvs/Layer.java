package luvs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents CSS {@code @layer} for cascade layers.
 *
 * <p>Usage:
 * <pre>
 * import static luvs.Layer.*;
 *
 * // Anonymous layer
 * var resetLayer = layer(
 *     $all.____(margin(ZERO), padding(ZERO))
 * );
 *
 * // Named layer
 * var baseLayer = layer("base",
 *     body.____(font_family("system-ui"), line_height(1.6))
 * );
 *
 * // Nested layers
 * var themeLayer = layer("theme",
 *     layer("light", ...),
 *     layer("dark", ...)
 * );
 * </pre>
 *
 * <p>Layer ordering can be declared with {@code layerOrder()}:
 * <pre>
 * var ordering = layerOrder("reset", "base", "components", "utilities");
 * </pre>
 */
public non-sealed class Layer implements CssRuleFrag {

    private final String name; // null for anonymous
    private final Object[] content; // CssRule, CssRules, or nested Layer

    private Layer(String name, Object[] content) {
        this.name = name;
        this.content = content;
    }

    /**
     * Creates an anonymous {@code @layer}.
     */
    public static Layer layer(CssRuleFrag... rules) {
        return createLayer(null, rules);
    }

    /**
     * Creates a named {@code @layer}.
     */
    public static Layer layer(String name, CssRuleFrag... rules) {
        return createLayer(name, rules);
    }

    private static Layer createLayer(String name, CssRuleFrag[] rules) {
        List<Object> contentList = new ArrayList<>();
        flattenContent(rules, contentList);
        return new Layer(name, contentList.toArray());
    }

    private static void flattenContent(CssRuleFrag[] fragments, List<Object> out) {
        for (CssRuleFrag frag : fragments) {
            switch (frag) {
                case CssRule rule -> out.add(rule);
                case CssRules cssRules -> Collections.addAll(out, (Object[]) cssRules.getRules());
                case Layer layer -> out.add(layer); // nested layers allowed
                case Layer.LayerOrder ignored -> throw new IllegalArgumentException(
                    "@layer ordering cannot be nested inside @layer");
                case MediaQuery ignored -> throw new IllegalArgumentException(
                    "@media cannot be nested inside @layer");
                case ContainerQuery ignored -> throw new IllegalArgumentException(
                    "@container cannot be nested inside @layer");
                case Supports ignored -> throw new IllegalArgumentException(
                    "@supports cannot be nested inside @layer");
                case FontFace ignored -> throw new IllegalArgumentException(
                    "@font-face cannot be nested inside @layer");
                case CssComment ignored -> {}
                case CssEmptyLine ignored -> {}
            }
        }
    }

    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append("@layer");
        if (name != null) {
            sb.append(" ").append(name);
        }
        sb.append(" {\n");

        for (Object item : content) {
            String[] lines = item.toString().split("\n");
            for (String line : lines) {
                sb.append("    ").append(line).append("\n");
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Declares the order of cascade layers.
     * Should appear before any layer definitions.
     *
     * Example: layerOrder("reset", "base", "components")
     * Produces: {@code @layer reset, base, components;}
     */
    public static LayerOrder layerOrder(String... layerNames) {
        return new LayerOrder(layerNames);
    }

    /**
     * Represents a {@code @layer} ordering declaration.
     */
    public static non-sealed class LayerOrder implements CssRuleFrag {
        private final String[] layerNames;

        private LayerOrder(String[] layerNames) {
            this.layerNames = layerNames;
        }

        @Override
        public String delegatedCharSeqVal() {
            return "@layer " + String.join(", ", layerNames) + ";";
        }
    }
}
