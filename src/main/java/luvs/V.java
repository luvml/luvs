package luvs;

import luvs.values.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static luvs.CssProp.*;

/**
 * Static factory for creating CSS Values (V).
 * This is the primary way to create type-safe dimensional values.
 */
public final class V {

    private V() {} // Utility class

    /** Converts CharSequence to string, using delegatedCharSeqVal() for DelegatedCharSeq objects */
    private static String toStr(CharSequence value) {
        return (value instanceof luvx.DelegatedCharSeq)
            ? ((luvx.DelegatedCharSeq) value).delegatedCharSeqVal()
            : value.toString();
    }

    private static String joinValues(CharSequence... values) {
        return Stream.of(values).map(V::toStr).collect(Collectors.joining(", "));
    }

    // ========== Length Units ==========

    public static CssUnit px(Number value) { return new Length(value, LengthUnit.PX); }
    public static CssUnit rem(Number value) { return new Length(value, LengthUnit.REM); }
    public static CssUnit em(Number value) { return new Length(value, LengthUnit.EM); }
    public static CssUnit percent(Number value) { return new Length(value, LengthUnit.PERCENT); }
    public static CssUnit vh(Number value) { return new Length(value, LengthUnit.VH); }
    public static CssUnit vw(Number value) { return new Length(value, LengthUnit.VW); }
    public static CssUnit vmin(Number value) { return new Length(value, LengthUnit.VMIN); }
    public static CssUnit vmax(Number value) { return new Length(value, LengthUnit.VMAX); }

    // Print units
    public static CssUnit cm(Number value) { return new Length(value, LengthUnit.CM); }
    public static CssUnit mm(Number value) { return new Length(value, LengthUnit.MM); }
    public static CssUnit in(Number value) { return new Length(value, LengthUnit.IN); }
    public static CssUnit pt(Number value) { return new Length(value, LengthUnit.PT); }
    public static CssUnit pc(Number value) { return new Length(value, LengthUnit.PC); }

    // ========== Time Units ==========

    public static CssUnit s(Number value) { return new Time(value, TimeUnit.S); }
    public static CssUnit ms(Number value) { return new Time(value, TimeUnit.MS); }

    // ========== Grid Units ==========

    /**
     * Creates a CSS grid fractional unit (fr).
     * Usage: grid_template_columns(fr(1), fr(2)) → "1fr 2fr"
     */
    public static GridUnit fr(Number value) { return new GridUnit(value.doubleValue()); }

    // ========== Angle Units ==========

    public static CssUnit deg(Number value) { return new Angle(value, AngleUnit.DEG); }
    public static CssUnit rad(Number value) { return new Angle(value, AngleUnit.RAD); }
    public static CssUnit grad(Number value) { return new Angle(value, AngleUnit.GRAD); }
    public static CssUnit turn(Number value) { return new Angle(value, AngleUnit.TURN); }
    
    // It's often useful to have a zero constant without a unit, as CSS allows it.
    public static final String ZERO = "0";

    /**
     * Represents the CSS 'auto' value.
     */
    public static final String AUTO = "auto";
    
    /**
     * Represents the CSS 'inherit' keyword.
     */
    public static final String INHERIT = "inherit";

    /**
     * Represents the CSS 'initial' keyword.
     */
    public static final String INITIAL = "initial";

    /**
     * Represents the CSS 'subgrid' keyword for grid-template-columns/rows.
     * Usage: grid_template_columns(SUBGRID)
     * Allows nested grid items to align with parent grid tracks.
     */
    public static final String SUBGRID = "subgrid";

    // ========== Common Value Constants (Added after audit) ==========

    // Text overflow
    public static final String ELLIPSIS = "ellipsis";
    // CLIP - already defined

    // Word break & overflow wrap
    public static final String BREAK_ALL = "break-all";
    public static final String KEEP_ALL = "keep-all";
    public static final String BREAK_WORD = "break-word";
    public static final String ANYWHERE = "anywhere";
    // NORMAL - already defined

