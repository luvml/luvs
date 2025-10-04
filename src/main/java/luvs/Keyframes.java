package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a CSS @keyframes rule.
 *
 * Usage:
 * <pre>
 * var fadeIn = keyframes("fadeIn",
 *     frame("0%", opacity(0)),
 *     frame("100%", opacity(1))
 * );
 * </pre>
 */
public class Keyframes {
    private final String name;
    private final KeyframeStep[] steps;

    public Keyframes(String name, KeyframeStep... steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String stepsStr = Arrays.stream(steps)
            .map(KeyframeStep::toString)
            .collect(Collectors.joining("\n  "));
        return "@keyframes " + name + " {\n  " + stepsStr + "\n}";
    }

    /**
     * Represents a single keyframe step.
     */
    public static class KeyframeStep {
        private final String selector; // "0%", "50%", "from", "to"
        private final CssProperty[] properties;

        public KeyframeStep(String selector, CssProperty... properties) {
            this.selector = selector;
            this.properties = properties;
        }

        @Override
        public String toString() {
            String propsStr = Arrays.stream(properties)
                .map(CssProperty::toString)
                .collect(Collectors.joining(" "));
            return selector + " { " + propsStr + " }";
        }
    }

    // Factory methods
    public static KeyframeStep frame(String selector, CssProperty... properties) {
        return new KeyframeStep(selector, properties);
    }

    public static KeyframeStep from(CssProperty... properties) {
        return new KeyframeStep("from", properties);
    }

    public static KeyframeStep to(CssProperty... properties) {
        return new KeyframeStep("to", properties);
    }

    public static Keyframes keyframes(String name, KeyframeStep... steps) {
        return new Keyframes(name, steps);
    }
}
