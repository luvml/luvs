package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a CSS {@code @font-face} rule for defining custom fonts.
 *
 * <p>Usage:
 * <pre>
 * import static luvs.FontFace.*;
 * import static luvs.FontDisplay.*;
 *
 * var myFont = fontFace(
 *     fontFamily("MyCustomFont"),
 *     src(url("fonts/myfont.woff2"), format("woff2")),
 *     fontWeight(NORMAL),
 *     fontStyle(NORMAL),
 *     fontDisplay(SWAP)
 * );
 * </pre>
 */
public non-sealed class FontFace implements CssRuleFrag {

    private final FontDescriptor[] descriptors;

    private FontFace(FontDescriptor... descriptors) {
        this.descriptors = descriptors;
    }

    /**
     * Creates a {@code @font-face} rule with the specified font descriptors.
     */
    public static FontFace fontFace(FontDescriptor... descriptors) {
        return new FontFace(descriptors);
    }

    @Override
    public String delegatedCharSeqVal() {
        String descriptorsStr = Arrays.stream(descriptors)
            .map(FontDescriptor::toString)
            .collect(Collectors.joining("\n    "));
        return "@font-face {\n    " + descriptorsStr + "\n}";
    }

    /**
     * Represents a font descriptor (name-value pair inside @font-face).
     */
    public static class FontDescriptor {
        private final String name;
        private final CharSequence value;

        public FontDescriptor(String name, CharSequence value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            return name + ": " + value + ";";
        }
    }

    // ========== Font Descriptor Factory Methods ==========

    /**
     * font-family descriptor (required).
     * Example: fontFamily("MyFont")
     */
    public static FontDescriptor fontFamily(CharSequence familyName) {
        // Quote the family name if not already quoted
        String quoted = familyName.toString();
        if (!quoted.startsWith("\"") && !quoted.startsWith("'")) {
            quoted = "\"" + quoted + "\"";
        }
        return new FontDescriptor("font-family", quoted);
    }

    /**
     * src descriptor (required).
     * Example: src(url("font.woff2"), format("woff2"))
     * Example: src(url("font.woff2"), url("font.woff"))
     */
    public static FontDescriptor src(CharSequence... sources) {
        String value = Arrays.stream(sources)
            .map(CharSequence::toString)
            .collect(Collectors.joining(", "));
        return new FontDescriptor("src", value);
    }

    /**
     * font-weight descriptor (optional).
     * Example: fontWeight(BOLD)
     * Example: fontWeight(100, 900) for range
     */
    public static FontDescriptor fontWeight(CharSequence weight) {
        return new FontDescriptor("font-weight", weight);
    }

    /**
     * font-weight range descriptor (optional).
     * Example: fontWeight(100, 900)
     */
    public static FontDescriptor fontWeight(int min, int max) {
        return new FontDescriptor("font-weight", min + " " + max);
    }

    /**
     * font-style descriptor (optional).
     * Example: fontStyle(FS_ITALIC)
     */
    public static FontDescriptor fontStyle(CharSequence style) {
        return new FontDescriptor("font-style", style);
    }

    /**
     * font-display descriptor (optional).
     * Example: fontDisplay(SWAP)
     */
    public static FontDescriptor fontDisplay(FontDisplay display) {
        return new FontDescriptor("font-display", display.toString());
    }

    /**
     * unicode-range descriptor (optional).
     * Example: unicodeRange("U+0025-00FF")
     */
    public static FontDescriptor unicodeRange(CharSequence range) {
        return new FontDescriptor("unicode-range", range);
    }

    /**
     * font-stretch descriptor (optional).
     * Example: fontStretch("75% 125%")
     */
    public static FontDescriptor fontStretch(CharSequence stretch) {
        return new FontDescriptor("font-stretch", stretch);
    }

    /**
     * font-variant descriptor (optional).
     * Example: fontVariant("small-caps")
     */
    public static FontDescriptor fontVariant(CharSequence variant) {
        return new FontDescriptor("font-variant", variant);
    }

    // ========== Helper Functions for src Values ==========

    /**
     * url() function for src descriptor.
     * Example: url("fonts/myfont.woff2")
     */
    public static CharSequence url(String path) {
        // Quote the path if not already quoted
        String quoted = path;
        if (!path.startsWith("\"") && !path.startsWith("'")) {
            quoted = "\"" + path + "\"";
        }
        return "url(" + quoted + ")";
    }

    /**
     * format() function for src descriptor.
     * Example: format("woff2")
     */
    public static CharSequence format(String formatType) {
        return "format(\"" + formatType + "\")";
    }

    /**
     * local() function for src descriptor.
     * Example: local("Arial")
     */
    public static CharSequence local(String fontName) {
        return "local(\"" + fontName + "\")";
    }

    /**
     * Combines url and format for convenience.
     * Example: urlFormat("font.woff2", "woff2")
     * Produces: url("font.woff2") format("woff2")
     */
    public static CharSequence urlFormat(String path, String formatType) {
        return url(path) + " " + format(formatType);
    }
}