    // Text transform
    public static final String UPPERCASE = "uppercase";
    public static final String LOWERCASE = "lowercase";
    public static final String CAPITALIZE = "capitalize";

    // Vertical align
    // BASELINE - already defined
    public static final String TOP = "top";
    public static final String MIDDLE = "middle";
    public static final String BOTTOM = "bottom";
    public static final String TEXT_TOP = "text-top";
    public static final String TEXT_BOTTOM = "text-bottom";
    public static final String SUB = "sub";
    public static final String SUPER = "super";

    // Hyphens
    public static final String MANUAL = "manual";

    // List style
    public static final String DISC = "disc";
    public static final String CIRCLE = "circle";
    public static final String SQUARE = "square";
    public static final String DECIMAL = "decimal";
    public static final String INSIDE = "inside";
    public static final String OUTSIDE = "outside";

    // Webkit box orient (for line-clamp)
    // VERTICAL - already defined
    // HORIZONTAL - already defined

    // Scrollbar width (Firefox)
    public static final String THIN = "thin";

    // Column span
    public static final String ALL = "all";

    // Appearance
    public static final String MENULIST = "menulist";
    public static final String TEXTFIELD = "textfield";
    public static final String BUTTON = "button";
    public static final String CHECKBOX = "checkbox";

    // Writing mode
    public static final String LR_TB = "lr-tb";
    public static final String RL_TB = "rl-tb";
    public static final String TB_RL = "tb-rl";
    public static final String TB_LR = "tb-lr";

    // Direction
    public static final String LTR = "ltr";
    public static final String RTL = "rtl";

    // Image rendering
    public static final String CRISP_EDGES = "crisp-edges";
    public static final String PIXELATED = "pixelated";

    // Transform style
    public static final String FLAT = "flat";
    public static final String PRESERVE_3D = "preserve-3d";

    // Mix blend mode / isolation
    public static final String MULTIPLY = "multiply";
    public static final String SCREEN = "screen";
    public static final String OVERLAY = "overlay";
    public static final String DARKEN = "darken";
    public static final String LIGHTEN = "lighten";
    public static final String COLOR_DODGE = "color-dodge";
    public static final String COLOR_BURN = "color-burn";
    public static final String HARD_LIGHT = "hard-light";
    public static final String SOFT_LIGHT = "soft-light";
    public static final String DIFFERENCE = "difference";
    public static final String EXCLUSION = "exclusion";
    public static final String HUE = "hue";
    public static final String SATURATION = "saturation";
    public static final String COLOR = "color";
    public static final String LUMINOSITY = "luminosity";
    public static final String ISOLATE = "isolate";

    // Visibility (VISIBLE, HIDDEN already defined in enums below)
    public static final String COLLAPSE = "collapse";

    // Float & Clear (LEFT, RIGHT, NONE already defined in enums below)
    public static final String BOTH = "both";

    // Animation play state
    public static final String RUNNING = "running";
    public static final String PAUSED = "paused";

    // All property values
    public static final String UNSET = "unset";
    public static final String REVERT = "revert";

    // ====================================================================

    /**
     * For creating CSS var() functions.
     * @param varName The name of the CSS variable (without --).
     * @return The string "var(--varName)".
     */
    public static String var(String varName) {
        return String.format("var(--%s)", varName);
    }
    
    /**
     * For creating CSS var() functions with a fallback.
     * @param varName The name of the CSS variable (without --).
     * @param fallback The fallback value.
     * @return The string "var(--varName, fallback)".
     */
    public static String var(String varName, CharSequence fallback) {
        return String.format("var(--%s, %s)", varName, toStr(fallback));
    }

    /**
     * For creating CSS calc() expressions from strings (fallback).
     * @param expression The calc expression content.
     * @return The string "calc(expression)".
     */
    public static CalcExpression calc(String expression) {
        return CalcExpression.calc(expression);
    }

    // ========== Math Functions ==========

    /**
     * CSS min() function.
     * Usage: width(min(percent(100), px(500)))
     */
    public static String min(CharSequence... values) {
        return "min(" + joinValues(values) + ")";
    }

