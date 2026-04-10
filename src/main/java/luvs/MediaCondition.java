package luvs;

import luvx.DelegatedCharSeq;

/**
 * Represents a CSS media query condition.
 * Supports composable conditions via {@link #and} and {@link #or}.
 *
 * <p>Usage (via static imports from {@link MediaQuery}):
 * <pre>
 * minWidth(px(768))
 * minWidth(px(768)).and(maxWidth(px(1200)))
 * screen().and(minWidth(px(768)))
 * prefersColorScheme(DARK)
 * not(print())
 * </pre>
 */
public final class MediaCondition implements DelegatedCharSeq {

    private final String value;

    MediaCondition(String value) {
        this.value = value;
    }

    // ========== Combinators ==========

    /**
     * Combines this condition with another using AND.
     * e.g., {@code minWidth(px(768)).and(maxWidth(px(1200)))}
     * → {@code (min-width: 768px) and (max-width: 1200px)}
     */
    public MediaCondition and(MediaCondition other) {
        return new MediaCondition(this.value + " and " + other.value);
    }

    /**
     * Combines this condition with another using OR (comma in CSS).
     * e.g., {@code screen().or(print())} → {@code screen, print}
     */
    public MediaCondition or(MediaCondition other) {
        return new MediaCondition(this.value + ", " + other.value);
    }

    // ========== Static Factories: Media Types ==========

    public static MediaCondition screen() {
        return new MediaCondition("screen");
    }

    public static MediaCondition print() {
        return new MediaCondition("print");
    }

    public static MediaCondition all() {
        return new MediaCondition("all");
    }

    // ========== Static Factories: Dimension Features ==========

    public static MediaCondition minWidth(CharSequence value) {
        return feature("min-width", value);
    }

    public static MediaCondition maxWidth(CharSequence value) {
        return feature("max-width", value);
    }

    public static MediaCondition minHeight(CharSequence value) {
        return feature("min-height", value);
    }

    public static MediaCondition maxHeight(CharSequence value) {
        return feature("max-height", value);
    }

    // ========== Static Factories: Preference Features ==========

    /** e.g., {@code prefersColorScheme(DARK)} → {@code (prefers-color-scheme: dark)} */
    public static MediaCondition prefersColorScheme(String scheme) {
        return new MediaCondition("(prefers-color-scheme: " + scheme + ")");
    }

    /** {@code (prefers-reduced-motion: reduce)} */
    public static MediaCondition prefersReducedMotion() {
        return new MediaCondition("(prefers-reduced-motion: reduce)");
    }

    /** {@code (prefers-contrast: more)} etc. */
    public static MediaCondition prefersContrast(String value) {
        return new MediaCondition("(prefers-contrast: " + value + ")");
    }

    // ========== Static Factories: Display Features ==========

    /** e.g., {@code orientation(PORTRAIT)} → {@code (orientation: portrait)} */
    public static MediaCondition orientation(String value) {
        return new MediaCondition("(orientation: " + value + ")");
    }

    // ========== Static Factories: Logical ==========

    /** Negates a condition. e.g., {@code not(print())} → {@code not print} */
    public static MediaCondition not(MediaCondition condition) {
        return new MediaCondition("not " + condition.value);
    }

    // ========== Escape Hatch ==========

    /** Raw condition string for anything not covered by the DSL. */
    public static MediaCondition condition(String rawCondition) {
        return new MediaCondition(rawCondition);
    }

    // ========== Generic Feature Builder ==========

    /** Builds {@code (feature-name: value)}. */
    public static MediaCondition feature(String name, CharSequence value) {
        return new MediaCondition("(" + name + ": " + toStr(value) + ")");
    }

    // ========== Constants ==========

    public static final String DARK = "dark";
    public static final String LIGHT = "light";
    public static final String PORTRAIT = "portrait";
    public static final String LANDSCAPE = "landscape";

    // ========== Internal ==========

    private static String toStr(CharSequence value) {
        return (value instanceof DelegatedCharSeq dcs)
            ? dcs.delegatedCharSeqVal()
            : value.toString();
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
