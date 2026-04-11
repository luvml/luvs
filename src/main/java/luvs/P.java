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

    private static String joinSpace(CharSequence... values) {
        return Stream.of(values).map(P::toStr).collect(Collectors.joining(" "));
    }

    private static String joinComma(CharSequence... values) {
        return Stream.of(values).map(P::toStr).collect(Collectors.joining(", "));
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

    // Font shorthand: font-style font-variant font-weight font-size/line-height font-family
    // Example: font("italic small-caps bold 16px/1.5 Arial, sans-serif")
    public static CssProperty font(CharSequence value) { return prop("font", value); }

    public static CssProperty text_align(TextAlign value) { return prop("text-align", value); }
    public static CssProperty text_align(CharSequence value) { return prop("text-align", value); }

    public static CssProperty text_decoration(CharSequence value) { return prop("text-decoration", value); }

    public static CssProperty line_height(CharSequence value) { return prop("line-height", value); }
    public static CssProperty line_height(Number value) { return prop("line-height", value.toString()); }

    public static CssProperty font_style(FontStyle value) { return prop("font-style", value); }
    public static CssProperty font_style(CharSequence value) { return prop("font-style", value); }

    public static CssProperty font_variant(CharSequence value) { return prop("font-variant", value); }
    public static CssProperty font_stretch(CharSequence value) { return prop("font-stretch", value); }

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
    public static CssProperty border_width(CharSequence... values) { return prop("border-width", joinValues(values)); }
    public static CssProperty border_style(CharSequence value) { return prop("border-style", value); }

    // Individual border side properties
    public static CssProperty border_top_width(CharSequence value) { return prop("border-top-width", value); }
    public static CssProperty border_top_style(CharSequence value) { return prop("border-top-style", value); }
    public static CssProperty border_top_color(CharSequence value) { return prop("border-top-color", value); }
    public static CssProperty border_right_width(CharSequence value) { return prop("border-right-width", value); }
    public static CssProperty border_right_style(CharSequence value) { return prop("border-right-style", value); }
    public static CssProperty border_right_color(CharSequence value) { return prop("border-right-color", value); }
    public static CssProperty border_bottom_width(CharSequence value) { return prop("border-bottom-width", value); }
    public static CssProperty border_bottom_style(CharSequence value) { return prop("border-bottom-style", value); }
    public static CssProperty border_bottom_color(CharSequence value) { return prop("border-bottom-color", value); }
    public static CssProperty border_left_width(CharSequence value) { return prop("border-left-width", value); }
    public static CssProperty border_left_style(CharSequence value) { return prop("border-left-style", value); }
    public static CssProperty border_left_color(CharSequence value) { return prop("border-left-color", value); }

    public static CssProperty border_radius(CharSequence... values) { return prop("border-radius", joinValues(values)); }
    public static CssProperty border_top_left_radius(CharSequence value) { return prop("border-top-left-radius", value); }
    public static CssProperty border_top_right_radius(CharSequence value) { return prop("border-top-right-radius", value); }
    public static CssProperty border_bottom_left_radius(CharSequence value) { return prop("border-bottom-left-radius", value); }
    public static CssProperty border_bottom_right_radius(CharSequence value) { return prop("border-bottom-right-radius", value); }
    public static CssProperty border_collapse(BorderCollapse value) { return prop("border-collapse", value); }
    public static CssProperty border_collapse(CharSequence value) { return prop("border-collapse", value); }

    public static CssProperty outline(CharSequence value) { return prop("outline", value); }

    public static CssProperty box_sizing(BoxSizing value) { return prop("box-sizing", value); }
    public static CssProperty box_sizing(CharSequence value) { return prop("box-sizing", value); }

    public static CssProperty box_decoration_break(CharSequence value) { return prop("box-decoration-break", value); }

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

    public static CssProperty visibility(CharSequence value) { return prop("visibility", value); }

    public static CssProperty float_(CharSequence value) { return prop("float", value); }
    public static CssProperty clear(CharSequence value) { return prop("clear", value); }

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

    public static CssProperty align_self(CharSequence value) { return prop("align-self", value); }
    public static CssProperty align_content(CharSequence value) { return prop("align-content", value); }

    public static CssProperty justify_items(CharSequence value) { return prop("justify-items", value); }
    public static CssProperty justify_self(CharSequence value) { return prop("justify-self", value); }

    // Alignment shorthands
    public static CssProperty place_items(CharSequence value) { return prop("place-items", value); }
    public static CssProperty place_content(CharSequence value) { return prop("place-content", value); }
    public static CssProperty place_self(CharSequence value) { return prop("place-self", value); }

    public static CssProperty flex(Flex value) { return prop("flex", value); }
    public static CssProperty flex(CharSequence value) { return prop("flex", value); }

    public static CssProperty flex_wrap(FlexWrap value) { return prop("flex-wrap", value); }
    public static CssProperty flex_wrap(CharSequence value) { return prop("flex-wrap", value); }
    public static CssProperty flex_flow(CharSequence value) { return prop("flex-flow", value); }
    public static CssProperty flex_shrink(CharSequence value) { return prop("flex-shrink", value); }
    public static CssProperty flex_grow(CharSequence value) { return prop("flex-grow", value); }
    public static CssProperty flex_basis(CharSequence value) { return prop("flex-basis", value); }
    public static CssProperty order(int value) { return prop("order", String.valueOf(value)); }
    public static CssProperty order(CharSequence value) { return prop("order", value); }

    public static CssProperty gap(CharSequence value) { return prop("gap", value); }

    // --- Grid ---
    public static CssProperty grid(CharSequence value) { return prop("grid", value); }
    public static CssProperty grid_template_columns(CharSequence value) { return prop("grid-template-columns", value); }
    public static CssProperty grid_template_rows(CharSequence value) { return prop("grid-template-rows", value); }
    public static CssProperty grid_template(CharSequence value) { return prop("grid-template", value); }
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

    // Individual transform properties (modern CSS)
    public static CssProperty rotate(CharSequence value) { return prop("rotate", value); }
    public static CssProperty scale(CharSequence value) { return prop("scale", value); }
    public static CssProperty translate(CharSequence value) { return prop("translate", value); }

    public static CssProperty filter(CharSequence value) { return prop("filter", value); }

    // --- Pseudo-element content ---
    public static CssProperty content(CharSequence value) { return prop("content", value); }

    // --- CSS Counters ---
    public static CssProperty counter_reset(CharSequence value) { return prop("counter-reset", value); }
    public static CssProperty counter_increment(CharSequence value) { return prop("counter-increment", value); }

    // --- Opacity ---
    public static CssProperty opacity(Number value) { return prop("opacity", value.toString()); }
    public static CssProperty opacity(CharSequence value) { return prop("opacity", value); }

    // --- All (resets all properties) ---
    public static CssProperty all(CharSequence value) { return prop("all", value); }

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
    public static CssProperty animation_play_state(CharSequence value) { return prop("animation-play-state", value); }

    // --- Backdrop Filter ---
    public static CssProperty backdrop_filter(CharSequence value) { return prop("backdrop-filter", value); }

    // --- Clip Path ---
    public static CssProperty clip_path(CharSequence value) { return prop("clip-path", value); }

    // --- Mask Properties ---
    public static CssProperty mask(CharSequence value) { return prop("mask", value); }
    public static CssProperty mask_image(CharSequence value) { return prop("mask-image", value); }
    public static CssProperty mask_mode(CharSequence value) { return prop("mask-mode", value); }
    public static CssProperty mask_repeat(CharSequence value) { return prop("mask-repeat", value); }
    public static CssProperty mask_position(CharSequence value) { return prop("mask-position", value); }
    public static CssProperty mask_clip(CharSequence value) { return prop("mask-clip", value); }
    public static CssProperty mask_origin(CharSequence value) { return prop("mask-origin", value); }
    public static CssProperty mask_size(CharSequence value) { return prop("mask-size", value); }
    public static CssProperty mask_composite(CharSequence value) { return prop("mask-composite", value); }

    // --- Scroll Properties ---
    public static CssProperty scroll_behavior(CharSequence value) { return prop("scroll-behavior", value); }
    public static CssProperty scroll_snap_type(CharSequence value) { return prop("scroll-snap-type", value); }
    public static CssProperty scroll_snap_align(CharSequence value) { return prop("scroll-snap-align", value); }
    public static CssProperty scroll_snap_stop(CharSequence value) { return prop("scroll-snap-stop", value); }
    public static CssProperty scroll_margin(CharSequence... values) { return prop("scroll-margin", joinSpace(values)); }
    public static CssProperty scroll_margin_top(CharSequence value) { return prop("scroll-margin-top", value); }
    public static CssProperty scroll_margin_right(CharSequence value) { return prop("scroll-margin-right", value); }
    public static CssProperty scroll_margin_bottom(CharSequence value) { return prop("scroll-margin-bottom", value); }
    public static CssProperty scroll_margin_left(CharSequence value) { return prop("scroll-margin-left", value); }
    public static CssProperty scroll_margin_block(CharSequence... values) { return prop("scroll-margin-block", joinSpace(values)); }
    public static CssProperty scroll_margin_block_start(CharSequence value) { return prop("scroll-margin-block-start", value); }
    public static CssProperty scroll_margin_block_end(CharSequence value) { return prop("scroll-margin-block-end", value); }
    public static CssProperty scroll_margin_inline(CharSequence... values) { return prop("scroll-margin-inline", joinSpace(values)); }
    public static CssProperty scroll_margin_inline_start(CharSequence value) { return prop("scroll-margin-inline-start", value); }
    public static CssProperty scroll_margin_inline_end(CharSequence value) { return prop("scroll-margin-inline-end", value); }
    public static CssProperty scroll_padding(CharSequence... values) { return prop("scroll-padding", joinSpace(values)); }
    public static CssProperty scroll_padding_top(CharSequence value) { return prop("scroll-padding-top", value); }
    public static CssProperty scroll_padding_right(CharSequence value) { return prop("scroll-padding-right", value); }
    public static CssProperty scroll_padding_bottom(CharSequence value) { return prop("scroll-padding-bottom", value); }
    public static CssProperty scroll_padding_left(CharSequence value) { return prop("scroll-padding-left", value); }
    public static CssProperty scroll_padding_block(CharSequence... values) { return prop("scroll-padding-block", joinSpace(values)); }
    public static CssProperty scroll_padding_block_start(CharSequence value) { return prop("scroll-padding-block-start", value); }
    public static CssProperty scroll_padding_block_end(CharSequence value) { return prop("scroll-padding-block-end", value); }
    public static CssProperty scroll_padding_inline(CharSequence... values) { return prop("scroll-padding-inline", joinSpace(values)); }
    public static CssProperty scroll_padding_inline_start(CharSequence value) { return prop("scroll-padding-inline-start", value); }
    public static CssProperty scroll_padding_inline_end(CharSequence value) { return prop("scroll-padding-inline-end", value); }
    public static CssProperty overscroll_behavior(CharSequence value) { return prop("overscroll-behavior", value); }
    public static CssProperty overscroll_behavior_x(CharSequence value) { return prop("overscroll-behavior-x", value); }
    public static CssProperty overscroll_behavior_y(CharSequence value) { return prop("overscroll-behavior-y", value); }
    public static CssProperty overscroll_behavior_block(CharSequence value) { return prop("overscroll-behavior-block", value); }
    public static CssProperty overscroll_behavior_inline(CharSequence value) { return prop("overscroll-behavior-inline", value); }

    // --- Performance Hints ---
    public static CssProperty will_change(CharSequence... properties) { return prop("will-change", joinComma(properties)); }
    public static CssProperty contain(CharSequence value) { return prop("contain", value); }
    public static CssProperty content_visibility(CharSequence value) { return prop("content-visibility", value); }
    public static CssProperty contain_intrinsic_size(CharSequence value) { return prop("contain-intrinsic-size", value); }
    public static CssProperty contain_intrinsic_width(CharSequence value) { return prop("contain-intrinsic-width", value); }
    public static CssProperty contain_intrinsic_height(CharSequence value) { return prop("contain-intrinsic-height", value); }

    // --- Interaction Properties ---
    public static CssProperty user_select(CharSequence value) { return prop("user-select", value); }
    public static CssProperty pointer_events(CharSequence value) { return prop("pointer-events", value); }
    public static CssProperty touch_action(CharSequence value) { return prop("touch-action", value); }

    // --- Aspect Ratio ---
    public static CssProperty aspect_ratio(CharSequence value) { return prop("aspect-ratio", value); }
    public static CssProperty aspect_ratio(int width, int height) { return prop("aspect-ratio", width + " / " + height); }

    // --- Logical Properties (Inline/Block) ---
    // Margin
    public static CssProperty margin_inline(CharSequence... values) { return prop("margin-inline", joinSpace(values)); }
    public static CssProperty margin_inline_start(CharSequence value) { return prop("margin-inline-start", value); }
    public static CssProperty margin_inline_end(CharSequence value) { return prop("margin-inline-end", value); }
    public static CssProperty margin_block(CharSequence... values) { return prop("margin-block", joinSpace(values)); }
    public static CssProperty margin_block_start(CharSequence value) { return prop("margin-block-start", value); }
    public static CssProperty margin_block_end(CharSequence value) { return prop("margin-block-end", value); }

    // Padding
    public static CssProperty padding_inline(CharSequence... values) { return prop("padding-inline", joinSpace(values)); }
    public static CssProperty padding_inline_start(CharSequence value) { return prop("padding-inline-start", value); }
    public static CssProperty padding_inline_end(CharSequence value) { return prop("padding-inline-end", value); }
    public static CssProperty padding_block(CharSequence... values) { return prop("padding-block", joinSpace(values)); }
    public static CssProperty padding_block_start(CharSequence value) { return prop("padding-block-start", value); }
    public static CssProperty padding_block_end(CharSequence value) { return prop("padding-block-end", value); }

    // Border
    public static CssProperty border_inline(CharSequence... values) { return prop("border-inline", joinSpace(values)); }
    public static CssProperty border_inline_start(CharSequence... values) { return prop("border-inline-start", joinSpace(values)); }
    public static CssProperty border_inline_end(CharSequence... values) { return prop("border-inline-end", joinSpace(values)); }
    public static CssProperty border_block(CharSequence... values) { return prop("border-block", joinSpace(values)); }
    public static CssProperty border_block_start(CharSequence... values) { return prop("border-block-start", joinSpace(values)); }
    public static CssProperty border_block_end(CharSequence... values) { return prop("border-block-end", joinSpace(values)); }

    // Border Width
    public static CssProperty border_inline_width(CharSequence value) { return prop("border-inline-width", value); }
    public static CssProperty border_inline_start_width(CharSequence value) { return prop("border-inline-start-width", value); }
    public static CssProperty border_inline_end_width(CharSequence value) { return prop("border-inline-end-width", value); }
    public static CssProperty border_block_width(CharSequence value) { return prop("border-block-width", value); }
    public static CssProperty border_block_start_width(CharSequence value) { return prop("border-block-start-width", value); }
    public static CssProperty border_block_end_width(CharSequence value) { return prop("border-block-end-width", value); }

    // Border Color
    public static CssProperty border_inline_color(CharSequence value) { return prop("border-inline-color", value); }
    public static CssProperty border_inline_start_color(CharSequence value) { return prop("border-inline-start-color", value); }
    public static CssProperty border_inline_end_color(CharSequence value) { return prop("border-inline-end-color", value); }
    public static CssProperty border_block_color(CharSequence value) { return prop("border-block-color", value); }
    public static CssProperty border_block_start_color(CharSequence value) { return prop("border-block-start-color", value); }
    public static CssProperty border_block_end_color(CharSequence value) { return prop("border-block-end-color", value); }

    // Border Style
    public static CssProperty border_inline_style(CharSequence value) { return prop("border-inline-style", value); }
    public static CssProperty border_inline_start_style(CharSequence value) { return prop("border-inline-start-style", value); }
    public static CssProperty border_inline_end_style(CharSequence value) { return prop("border-inline-end-style", value); }
    public static CssProperty border_block_style(CharSequence value) { return prop("border-block-style", value); }
    public static CssProperty border_block_start_style(CharSequence value) { return prop("border-block-start-style", value); }
    public static CssProperty border_block_end_style(CharSequence value) { return prop("border-block-end-style", value); }

    // Positioning
    public static CssProperty inset(CharSequence... values) { return prop("inset", joinSpace(values)); }
    public static CssProperty inset_inline(CharSequence... values) { return prop("inset-inline", joinSpace(values)); }
    public static CssProperty inset_inline_start(CharSequence value) { return prop("inset-inline-start", value); }
    public static CssProperty inset_inline_end(CharSequence value) { return prop("inset-inline-end", value); }
    public static CssProperty inset_block(CharSequence... values) { return prop("inset-block", joinSpace(values)); }
    public static CssProperty inset_block_start(CharSequence value) { return prop("inset-block-start", value); }
    public static CssProperty inset_block_end(CharSequence value) { return prop("inset-block-end", value); }

    // Size
    public static CssProperty inline_size(CharSequence value) { return prop("inline-size", value); }
    public static CssProperty block_size(CharSequence value) { return prop("block-size", value); }
    public static CssProperty min_inline_size(CharSequence value) { return prop("min-inline-size", value); }
    public static CssProperty min_block_size(CharSequence value) { return prop("min-block-size", value); }
    public static CssProperty max_inline_size(CharSequence value) { return prop("max-inline-size", value); }
    public static CssProperty max_block_size(CharSequence value) { return prop("max-block-size", value); }

    // --- Tier 2: Text Decoration Properties ---
    public static CssProperty text_decoration_line(CharSequence value) { return prop("text-decoration-line", value); }
    public static CssProperty text_decoration_color(CharSequence value) { return prop("text-decoration-color", value); }
    public static CssProperty text_decoration_style(CharSequence value) { return prop("text-decoration-style", value); }
    public static CssProperty text_decoration_thickness(CharSequence value) { return prop("text-decoration-thickness", value); }
    public static CssProperty text_underline_offset(CharSequence value) { return prop("text-underline-offset", value); }

    // --- Tier 2: Outline Offset ---
    public static CssProperty outline_offset(CharSequence value) { return prop("outline-offset", value); }

    // --- Tier 2: Container Queries ---
    public static CssProperty container_type(CharSequence value) { return prop("container-type", value); }
    public static CssProperty container_name(CharSequence value) { return prop("container-name", value); }
    public static CssProperty container(CharSequence... values) { return prop("container", joinSpace(values)); }

    // --- Tier 2: Grid Named Areas ---
    public static CssProperty grid_template_areas(CharSequence value) { return prop("grid-template-areas", value); }
    public static CssProperty grid_area(CharSequence value) { return prop("grid-area", value); }

    // --- Tier 3: Text Wrap ---
    public static CssProperty text_wrap(CharSequence value) { return prop("text-wrap", value); }

    // =============================================================================
    // MISSING STANDARD PROPERTIES (Added after audit)
    // =============================================================================

    // --- ⭐⭐⭐ CRITICAL: Text Overflow & Word Breaking ---
    public static CssProperty text_overflow(CharSequence value) { return prop("text-overflow", value); }
    public static CssProperty word_break(CharSequence value) { return prop("word-break", value); }
    public static CssProperty overflow_wrap(CharSequence value) { return prop("overflow-wrap", value); }
    public static CssProperty word_wrap(CharSequence value) { return prop("word-wrap", value); } // Alias for overflow-wrap
    public static CssProperty line_break(CharSequence value) { return prop("line-break", value); }

    // --- ⭐⭐⭐ CRITICAL: Object Position (goes with object-fit) ---
    public static CssProperty object_position(CharSequence value) { return prop("object-position", value); }

    // --- ⭐⭐⭐ CRITICAL: Form Styling ---
    public static CssProperty appearance(CharSequence value) { return prop("appearance", value); }

    // --- ⭐⭐⭐ CRITICAL: Webkit Multi-line Ellipsis (vendor-prefixed but essential) ---
    public static CssProperty webkit_line_clamp(CharSequence lines) { return prop("-webkit-line-clamp", lines); }
    public static CssProperty webkit_box_orient(CharSequence orientation) { return prop("-webkit-box-orient", orientation); }

    // --- ⭐⭐ HIGH: Typography & Text ---
    public static CssProperty text_transform(CharSequence value) { return prop("text-transform", value); }
    public static CssProperty vertical_align(CharSequence value) { return prop("vertical-align", value); }
    public static CssProperty letter_spacing(CharSequence value) { return prop("letter-spacing", value); }
    public static CssProperty word_spacing(CharSequence value) { return prop("word-spacing", value); }
    public static CssProperty text_indent(CharSequence value) { return prop("text-indent", value); }
    public static CssProperty text_align_last(CharSequence value) { return prop("text-align-last", value); }
    public static CssProperty text_shadow(CharSequence value) { return prop("text-shadow", value); }
    public static CssProperty hyphens(CharSequence value) { return prop("hyphens", value); }
    public static CssProperty text_rendering(CharSequence value) { return prop("text-rendering", value); }

    // --- ⭐⭐ HIGH: UI & Forms ---
    public static CssProperty caret_color(CharSequence value) { return prop("caret-color", value); }
    public static CssProperty accent_color(CharSequence value) { return prop("accent-color", value); }

    // --- ⭐⭐ HIGH: Lists ---
    public static CssProperty list_style_type(CharSequence value) { return prop("list-style-type", value); }
    public static CssProperty list_style_position(CharSequence value) { return prop("list-style-position", value); }
    public static CssProperty list_style_image(CharSequence value) { return prop("list-style-image", value); }
    public static CssProperty list_style(CharSequence value) { return prop("list-style", value); }

    // --- ⭐⭐ HIGH: Outline (complete the set) ---
    public static CssProperty outline_style(CharSequence value) { return prop("outline-style", value); }
    public static CssProperty outline_color(CharSequence value) { return prop("outline-color", value); }
    public static CssProperty outline_width(CharSequence value) { return prop("outline-width", value); }

    // --- ⭐⭐ HIGH: Multi-column Layout ---
    public static CssProperty columns(CharSequence value) { return prop("columns", value); }
    public static CssProperty column_count(CharSequence value) { return prop("column-count", value); }
    public static CssProperty column_width(CharSequence value) { return prop("column-width", value); }
    public static CssProperty column_gap(CharSequence value) { return prop("column-gap", value); }
    public static CssProperty row_gap(CharSequence value) { return prop("row-gap", value); }
    public static CssProperty column_rule(CharSequence value) { return prop("column-rule", value); }
    public static CssProperty column_rule_color(CharSequence value) { return prop("column-rule-color", value); }
    public static CssProperty column_rule_style(CharSequence value) { return prop("column-rule-style", value); }
    public static CssProperty column_rule_width(CharSequence value) { return prop("column-rule-width", value); }
    public static CssProperty column_span(CharSequence value) { return prop("column-span", value); }
    public static CssProperty column_fill(CharSequence value) { return prop("column-fill", value); }

    // --- ⭐⭐ HIGH: Scrollbars (Firefox) ---
    public static CssProperty scrollbar_width(CharSequence value) { return prop("scrollbar-width", value); }
    public static CssProperty scrollbar_color(CharSequence value) { return prop("scrollbar-color", value); }

    // --- ⭐ MEDIUM: Background Properties ---
    public static CssProperty background_position(CharSequence value) { return prop("background-position", value); }
    public static CssProperty background_size(CharSequence value) { return prop("background-size", value); }
    public static CssProperty background_repeat(CharSequence value) { return prop("background-repeat", value); }
    public static CssProperty background_attachment(CharSequence value) { return prop("background-attachment", value); }
    public static CssProperty background_origin(CharSequence value) { return prop("background-origin", value); }
    public static CssProperty background_clip(CharSequence value) { return prop("background-clip", value); }

    // --- ⭐ MEDIUM: Transform Properties ---
    public static CssProperty transform_origin(CharSequence value) { return prop("transform-origin", value); }
    public static CssProperty transform_style(CharSequence value) { return prop("transform-style", value); }
    public static CssProperty perspective(CharSequence value) { return prop("perspective", value); }
    public static CssProperty perspective_origin(CharSequence value) { return prop("perspective-origin", value); }
    public static CssProperty backface_visibility(CharSequence value) { return prop("backface-visibility", value); }

    // --- ⭐ MEDIUM: Grid Properties (extended) ---
    public static CssProperty grid_column_start(CharSequence value) { return prop("grid-column-start", value); }
    public static CssProperty grid_column_end(CharSequence value) { return prop("grid-column-end", value); }
    public static CssProperty grid_row_start(CharSequence value) { return prop("grid-row-start", value); }
    public static CssProperty grid_row_end(CharSequence value) { return prop("grid-row-end", value); }
    public static CssProperty grid_auto_rows(CharSequence value) { return prop("grid-auto-rows", value); }
    public static CssProperty grid_auto_columns(CharSequence value) { return prop("grid-auto-columns", value); }
    public static CssProperty grid_auto_flow(CharSequence value) { return prop("grid-auto-flow", value); }

    // --- ⭐ MEDIUM: Tables ---
    public static CssProperty border_spacing(CharSequence value) { return prop("border-spacing", value); }
    public static CssProperty caption_side(CharSequence value) { return prop("caption-side", value); }
    public static CssProperty empty_cells(CharSequence value) { return prop("empty-cells", value); }
    public static CssProperty table_layout(CharSequence value) { return prop("table-layout", value); }

    // --- ⭐ MEDIUM: Effects & Blend Modes ---
    public static CssProperty mix_blend_mode(CharSequence value) { return prop("mix-blend-mode", value); }
    public static CssProperty isolation(CharSequence value) { return prop("isolation", value); }

    // --- ⭐ MEDIUM: Typography Control (Print) ---
    public static CssProperty orphans(CharSequence value) { return prop("orphans", value); }
    public static CssProperty widows(CharSequence value) { return prop("widows", value); }

    // --- ⭐ MEDIUM: Text & Writing ---
    public static CssProperty direction(CharSequence value) { return prop("direction", value); }
    public static CssProperty writing_mode(CharSequence value) { return prop("writing-mode", value); }
    public static CssProperty unicode_bidi(CharSequence value) { return prop("unicode-bidi", value); }
    public static CssProperty tab_size(CharSequence value) { return prop("tab-size", value); }
    public static CssProperty quotes(CharSequence value) { return prop("quotes", value); }

    // --- ⭐ MEDIUM: Page Breaks (Print) ---
    public static CssProperty page_break_before(CharSequence value) { return prop("page-break-before", value); }
    public static CssProperty page_break_after(CharSequence value) { return prop("page-break-after", value); }
    public static CssProperty page_break_inside(CharSequence value) { return prop("page-break-inside", value); }
    public static CssProperty break_before(CharSequence value) { return prop("break-before", value); }
    public static CssProperty break_after(CharSequence value) { return prop("break-after", value); }
    public static CssProperty break_inside(CharSequence value) { return prop("break-inside", value); }

    // --- ⭐ MEDIUM: Rendering ---
    public static CssProperty image_rendering(CharSequence value) { return prop("image-rendering", value); }

    // --- LOWER: Border Image (complete set for rare use) ---
    public static CssProperty border_image(CharSequence value) { return prop("border-image", value); }
    public static CssProperty border_image_source(CharSequence value) { return prop("border-image-source", value); }
    public static CssProperty border_image_slice(CharSequence value) { return prop("border-image-slice", value); }
    public static CssProperty border_image_width(CharSequence value) { return prop("border-image-width", value); }
    public static CssProperty border_image_outset(CharSequence value) { return prop("border-image-outset", value); }
    public static CssProperty border_image_repeat(CharSequence value) { return prop("border-image-repeat", value); }

    // --- LOWER: Background Blend ---
    public static CssProperty background_blend_mode(CharSequence value) { return prop("background-blend-mode", value); }

    // --- SVG Properties (common with inline SVG) ---
    public static CssProperty fill(CharSequence value) { return prop("fill", value); }
    public static CssProperty stroke(CharSequence value) { return prop("stroke", value); }
    public static CssProperty stroke_width(CharSequence value) { return prop("stroke-width", value); }
    public static CssProperty stroke_dasharray(CharSequence value) { return prop("stroke-dasharray", value); }
    public static CssProperty stroke_dashoffset(CharSequence value) { return prop("stroke-dashoffset", value); }
    public static CssProperty stroke_linecap(CharSequence value) { return prop("stroke-linecap", value); }
    public static CssProperty stroke_linejoin(CharSequence value) { return prop("stroke-linejoin", value); }
    public static CssProperty stroke_miterlimit(CharSequence value) { return prop("stroke-miterlimit", value); }
    public static CssProperty stroke_opacity(CharSequence value) { return prop("stroke-opacity", value); }
    public static CssProperty fill_opacity(CharSequence value) { return prop("fill-opacity", value); }
    public static CssProperty fill_rule(CharSequence value) { return prop("fill-rule", value); }
    public static CssProperty paint_order(CharSequence value) { return prop("paint-order", value); }
    public static CssProperty vector_effect(CharSequence value) { return prop("vector-effect", value); }
    public static CssProperty marker(CharSequence value) { return prop("marker", value); }
    public static CssProperty marker_start(CharSequence value) { return prop("marker-start", value); }
    public static CssProperty marker_mid(CharSequence value) { return prop("marker-mid", value); }
    public static CssProperty marker_end(CharSequence value) { return prop("marker-end", value); }

    // --- CSS Shapes (modern) ---
    public static CssProperty shape_outside(CharSequence value) { return prop("shape-outside", value); }
    public static CssProperty shape_margin(CharSequence value) { return prop("shape-margin", value); }
    public static CssProperty shape_image_threshold(CharSequence value) { return prop("shape-image-threshold", value); }

    // --- Legacy Clipping ---
    public static CssProperty clip(CharSequence value) { return prop("clip", value); }

    // --- Mobile/Text Properties ---
    public static CssProperty text_size_adjust(CharSequence value) { return prop("text-size-adjust", value); }
    public static CssProperty webkit_tap_highlight_color(CharSequence value) { return prop("-webkit-tap-highlight-color", value); }

    // --- Color Scheme (Dark Mode) ---
    public static CssProperty color_scheme(CharSequence value) { return prop("color-scheme", value); }

    // --- Accessibility & Print ---
    public static CssProperty forced_color_adjust(CharSequence value) { return prop("forced-color-adjust", value); }
    public static CssProperty print_color_adjust(CharSequence value) { return prop("print-color-adjust", value); }

    // --- Advanced Typography ---
    public static CssProperty hanging_punctuation(CharSequence value) { return prop("hanging-punctuation", value); }
    public static CssProperty initial_letter(CharSequence value) { return prop("initial-letter", value); }

    // --- Text Emphasis (East Asian typography) ---
    public static CssProperty text_emphasis(CharSequence value) { return prop("text-emphasis", value); }
    public static CssProperty text_emphasis_style(CharSequence value) { return prop("text-emphasis-style", value); }
    public static CssProperty text_emphasis_color(CharSequence value) { return prop("text-emphasis-color", value); }

    // --- @page Properties (Print) ---
    public static CssProperty size(CharSequence value) { return prop("size", value); }
    public static CssProperty marks(CharSequence value) { return prop("marks", value); }
    public static CssProperty bleed(CharSequence value) { return prop("bleed", value); }

    // =============================================================================

    // --- Custom ---
    /**
     * For setting CSS variables like --main-color: #123456;
     */
    public static CssProperty custom_var(String name, CharSequence value) {
        return prop("--" + name, value);
    }
}