    /**
     * CSS max() function.
     * Usage: height(max(vh(50), px(300)))
     */
    public static String max(CharSequence... values) {
        return "max(" + joinValues(values) + ")";
    }

    /**
     * CSS clamp() function.
     * Usage: font_size(clamp(px(12), vw(2), px(24)))
     * @param min Minimum value
     * @param preferred Preferred value
     * @param max Maximum value
     */
    public static String clamp(CharSequence min, CharSequence preferred, CharSequence max) {
        return "clamp(" + toStr(min) + ", " + toStr(preferred) + ", " + toStr(max) + ")";
    }

    // ========== Color Functions ==========

    /**
     * CSS rgb() function.
     * Usage: color(rgb(255, 0, 0))
     */
    public static String rgb(int r, int g, int b) {
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }

    /**
     * CSS rgba() function.
     * Usage: background_color(rgba(0, 0, 255, 0.5))
     */
    public static String rgba(int r, int g, int b, double alpha) {
        return "rgba(" + r + ", " + g + ", " + b + ", " + alpha + ")";
    }

    /**
     * CSS hsl() function.
     * Usage: color(hsl(120, percent(100), percent(50)))
     */
    public static String hsl(int hue, CharSequence saturation, CharSequence lightness) {
        return "hsl(" + hue + ", " + toStr(saturation) + ", " + toStr(lightness) + ")";
    }

    /**
     * CSS hsla() function.
     * Usage: color(hsla(120, percent(100), percent(50), 0.8))
     */
    public static String hsla(int hue, CharSequence saturation, CharSequence lightness, double alpha) {
        return "hsla(" + hue + ", " + toStr(saturation) + ", " + toStr(lightness) + ", " + alpha + ")";
    }

    /**
     * CSS color-mix() function.
     * Mixes two colors in a specified color space.
     * Usage: color(colorMix("in srgb", RED, percent(50), BLUE))
     * Produces: color-mix(in srgb, red 50%, blue)
     *
     * @param colorSpace The color space (e.g., "in srgb", "in oklch", "in hsl")
     * @param color1 First color
     * @param percentage1 Percentage of first color (optional, use null to omit)
     * @param color2 Second color
     */
    public static String colorMix(String colorSpace, CharSequence color1, CharSequence percentage1, CharSequence color2) {
        String c1 = toStr(color1);
        String c2 = toStr(color2);
        if (percentage1 != null) {
            return "color-mix(" + colorSpace + ", " + c1 + " " + toStr(percentage1) + ", " + c2 + ")";
        } else {
            return "color-mix(" + colorSpace + ", " + c1 + ", " + c2 + ")";
        }
    }

    /**
     * CSS color-mix() function with percentages for both colors.
     * Usage: colorMix("in srgb", RED, percent(30), BLUE, percent(70))
     * Produces: color-mix(in srgb, red 30%, blue 70%)
     */
    public static String colorMix(String colorSpace, CharSequence color1, CharSequence percentage1,
                                   CharSequence color2, CharSequence percentage2) {
        return "color-mix(" + colorSpace + ", " + toStr(color1) + " " + toStr(percentage1) +
               ", " + toStr(color2) + " " + toStr(percentage2) + ")";
    }

    /**
     * Simple color-mix with equal parts (50/50).
     * Usage: colorMix("in srgb", RED, BLUE)
     * Produces: color-mix(in srgb, red, blue)
     */
    public static String colorMix(String colorSpace, CharSequence color1, CharSequence color2) {
        return colorMix(colorSpace, color1, null, color2);
    }

    /**
     * Relative RGB color syntax - derives new color from existing.
     * Usage: rgbFrom(var("--primary"), "r g b / 0.5")
     * Produces: rgb(from var(--primary) r g b / 0.5)
     *
     * Example variations:
     * - rgbFrom("blue", "r g b / 0.5") → semi-transparent version
     * - rgbFrom(var("--accent"), "calc(r * 1.2) g b") → lighter red channel
     * - rgbFrom("#ff0000", "r g b / calc(alpha - 0.2)") → more transparent
     */
    public static String rgbFrom(CharSequence sourceColor, String channels) {
        return "rgb(from " + toStr(sourceColor) + " " + channels + ")";
    }

