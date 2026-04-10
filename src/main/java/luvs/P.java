package luvs;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import luvs.values.*;

/**
 * Static factory for creating CSS Properties (P).
 * Methods are named in snake_case to mirror CSS property names.
 */
public final class P {

    private P() {} // Utility class

    /** Converts CharSequence to string, using delegatedCharSeqVal() for DelegatedCharSeq objects */
    private static String toStr(CharSequence value) {
        return (value instanceof luvx.DelegatedCharSeq)
            ? ((luvx.DelegatedCharSeq) value).delegatedCharSeqVal()
            : value.toString();
    }

    private static String joinValues(CharSequence... values) {
        return Stream.of(values).map(P::toStr).collect(Collectors.joining(" "));
    }

    public static CssProperty prop(String name, CharSequence value) {
        return new CssProperty(name, value);
    }

    /**
     * Marks a CSS property value as !important.
     * Usage: background_color(important(PRIMARY))
     */
    public static String important(CharSequence value) {
        return toStr(value) + " !important";
    }
    
    // --- Color & Background ---
    public static CssProperty color(Color value) { return prop("color", value); }
    public static CssProperty color(CharSequence value) { return prop("color", value); }

    public static CssProperty background_color(Color value) { return prop("background-color", value); }
    public static CssProperty background_color(CharSequence value) { return prop("background-color", value); }

    public static CssProperty background(CharSequence value) { return prop("background", value); }
    public static CssProperty background_image(CharSequence value) { return prop("background-image", value); }

    // --- Font & Text ---
    public static CssProperty font_size(CharSequence value) { return prop("font-size", value); }

    public static CssProperty font_weight(FontWeight value) { return prop("font-weight", value); }
    public static CssProperty font_weight(int value) { 
        double x = value/100;
        int xi = (int)x;
        if(xi!=x)throw new IllegalArgumentException("Not a valid font weight "+value);
        if(xi>=1 && xi<=9)return prop("font-weight", xi+""); 
        throw new IllegalArgumentException("Not a valid font weight "+value);
    }
    public static CssProperty font_weight(CharSequence value) { return prop("font-weight", value); }

    public static CssProperty font_family(CharSequence... values) { return prop("font-family", String.join(", ", values)); }

    public static CssProperty text_align(TextAlign value) { return prop("text-align", value); }
    public static CssProperty text_align(CharSequence value) { return prop("text-align", value); }

    public static CssProperty text_decoration(CharSequence value) { return prop("text-decoration", value); }

    public static CssProperty line_height(CharSequence value) { return prop("line-height", value); }
    public static CssProperty line_height(Number value) { return prop("line-height", value.toString()); }

    public static CssProperty font_style(FontStyle value) { return prop("font-style", value); }
    public static CssProperty font_style(CharSequence value) { return prop("font-style", value); }

    public static CssProperty white_space(WhiteSpace value) { return prop("white-space", value); }
    public static CssProperty white_space(CharSequence value) { return prop("white-space", value); }

    // --- Box Model (Margin, Padding, Border) ---
    public static CssProperty margin(CharSequence... values) { return prop("margin", joinValues(values)); }
    public static CssProperty margin_top(CharSequence value) { return prop("margin-top", value); }
    public static CssProperty margin_right(CharSequence value) { return prop("margin-right", value); }
    public static CssProperty margin_bottom(CharSequence value) { return prop("margin-bottom", value); }
    public static CssProperty margin_left(CharSequence value) { return prop("margin-left", value); }

    public static CssProperty padding(CharSequence... values) { return prop("padding", joinValues(values)); }
    public static CssProperty padding_top(CharSequence value) { return prop("padding-top", value); }
    public static CssProperty padding_right(CharSequence value) { return prop("padding-right", value); }
    public static CssProperty padding_bottom(CharSequence value) { return prop("padding-bottom", value); }
    public static CssProperty padding_left(CharSequence value) { return prop("padding-left", value); }

