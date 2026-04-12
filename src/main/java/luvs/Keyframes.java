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
public class Keyframes implements CharSequence {
    private final String name;
    private final KeyframeStep[] steps;

    public Keyframes(String name, KeyframeStep... steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the @keyframes CSS block.
     */
    @Override
    public String toString() {
        String stepsStr = Arrays.stream(steps)
            .map(KeyframeStep::toString)
            .collect(Collectors.joining("\n  "));
        return "@keyframes " + name + " {\n  " + stepsStr + "\n}";
    }

    // ========== CharSequence Implementation ==========
    // Delegates to name so Keyframes can be used directly in animation() etc.

    @Override
    public int length() {
        return name.length();
    }

    @Override
    public char charAt(int index) {
        return name.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return name.subSequence(start, end);
    }

    /**
     * Represents a single keyframe step.
     */
    public static class KeyframeStep {
        private final String selector; // "0%", "50%", "from", "to"
        private final CssPropertyFrag[] properties;

        public KeyframeStep(String selector, CssPropertyFrag... properties) {
            this.selector = selector;
            this.properties = properties;
        }

        @Override
        public String toString() {
            String propsStr = Arrays.stream(properties)
                .map(CssPropertyFrag::toString)
                .collect(Collectors.joining(" "));
            return selector + " { " + propsStr + " }";
        }
    }

    // Factory methods

    /**
     * Keyframe step at a percentage.
     * Usage: frame(50, transform(translateY(px(-20))))
     * @param percent 0-100
     */
    public static KeyframeStep frame(int percent, CssPropertyFrag... properties) {
        return new KeyframeStep(percent + "%", properties);
    }

    /**
     * Keyframe step with string selector (e.g., "0%, 100%" for combined steps).
     */
    public static KeyframeStep frame(String selector, CssPropertyFrag... properties) {
        return new KeyframeStep(selector, properties);
    }

    public static KeyframeStep from(CssPropertyFrag... properties) {
        return new KeyframeStep("from", properties);
    }

    public static KeyframeStep to(CssPropertyFrag... properties) {
        return new KeyframeStep("to", properties);
    }

    public static Keyframes keyframes(String name, KeyframeStep... steps) {
        return new Keyframes(name, steps);
    }
}