    /**
     * Relative HSL color syntax.
     * Usage: hslFrom(var("--primary"), "h s calc(l * 1.2)")
     * Produces: hsl(from var(--primary) h s calc(l * 1.2))
     *
     * Common patterns:
     * - hslFrom("blue", "h s calc(l * 0.8)") → darker version
     * - hslFrom(var("--accent"), "calc(h + 180) s l") → complementary color
     * - hslFrom("#ff0000", "h calc(s * 0.5) l") → desaturated version
     */
    public static String hslFrom(CharSequence sourceColor, String channels) {
        return "hsl(from " + toStr(sourceColor) + " " + channels + ")";
    }

    /**
     * Relative OKLCH color syntax (modern color space).
     * Usage: oklchFrom(var("--primary"), "l c h / 0.8")
     * Produces: oklch(from var(--primary) l c h / 0.8)
     */
    public static String oklchFrom(CharSequence sourceColor, String channels) {
        return "oklch(from " + toStr(sourceColor) + " " + channels + ")";
    }

    /**
     * Relative OKLAB color syntax.
     * Usage: oklabFrom(var("--bg"), "calc(l + 0.1) a b")
     * Produces: oklab(from var(--bg) calc(l + 0.1) a b)
     */
    public static String oklabFrom(CharSequence sourceColor, String channels) {
        return "oklab(from " + toStr(sourceColor) + " " + channels + ")";
    }

    /**
     * Generic relative color syntax for any color function.
     * Usage: colorFrom("lch", var("--primary"), "l c h / 0.5")
     * Produces: lch(from var(--primary) l c h / 0.5)
     *
     * Use this for color spaces not covered by dedicated helpers.
     */
    public static String colorFrom(String colorFunction, CharSequence sourceColor, String channels) {
        return colorFunction + "(from " + toStr(sourceColor) + " " + channels + ")";
    }

    // ========== Gradient Functions ==========

    /**
     * CSS linear-gradient() with angle.
     * Usage: background(linearGradientWithAngle(deg(45), RED, BLUE))
     */
    public static String linearGradientWithAngle(CharSequence angle, CharSequence... colors) {
        return "linear-gradient(" + toStr(angle) + ", " + joinValues(colors) + ")";
    }

    /**
     * CSS linear-gradient() without angle (defaults to top-to-bottom).
     * Usage: background(linearGradient(RED, BLUE))
     */
    public static String linearGradient(CharSequence... colors) {
        return "linear-gradient(" + joinValues(colors) + ")";
    }

    /**
     * CSS radial-gradient() with simplified API.
     * Usage: background(radialGradient("#ff0000", "#0000ff"))
     */
    public static String radialGradient(CharSequence... colors) {
        return "radial-gradient(" + joinValues(colors) + ")";
    }

    /**
     * CSS conic-gradient() with simplified API.
     * Usage: background(conicGradient("#ff0000", "#0000ff", "#00ff00"))
     */
    public static String conicGradient(CharSequence... colors) {
        return "conic-gradient(" + joinValues(colors) + ")";
    }

    // ========== Transform Functions (chainable) ==========

    public static Transform rotate(CharSequence angle) {
        return new Transform("rotate(" + toStr(angle) + ")");
    }

    public static Transform rotateX(CharSequence angle) {
        return new Transform("rotateX(" + toStr(angle) + ")");
    }

    public static Transform rotateY(CharSequence angle) {
        return new Transform("rotateY(" + toStr(angle) + ")");
    }

    public static Transform rotateZ(CharSequence angle) {
        return new Transform("rotateZ(" + toStr(angle) + ")");
    }

    public static Transform scale(Number value) {
        return new Transform("scale(" + value + ")");
    }

    public static Transform scale(Number x, Number y) {
        return new Transform("scale(" + x + ", " + y + ")");
    }