    public static CssProperty border(CharSequence... values) { return prop("border", joinValues(values)); }
    public static CssProperty border_top(CharSequence... values) { return prop("border-top", joinValues(values)); }
    public static CssProperty border_right(CharSequence... values) { return prop("border-right", joinValues(values)); }
    public static CssProperty border_bottom(CharSequence... values) { return prop("border-bottom", joinValues(values)); }
    public static CssProperty border_left(CharSequence... values) { return prop("border-left", joinValues(values)); }
    public static CssProperty border_color(CharSequence value) { return prop("border-color", value); }
    public static CssProperty border_radius(CharSequence... values) { return prop("border-radius", joinValues(values)); }
    public static CssProperty border_collapse(BorderCollapse value) { return prop("border-collapse", value); }
    public static CssProperty border_collapse(CharSequence value) { return prop("border-collapse", value); }

    public static CssProperty outline(CharSequence value) { return prop("outline", value); }

    public static CssProperty box_sizing(BoxSizing value) { return prop("box-sizing", value); }
    public static CssProperty box_sizing(CharSequence value) { return prop("box-sizing", value); }

    // Box-shadow helpers
    public static CssProperty box_shadow(CharSequence value) { return prop("box-shadow", value); }

    /**
     * Creates a box-shadow with common parameters.
     * @param offsetX horizontal offset
     * @param offsetY vertical offset
     * @param blurRadius blur radius
     * @param color shadow color
     */
    public static CssProperty box_shadow(CharSequence offsetX, CharSequence offsetY, CharSequence blurRadius, CharSequence color) {
        return prop("box-shadow", toStr(offsetX) + " " + toStr(offsetY) + " " + toStr(blurRadius) + " " + toStr(color));
    }

    /**
     * Creates a box-shadow with spread radius.
     * @param offsetX horizontal offset
     * @param offsetY vertical offset
     * @param blurRadius blur radius
     * @param spreadRadius spread radius
     * @param color shadow color
     */
    public static CssProperty box_shadow(CharSequence offsetX, CharSequence offsetY, CharSequence blurRadius, CharSequence spreadRadius, CharSequence color) {
        return prop("box-shadow", toStr(offsetX) + " " + toStr(offsetY) + " " + toStr(blurRadius) + " " + toStr(spreadRadius) + " " + toStr(color));
    }

    // --- Layout ---
    public static CssProperty display(Display value) { return prop("display", value); }
    public static CssProperty display(CharSequence value) { return prop("display", value); }

    public static CssProperty position(Position value) { return prop("position", value); }
    public static CssProperty position(CharSequence value) { return prop("position", value); }

    public static CssProperty top(CharSequence value) { return prop("top", value); }
    public static CssProperty right(CharSequence value) { return prop("right", value); }
    public static CssProperty bottom(CharSequence value) { return prop("bottom", value); }
    public static CssProperty left(CharSequence value) { return prop("left", value); }

    public static CssProperty width(CharSequence value) { return prop("width", value); }
    public static CssProperty height(CharSequence value) { return prop("height", value); }
    public static CssProperty min_width(CharSequence value) { return prop("min-width", value); }
    public static CssProperty min_height(CharSequence value) { return prop("min-height", value); }
    public static CssProperty max_width(CharSequence value) { return prop("max-width", value); }
    public static CssProperty max_height(CharSequence value) { return prop("max-height", value); }

    public static CssProperty z_index(Number value) { return prop("z-index", value.toString()); }
    public static CssProperty z_index(CharSequence value) { return prop("z-index", value); }

    public static CssProperty resize(Resize value) { return prop("resize", value); }
    public static CssProperty resize(CharSequence value) { return prop("resize", value); }

    public static CssProperty object_fit(ObjectFit value) { return prop("object-fit", value); }
    public static CssProperty object_fit(CharSequence value) { return prop("object-fit", value); }
    
    // --- Flexbox ---
    public static CssProperty flex_direction(FlexDirection value) { return prop("flex-direction", value); }
    public static CssProperty flex_direction(CharSequence value) { return prop("flex-direction", value); }

    public static CssProperty justify_content(JustifyContent value) { return prop("justify-content", value); }
    public static CssProperty justify_content(CharSequence value) { return prop("justify-content", value); }

