package luvs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a CSS {@code @media} rule containing CSS rules.
 *
 * <p>Usage:
 * <pre>
 * import static luvs.MediaQuery.*;
 * import static luvs.MediaCondition.*;
 *
 * var tablet = media(minWidth(px(768)),
 *     container.____(padding(rem(2))),
 *     sidebar.____(display(BLOCK))
 * );
 *
 * var dark = media(prefersColorScheme(DARK),
 *     body.____(background_color("#1a1a1a"), color("#eee"))
 * );
 *
 * var responsive = media(minWidth(px(768)).and(maxWidth(px(1200))),
 *     container.____(max_width(px(1200)))
 * );
 * </pre>
 *
 * <p>Nesting media queries inside media queries is prevented at runtime.
 */
public non-sealed class MediaQuery implements CssRuleFrag {

    private final MediaCondition condition;
    private final CssRule[] rules;

    private MediaQuery(MediaCondition condition, CssRule[] rules) {
        this.condition = condition;
        this.rules = rules;
    }

    /**
     * Creates a CSS {@code @media} rule.
     *
     * @param condition The media condition (use {@link MediaCondition} factories).
     * @param content   CSS rule fragments to include. Accepts {@link CssRule} and {@link CssRules}.
     *                  Nested {@link MediaQuery} will throw {@link IllegalArgumentException}.
     */
    public static MediaQuery media(MediaCondition condition, CssRuleFrag... content) {
        List<CssRule> rulesList = new ArrayList<>();
        flattenContent(content, rulesList);
        return new MediaQuery(condition, rulesList.toArray(CssRule[]::new));
    }

    private static void flattenContent(CssRuleFrag[] fragments, List<CssRule> out) {
        for (CssRuleFrag frag : fragments) {
            switch (frag) {
                case CssRule rule -> out.add(rule);
                case CssRules cssRules -> Collections.addAll(out, cssRules.getRules());
                case MediaQuery mq -> throw new IllegalArgumentException(
                    "Media queries cannot be nested inside other media queries");
                case FontFace ignored -> throw new IllegalArgumentException(
                    "@font-face cannot be nested inside @media queries");
                case Supports ignored -> throw new IllegalArgumentException(
                    "@supports cannot be nested inside @media queries");
                case ContainerQuery ignored -> throw new IllegalArgumentException(
                    "@container cannot be nested inside @media queries");
                case Layer ignored -> throw new IllegalArgumentException(
                    "@layer cannot be nested inside @media queries");
                case Layer.LayerOrder ignored -> throw new IllegalArgumentException(
                    "@layer ordering cannot be nested inside @media queries");
                case CssComment ignored -> {} // comments not preserved inside media queries
                case CssEmptyLine ignored -> {} // empty lines not preserved inside media queries
            }
        }
    }

    public MediaCondition getCondition() {
        return condition;
    }

    CssRule[] getRules() {
        return rules;
    }

    @Override
    public String delegatedCharSeqVal() {
        StringBuilder sb = new StringBuilder();
        sb.append("@media ").append(condition).append(" {\n");

        for (int i = 0; i < rules.length; i++) {
            if (i > 0) {
                sb.append("\n");
            }
            String ruleStr = rules[i].delegatedCharSeqVal();
            for (String line : ruleStr.split("\n")) {
                sb.append("    ").append(line).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}