    public static Transform scaleX(Number value) {
        return new Transform("scaleX(" + value + ")");
    }

    public static Transform scaleY(Number value) {
        return new Transform("scaleY(" + value + ")");
    }

    public static Transform translate(CharSequence x, CharSequence y) {
        return new Transform("translate(" + toStr(x) + ", " + toStr(y) + ")");
    }

    public static Transform translateX(CharSequence value) {
        return new Transform("translateX(" + toStr(value) + ")");
    }

    public static Transform translateY(CharSequence value) {
        return new Transform("translateY(" + toStr(value) + ")");
    }

    public static Transform translateZ(CharSequence value) {
        return new Transform("translateZ(" + toStr(value) + ")");
    }

    public static Transform skew(CharSequence x, CharSequence y) {
        return new Transform("skew(" + toStr(x) + ", " + toStr(y) + ")");
    }

    public static Transform skewX(CharSequence angle) {
        return new Transform("skewX(" + toStr(angle) + ")");
    }

    public static Transform skewY(CharSequence angle) {
        return new Transform("skewY(" + toStr(angle) + ")");
    }

    // ========== Filter Functions (chainable) ==========

    public static Filter blur(CharSequence radius) {
        return new Filter("blur(" + toStr(radius) + ")");
    }

    public static Filter brightness(Number amount) {
        return new Filter("brightness(" + amount + ")");
    }

    public static Filter contrast(Number amount) {
        return new Filter("contrast(" + amount + ")");
    }

    public static Filter dropShadow(CharSequence offsetX, CharSequence offsetY, CharSequence blurRadius, CharSequence color) {
        return new Filter("drop-shadow(" + toStr(offsetX) + " " + toStr(offsetY) + " " + toStr(blurRadius) + " " + toStr(color) + ")");
    }

    public static Filter grayscale(Number amount) {
        return new Filter("grayscale(" + amount + ")");
    }

    public static Filter hueRotate(CharSequence angle) {
        return new Filter("hue-rotate(" + toStr(angle) + ")");
    }

    public static Filter invert(Number amount) {
        return new Filter("invert(" + amount + ")");
    }

    public static Filter opacity(Number amount) {
        return new Filter("opacity(" + amount + ")");
    }

    public static Filter saturate(Number amount) {
        return new Filter("saturate(" + amount + ")");
    }

    public static Filter sepia(Number amount) {
        return new Filter("sepia(" + amount + ")");
    }

    // ========== Type-Safe Enum Constants ==========
    // Re-export all enum values for single import convenience

    // Colors
    public static final Color 
     BLACK = Color.BLACK,
     WHITE = Color.WHITE,
     RED = Color.RED,
     GREEN = Color.GREEN,
     BLUE = Color.BLUE,
     YELLOW = Color.YELLOW,
     ORANGE = Color.ORANGE,
     PURPLE = Color.PURPLE,
     PINK = Color.PINK,
     GRAY = Color.GRAY,
     LIGHT_GRAY = Color.LIGHT_GRAY,
     DARK_GRAY = Color.DARK_GRAY,
     LIGHT_BLUE = Color.LIGHT_BLUE,
     TRANSPARENT = Color.TRANSPARENT,
     CURRENT_COLOR = Color.CURRENT_COLOR;

    // TextAlign (all values - small enum)
    public static final TextAlign
        LEFT = TextAlign.LEFT,
        RIGHT = TextAlign.RIGHT,
        CENTER = TextAlign.CENTER,
        JUSTIFY = TextAlign.JUSTIFY,
        START = TextAlign.START,
        END = TextAlign.END;

    // Display (common values)
    public static final Display
        BLOCK = Display.BLOCK,
        INLINE = Display.INLINE,
        INLINE_BLOCK = Display.INLINE_BLOCK,
        FLEX = Display.FLEX,
        INLINE_FLEX = Display.INLINE_FLEX,
        GRID = Display.GRID,
        INLINE_GRID = Display.INLINE_GRID,
        NONE = Display.NONE;