    public static CssProperty align_items(AlignItems value) { return prop("align-items", value); }
    public static CssProperty align_items(CharSequence value) { return prop("align-items", value); }

    public static CssProperty flex(Flex value) { return prop("flex", value); }
    public static CssProperty flex(CharSequence value) { return prop("flex", value); }

    public static CssProperty flex_wrap(FlexWrap value) { return prop("flex-wrap", value); }
    public static CssProperty flex_wrap(CharSequence value) { return prop("flex-wrap", value); }
    public static CssProperty flex_shrink(CharSequence value) { return prop("flex-shrink", value); }
    public static CssProperty flex_grow(CharSequence value) { return prop("flex-grow", value); }
    public static CssProperty flex_basis(CharSequence value) { return prop("flex-basis", value); }

    public static CssProperty gap(CharSequence value) { return prop("gap", value); }

    // --- Grid ---
    public static CssProperty grid_template_columns(CharSequence value) { return prop("grid-template-columns", value); }
    public static CssProperty grid_template_rows(CharSequence value) { return prop("grid-template-rows", value); }
    public static CssProperty grid_column(CharSequence value) { return prop("grid-column", value); }
    public static CssProperty grid_row(CharSequence value) { return prop("grid-row", value); }

    /**
     * Helper for CSS grid repeat() function.
     * @param count number of repetitions or "auto-fill" or "auto-fit"
     * @param trackSize track size (e.g., "1fr", "200px", "minmax(200px, 1fr)")
     * @return The string "repeat(count, trackSize)"
     */
    public static String grid_repeat(CharSequence count, CharSequence trackSize) {
        return "repeat(" + toStr(count) + ", " + toStr(trackSize) + ")";
    }

    /**
     * Helper for CSS grid minmax() function.
     * @param min minimum size
     * @param max maximum size
     * @return The string "minmax(min, max)"
     */
    public static String grid_minmax(CharSequence min, CharSequence max) {
        return "minmax(" + toStr(min) + ", " + toStr(max) + ")";
    }

    // --- Overflow & Cursor ---
    public static CssProperty overflow(Overflow value) { return prop("overflow", value); }
    public static CssProperty overflow(CharSequence value) { return prop("overflow", value); }

    public static CssProperty overflow_x(Overflow value) { return prop("overflow-x", value); }
    public static CssProperty overflow_x(CharSequence value) { return prop("overflow-x", value); }

    public static CssProperty overflow_y(Overflow value) { return prop("overflow-y", value); }
    public static CssProperty overflow_y(CharSequence value) { return prop("overflow-y", value); }

    public static CssProperty cursor(Cursor value) { return prop("cursor", value); }
    public static CssProperty cursor(CharSequence value) { return prop("cursor", value); }

    // --- Transforms & Filters ---
    public static CssProperty transform(CharSequence value) { return prop("transform", value); }
    public static CssProperty filter(CharSequence value) { return prop("filter", value); }

    // --- Pseudo-element content ---
    public static CssProperty content(CharSequence value) { return prop("content", value); }

    // --- Opacity ---
    public static CssProperty opacity(Number value) { return prop("opacity", value.toString()); }
    public static CssProperty opacity(CharSequence value) { return prop("opacity", value); }

    // --- Transitions & Animation ---
    public static CssProperty transition(CharSequence value) { return prop("transition", value); }

    /**
     * Creates a transition for a single property.
     * @param property CSS property name (e.g., "background", "transform")
     * @param duration duration (e.g., "0.3s", "200ms")
     */
    public static CssProperty transition(CharSequence property, CharSequence duration) {
        return prop("transition", toStr(property) + " " + toStr(duration));
    }

    /**
     * Creates a transition for a single property with timing function.
     * @param property CSS property name
     * @param duration duration
     * @param timingFunction timing function (e.g., "ease", "linear", "ease-in-out")
     */
    public static CssProperty transition(CharSequence property, CharSequence duration, CharSequence timingFunction) {
        return prop("transition", toStr(property) + " " + toStr(duration) + " " + toStr(timingFunction));
    }

