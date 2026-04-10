package luvs;

/**
 * DSL entry point for CSS media queries.
 * Use {@code import static luvs.MQ.*;} for convenient access.
 *
 * <p>Usage:
 * <pre>
 * import static luvs.MQ.*;
 * import static luvs.P.*;
 * import static luvs.V.*;
 *
 * // Simple breakpoint
 * var tablet = media(minWidth(px(768)),
 *     container.____(padding(rem(2)))
 * );
 *
 * // Range
 * var mid = media(minWidth(px(768)).and(maxWidth(px(1200))),
 *     container.____(max_width(px(1200)))
 * );
 *
 * // Dark mode
 * var dark = media(prefersColorScheme(DARK),
 *     body.____(background_color("#1a1a1a"))
 * );
 *
 * // Media type + condition
 * var screenOnly = media(screen().and(minWidth(px(768))),
 *     sidebar.____(display(FLEX))
 * );
 * </pre>
 */
public final class MQ {

    private MQ() {} // Non-instantiable

    // ========== Media Query Factory ==========

    /**
     * Creates a CSS {@code @media} rule.
     * @param condition The media condition (built from methods in this class).
     * @param content   CSS rule fragments to include. Nested MediaQuery throws at runtime.
     */
    public static MediaQuery media(MediaCondition condition, CssRuleFrag... content) {
        return MediaQuery.media(condition, content);
    }

    // ========== Media Types ==========

    public static MediaCondition screen() {
        return MediaCondition.screen();
    }

    public static MediaCondition print() {
        return MediaCondition.print();
    }

    public static MediaCondition all() {
        return MediaCondition.all();
    }

    // ========== Dimension Features ==========

    /** {@code (min-width: value)} */
    public static MediaCondition minWidth(CharSequence value) {
        return MediaCondition.minWidth(value);
    }

    /** {@code (max-width: value)} */
    public static MediaCondition maxWidth(CharSequence value) {
        return MediaCondition.maxWidth(value);
    }

    /** {@code (min-height: value)} */
    public static MediaCondition minHeight(CharSequence value) {
        return MediaCondition.minHeight(value);
    }

    /** {@code (max-height: value)} */
    public static MediaCondition maxHeight(CharSequence value) {
        return MediaCondition.maxHeight(value);
    }

    // ========== Preference Features ==========

    /** {@code (prefers-color-scheme: scheme)} */
    public static MediaCondition prefersColorScheme(String scheme) {
        return MediaCondition.prefersColorScheme(scheme);
    }

    /** {@code (prefers-reduced-motion: reduce)} */
    public static MediaCondition prefersReducedMotion() {
        return MediaCondition.prefersReducedMotion();
    }

    /** {@code (prefers-contrast: value)} */
    public static MediaCondition prefersContrast(String value) {
        return MediaCondition.prefersContrast(value);
    }

    // ========== Display Features ==========

    /** {@code (orientation: value)} */
    public static MediaCondition orientation(String value) {
        return MediaCondition.orientation(value);
    }

    // ========== Logical ==========

    /** Negates a condition. {@code not(print())} → {@code not print} */
    public static MediaCondition not(MediaCondition condition) {
        return MediaCondition.not(condition);
    }

    // ========== Escape Hatches ==========

    /** Raw condition string for anything not covered by the DSL. */
    public static MediaCondition condition(String rawCondition) {
        return MediaCondition.condition(rawCondition);
    }

    /** Generic feature: {@code (name: value)}. */
    public static MediaCondition feature(String name, CharSequence value) {
        return MediaCondition.feature(name, value);
    }

    // ========== Constants ==========

    public static final String DARK = MediaCondition.DARK;
    public static final String LIGHT = MediaCondition.LIGHT;
    public static final String PORTRAIT = MediaCondition.PORTRAIT;
    public static final String LANDSCAPE = MediaCondition.LANDSCAPE;
}