    // Position (all values - small enum)
    public static final Position
        STATIC = Position.STATIC,
        RELATIVE = Position.RELATIVE,
        ABSOLUTE = Position.ABSOLUTE,
        FIXED = Position.FIXED,
        STICKY = Position.STICKY;

    // FontWeight (common values)
    public static final FontWeight
        NORMAL = FontWeight.NORMAL,
        BOLD = FontWeight.BOLD,
        BOLDER = FontWeight.BOLDER,
        LIGHTER = FontWeight.LIGHTER,
        W100 = FontWeight.W100,
        W200 = FontWeight.W200,
        W300 = FontWeight.W300,  
        W400 = FontWeight.W400,
        W500 = FontWeight.W500,
        W600 = FontWeight.W600,
        W700 = FontWeight.W700,
        W800 = FontWeight.W800,
        W900 = FontWeight.W900
        ;

    // FlexDirection (all values - small enum)
    public static final FlexDirection
        ROW = FlexDirection.ROW,
        ROW_REVERSE = FlexDirection.ROW_REVERSE,
        COLUMN = FlexDirection.COLUMN,
        COLUMN_REVERSE = FlexDirection.COLUMN_REVERSE;

    // JustifyContent (all values - small enum)
    public static final JustifyContent
        FLEX_START = JustifyContent.FLEX_START,
        FLEX_END = JustifyContent.FLEX_END,
        JC_CENTER = JustifyContent.CENTER,
        SPACE_BETWEEN = JustifyContent.SPACE_BETWEEN,
        SPACE_AROUND = JustifyContent.SPACE_AROUND,
        SPACE_EVENLY = JustifyContent.SPACE_EVENLY;

    // AlignItems (all values - small enum)
    public static final AlignItems
        AI_FLEX_START = AlignItems.FLEX_START,
        AI_FLEX_END = AlignItems.FLEX_END,
        AI_CENTER = AlignItems.CENTER,
        BASELINE = AlignItems.BASELINE,
        STRETCH = AlignItems.STRETCH;

    // Overflow (all values - small enum)
    public static final Overflow
        VISIBLE = Overflow.VISIBLE,
        HIDDEN = Overflow.HIDDEN,
        SCROLL = Overflow.SCROLL,
        OV_AUTO = Overflow.AUTO,
        CLIP = Overflow.CLIP;

    // TextDecoration (common values)
    public static final TextDecoration
        TD_NONE = TextDecoration.NONE,
        UNDERLINE = TextDecoration.UNDERLINE,
        LINE_THROUGH = TextDecoration.LINE_THROUGH;

    // BorderStyle (common values)
    public static final BorderStyle
        BS_NONE = BorderStyle.NONE,
        SOLID = BorderStyle.SOLID,
        DASHED = BorderStyle.DASHED,
        DOTTED = BorderStyle.DOTTED;

    // BorderCollapse (all values - small enum)
    public static final BorderCollapse
        BC_COLLAPSE = BorderCollapse.COLLAPSE,
        BC_SEPARATE = BorderCollapse.SEPARATE;

    // BoxSizing (all values - small enum)
    public static final BoxSizing
        CONTENT_BOX = BoxSizing.CONTENT_BOX,
        BORDER_BOX = BoxSizing.BORDER_BOX;

    // Resize (all values - small enum)
    public static final Resize
        RS_NONE = Resize.NONE,
        RS_BOTH = Resize.BOTH,
        HORIZONTAL = Resize.HORIZONTAL,
        VERTICAL = Resize.VERTICAL;

    // ObjectFit (all values - small enum)
    public static final ObjectFit
        OF_FILL = ObjectFit.FILL,
        OF_CONTAIN = ObjectFit.CONTAIN,
        OF_COVER = ObjectFit.COVER,
        OF_NONE = ObjectFit.NONE,
        SCALE_DOWN = ObjectFit.SCALE_DOWN;

    // FontStyle (all values - small enum)
    public static final FontStyle
        FS_NORMAL = FontStyle.NORMAL,
        FS_ITALIC = FontStyle.ITALIC,
        FS_OBLIQUE = FontStyle.OBLIQUE;