    /**
     * Helper for creating a single transition spec (property + duration).
     * Usage: transition(trans("background", "0.3s"), trans("transform", "0.2s"))
     */
    public static String trans(CharSequence property, CharSequence duration) {
        return toStr(property) + " " + toStr(duration);
    }

    /**
     * Helper for creating a single transition spec with timing function.
     * Usage: trans("background", "0.3s", "ease-in-out")
     */
    public static String trans(CharSequence property, CharSequence duration, CharSequence timingFunction) {
        return toStr(property) + " " + toStr(duration) + " " + toStr(timingFunction);
    }

    /**
     * Creates multiple transitions with property-duration pairs.
     * Usage: transition(TRANSFORM, s(0.2), BOX_SHADOW, s(0.2))
     * @param propertyDurationPairs alternating property and duration values (must be even number)
     */
    public static CssProperty transition(CharSequence... propertyDurationPairs) {
        if (propertyDurationPairs.length == 1) {
            // Single value - use existing single-value method
            return prop("transition", propertyDurationPairs[0]);
        }
        if (propertyDurationPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Must provide property-duration pairs (even number of arguments). Got " + propertyDurationPairs.length);
        }
        java.util.List<String> transitions = new java.util.ArrayList<>();
        for (int i = 0; i < propertyDurationPairs.length; i += 2) {
            transitions.add(toStr(propertyDurationPairs[i]) + " " + toStr(propertyDurationPairs[i + 1]));
        }
        return prop("transition", String.join(", ", transitions));
    }

    public static CssProperty transition_property(CharSequence value) { return prop("transition-property", value); }
    public static CssProperty transition_duration(CharSequence value) { return prop("transition-duration", value); }
    public static CssProperty transition_timing_function(CharSequence value) { return prop("transition-timing-function", value); }
    public static CssProperty transition_delay(CharSequence value) { return prop("transition-delay", value); }

    public static CssProperty animation(CharSequence value) { return prop("animation", value); }

    /**
     * Animation with name and duration.
     * Usage: animation(fadeIn, s(0.5))
     * @param name animation name (Keyframes object or string)
     * @param duration duration (e.g., s(0.5), ms(300))
     */
    public static CssProperty animation(CharSequence name, CharSequence duration) {
        return prop("animation", toStr(name) + " " + toStr(duration));
    }

    /**
     * Animation with name, duration, and timing function.
     * Usage: animation(fadeIn, s(0.5), "ease-in-out")
     * @param name animation name (Keyframes object or string)
     * @param duration duration
     * @param timingFunction timing function (e.g., "ease", "linear", "ease-in-out")
     */
    public static CssProperty animation(CharSequence name, CharSequence duration, CharSequence timingFunction) {
        return prop("animation", toStr(name) + " " + toStr(duration) + " " + toStr(timingFunction));
    }

    /**
     * Animation with name, duration, timing function, and delay.
     * Usage: animation(fadeIn, s(0.5), "ease-in-out", s(0.2))
     * @param name animation name (Keyframes object or string)
     * @param duration duration
     * @param timingFunction timing function
     * @param delay delay before animation starts
     */
    public static CssProperty animation(CharSequence name, CharSequence duration, CharSequence timingFunction, CharSequence delay) {
        return prop("animation", toStr(name) + " " + toStr(duration) + " " + toStr(timingFunction) + " " + toStr(delay));
    }

    public static CssProperty animation_name(CharSequence value) { return prop("animation-name", value); }
    public static CssProperty animation_duration(CharSequence value) { return prop("animation-duration", value); }
    public static CssProperty animation_timing_function(CharSequence value) { return prop("animation-timing-function", value); }
    public static CssProperty animation_delay(CharSequence value) { return prop("animation-delay", value); }
    public static CssProperty animation_iteration_count(CharSequence value) { return prop("animation-iteration-count", value); }
    public static CssProperty animation_direction(CharSequence value) { return prop("animation-direction", value); }
    public static CssProperty animation_fill_mode(CharSequence value) { return prop("animation-fill-mode", value); }

    // --- Custom ---
    /**
     * For setting CSS variables like --main-color: #123456;
     */
    public static CssProperty custom_var(String name, CharSequence value) {
        return prop("--" + name, value);
    }
}