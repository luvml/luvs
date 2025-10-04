package luvs;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import luvs.values.*;

/**
 * Static factory for creating CSS Values (V).
 * This is the primary way to create type-safe dimensional values.
 */
public final class V {

    private V() {} // Utility class

    // ========== Length Units ==========

    public static CssUnit px(Number value) { return new Length(value, LengthUnit.PX); }
    public static CssUnit rem(Number value) { return new Length(value, LengthUnit.REM); }
    public static CssUnit em(Number value) { return new Length(value, LengthUnit.EM); }
    public static CssUnit percent(Number value) { return new Length(value, LengthUnit.PERCENT); }
    public static CssUnit vh(Number value) { return new Length(value, LengthUnit.VH); }
    public static CssUnit vw(Number value) { return new Length(value, LengthUnit.VW); }

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
        return String.format("var(--%s, %s)", varName, fallback);
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
        return "min(" + String.join(", ", values) + ")";
    }

    /**
     * CSS max() function.
     * Usage: height(max(vh(50), px(300)))
     */
    public static String max(CharSequence... values) {
        return "max(" + String.join(", ", values) + ")";
    }

    /**
     * CSS clamp() function.
     * Usage: font_size(clamp(px(12), vw(2), px(24)))
     * @param min Minimum value
     * @param preferred Preferred value
     * @param max Maximum value
     */
    public static String clamp(CharSequence min, CharSequence preferred, CharSequence max) {
        return "clamp(" + min + ", " + preferred + ", " + max + ")";
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
        return "hsl(" + hue + ", " + saturation + ", " + lightness + ")";
    }

    /**
     * CSS hsla() function.
     * Usage: color(hsla(120, percent(100), percent(50), 0.8))
     */
    public static String hsla(int hue, CharSequence saturation, CharSequence lightness, double alpha) {
        return "hsla(" + hue + ", " + saturation + ", " + lightness + ", " + alpha + ")";
    }

    // ========== Gradient Functions ==========

    /**
     * CSS linear-gradient() with angle.
     * Usage: background(linearGradientWithAngle(deg(45), RED, BLUE))
     */
    public static String linearGradientWithAngle(CharSequence angle, CharSequence... colors) {
        return "linear-gradient(" + angle + ", " + String.join(", ", colors) + ")";
    }

    /**
     * CSS linear-gradient() without angle (defaults to top-to-bottom).
     * Usage: background(linearGradient(RED, BLUE))
     */
    public static String linearGradient(CharSequence... colors) {
        return "linear-gradient(" + String.join(", ", colors) + ")";
    }

    /**
     * CSS radial-gradient() with simplified API.
     * Usage: background(radialGradient("#ff0000", "#0000ff"))
     */
    public static String radialGradient(CharSequence... colors) {
        return "radial-gradient(" + String.join(", ", colors) + ")";
    }

    /**
     * CSS conic-gradient() with simplified API.
     * Usage: background(conicGradient("#ff0000", "#0000ff", "#00ff00"))
     */
    public static String conicGradient(CharSequence... colors) {
        return "conic-gradient(" + String.join(", ", colors) + ")";
    }

    // ========== Transform Functions (chainable) ==========

    public static Transform rotate(CharSequence angle) {
        return new Transform("rotate(" + angle + ")");
    }

    public static Transform rotateX(CharSequence angle) {
        return new Transform("rotateX(" + angle + ")");
    }

    public static Transform rotateY(CharSequence angle) {
        return new Transform("rotateY(" + angle + ")");
    }

    public static Transform rotateZ(CharSequence angle) {
        return new Transform("rotateZ(" + angle + ")");
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
        return new Transform("translate(" + x + ", " + y + ")");
    }

    public static Transform translateX(CharSequence value) {
        return new Transform("translateX(" + value + ")");
    }

    public static Transform translateY(CharSequence value) {
        return new Transform("translateY(" + value + ")");
    }

    public static Transform translateZ(CharSequence value) {
        return new Transform("translateZ(" + value + ")");
    }

    public static Transform skew(CharSequence x, CharSequence y) {
        return new Transform("skew(" + x + ", " + y + ")");
    }

    public static Transform skewX(CharSequence angle) {
        return new Transform("skewX(" + angle + ")");
    }

    public static Transform skewY(CharSequence angle) {
        return new Transform("skewY(" + angle + ")");
    }

    // ========== Filter Functions (chainable) ==========

    public static Filter blur(CharSequence radius) {
        return new Filter("blur(" + radius + ")");
    }

    public static Filter brightness(Number amount) {
        return new Filter("brightness(" + amount + ")");
    }

    public static Filter contrast(Number amount) {
        return new Filter("contrast(" + amount + ")");
    }

    public static Filter dropShadow(CharSequence offsetX, CharSequence offsetY, CharSequence blurRadius, CharSequence color) {
        return new Filter("drop-shadow(" + offsetX + " " + offsetY + " " + blurRadius + " " + color + ")");
    }

    public static Filter grayscale(Number amount) {
        return new Filter("grayscale(" + amount + ")");
    }

    public static Filter hueRotate(CharSequence angle) {
        return new Filter("hue-rotate(" + angle + ")");
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
        W400 = FontWeight.W400,
        W700 = FontWeight.W700;

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
}