    // WhiteSpace (all values - small enum)
    public static final WhiteSpace
        WS_NORMAL = WhiteSpace.NORMAL,
        WS_NOWRAP = WhiteSpace.NOWRAP,
        WS_PRE = WhiteSpace.PRE,
        WS_PRE_WRAP = WhiteSpace.PRE_WRAP,
        WS_PRE_LINE = WhiteSpace.PRE_LINE;

    // FlexWrap (all values - small enum)
    public static final FlexWrap
        FW_NOWRAP = FlexWrap.NOWRAP,
        FW_WRAP = FlexWrap.WRAP,
        FW_WRAP_REVERSE = FlexWrap.WRAP_REVERSE;

    // Flex (common values)
    public static final Flex
        FLEX_1 = Flex.ONE,
        FLEX_0 = Flex.ZERO,
        FLEX_AUTO = Flex.AUTO,
        FLEX_NONE = Flex.NONE;

    // Cursor (common values only - for exhaustive list use Cursor enum directly)
    public static final Cursor
        POINTER = Cursor.POINTER,
        MOVE = Cursor.MOVE,
        TEXT = Cursor.TEXT,
        WAIT = Cursor.WAIT,
        HELP = Cursor.HELP,
        NOT_ALLOWED = Cursor.NOT_ALLOWED,
        GRAB = Cursor.GRAB,
        GRABBING = Cursor.GRABBING;

    // GridRepeatMode (all values - small enum)
    public static final GridRepeatMode
        AUTO_FIT = GridRepeatMode.AUTO_FIT,
        AUTO_FILL = GridRepeatMode.AUTO_FILL;

    // InputType (common values - for exhaustive list use InputType enum directly)
    public static final InputType
        INPUT_TEXT = InputType.TEXT,
        INPUT_NUMBER = InputType.NUMBER,
        INPUT_EMAIL = InputType.EMAIL,
        INPUT_PASSWORD = InputType.PASSWORD,
        INPUT_CHECKBOX = InputType.CHECKBOX,
        INPUT_RADIO = InputType.RADIO,
        INPUT_DATE = InputType.DATE,
        INPUT_TIME = InputType.TIME,
        INPUT_FILE = InputType.FILE,
        INPUT_RANGE = InputType.RANGE,
        INPUT_SEARCH = InputType.SEARCH,
        INPUT_TEL = InputType.TEL,
        INPUT_URL = InputType.URL,
        INPUT_SUBMIT = InputType.SUBMIT,
        INPUT_BUTTON = InputType.BUTTON,
        INPUT_RESET = InputType.RESET;

    // TimingFunction (all values - small enum)
    public static final TimingFunction
        EASE = TimingFunction.EASE,
        LINEAR = TimingFunction.LINEAR,
        EASE_IN = TimingFunction.EASE_IN,
        EASE_OUT = TimingFunction.EASE_OUT,
        EASE_IN_OUT = TimingFunction.EASE_IN_OUT,
        STEP_START = TimingFunction.STEP_START,
        STEP_END = TimingFunction.STEP_END;

    // ========== Gradient Color Stop Helper ==========

    /**
     * Creates a gradient color stop with percentage.
     * Usage: stop(PRIMARY, 0) → "PRIMARY 0%"
     * Usage: stop(PRIMARY, 50) → "PRIMARY 50%"
     */
    public static String stop(CharSequence color, Number percentage) {
        return toStr(color) + " " + percentage + "%";
    }

    /**
     * Creates a gradient color stop with explicit position.
     * Usage: stop(PRIMARY, "10px") → "PRIMARY 10px"
     * Usage: stop(PRIMARY, percent(25)) → "PRIMARY 25%"
     */
    public static String stop(CharSequence color, CharSequence position) {
        return toStr(color) + " " + toStr(position);
    }

    // ========== CSS Property Name Constants ==========
    // Re-export from CssProp for convenience

    // Note: CssProp constants are already imported via static import above
    // They are directly accessible: BACKGROUND, TRANSFORM, ALL, etc.
}