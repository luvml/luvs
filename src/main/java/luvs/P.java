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
    
    private static String joinValues(CharSequence... values) {
        return Stream.of(values).map(CharSequence::toString).collect(Collectors.joining(" "));
    }

    public static CssProperty prop(String name, CharSequence value) {
        return new CssProperty(name, value);
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
    public static CssProperty font_weight(CharSequence value) { return prop("font-weight", value); }

    public static CssProperty font_family(CharSequence... values) { return prop("font-family", String.join(", ", values)); }

    public static CssProperty text_align(TextAlign value) { return prop("text-align", value); }
    public static CssProperty text_align(CharSequence value) { return prop("text-align", value); }

    public static CssProperty text_decoration(CharSequence value) { return prop("text-decoration", value); }
    public static CssProperty line_height(CharSequence value) { return prop("line-height", value); }

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
    public static CssProperty border_color(CharSequence value) { return prop("border-color", value); }
    public static CssProperty border_radius(CharSequence... values) { return prop("border-radius", joinValues(values)); }
    public static CssProperty outline(CharSequence value) { return prop("outline", value); }

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
    
    // --- Flexbox ---
    public static CssProperty flex_direction(FlexDirection value) { return prop("flex-direction", value); }
    public static CssProperty flex_direction(CharSequence value) { return prop("flex-direction", value); }

    public static CssProperty justify_content(JustifyContent value) { return prop("justify-content", value); }
    public static CssProperty justify_content(CharSequence value) { return prop("justify-content", value); }

    public static CssProperty align_items(AlignItems value) { return prop("align-items", value); }
    public static CssProperty align_items(CharSequence value) { return prop("align-items", value); }

    public static CssProperty gap(CharSequence value) { return prop("gap", value); }

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

    // --- Animation ---
    public static CssProperty animation(CharSequence value) { return prop("animation", value); }
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