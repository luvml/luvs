package luvs;

import org.junit.jupiter.api.Test;

import static luvs.CssRules.*;
import static luvs.HtmlTag.*;
import static luvs.P.*;
import static luvs.V.*;
import static luvs.FontDisplay.*;
import static luvs.MediaQuery.*;
import static luvs.ContainerQuery.*;

/**
 * Tests for PRP-05: All new CSS features (Tier 1, 2, and 3)
 */
class NewFeaturesTest {

    @Test
    void testFontFace() {
        var css = rulesFrom(
            // Basic @font-face
            FontFace.fontFace(
                FontFace.fontFamily("CustomFont"),
                FontFace.src(
                    FontFace.url("fonts/custom.woff2"),
                    FontFace.format("woff2")
                ),
                FontFace.fontWeight("400"),
                FontFace.fontStyle("normal"),
                FontFace.fontDisplay(SWAP)
            ),

            // Font-face with multiple sources and fallbacks
            FontFace.fontFace(
                FontFace.fontFamily("MyFont"),
                FontFace.src(
                    FontFace.local("My Font"),
                    FontFace.urlFormat("fonts/myfont.woff2", "woff2"),
                    FontFace.urlFormat("fonts/myfont.woff", "woff")
                ),
                FontFace.fontWeight("300"), // or fontWeight(300, 700) for variable font range
                FontFace.unicodeRange("U+0025-00FF")
            )
        );

        String result = css.toString();
        System.out.println("=== @font-face ===");
        System.out.println(result);
        System.out.println("Result length: " + result.length());
        System.out.println("Result isEmpty: " + result.isEmpty());

        assert !result.isEmpty() : "Result is empty!";
        assert result.contains("@font-face") : "Missing @font-face in: " + result;
        assert result.contains("font-family: \"CustomFont\"");
        assert result.contains("font-display: swap");
    }

    @Test
    void testSupports() {
        var css = rulesFrom(
            // Basic feature query
            Supports.supports(
                Supports.property("display", "grid"),
                div.____( display(GRID) )
            ),

            // Logical operators (using instance methods)
            Supports.supports(
                Supports.property("display", "flex").and(Supports.property("gap", "1rem")),
                div.____( display(FLEX), gap(rem(1)) )
            ),

            // OR condition
            Supports.supports(
                Supports.property("backdrop-filter", "blur(10px)")
                    .or(Supports.property("-webkit-backdrop-filter", "blur(10px)")),
                div.____( backdrop_filter("blur(10px)") )
            ),

            // NOT condition
            Supports.supports(
                Supports.SupportsCondition.not(Supports.property("display", "grid")),
                div.____( display(FLEX) )
            )
        );

        String result = css.toString();
        System.out.println("\n=== @supports ===");
        System.out.println(result);

        assert result.contains("@supports");
        assert result.contains("(display: grid)");
        assert result.contains(" and ");
    }

    @Test
    void testBackdropFilterAndClipPath() {
        var css = rules(
            div.____(
                backdrop_filter("blur(10px) saturate(180%)"),
                clip_path("polygon(50% 0%, 100% 50%, 50% 100%, 0% 50%)")
            )
        );

        String result = css.toString();
        System.out.println("\n=== backdrop-filter & clip-path ===");
        System.out.println(result);

        assert result.contains("backdrop-filter: blur(10px)");
        assert result.contains("clip-path: polygon");
    }

    @Test
    void testScrollProperties() {
        var css = rules(
            body.____(
                scroll_behavior("smooth")
            ),

            div.____(
                scroll_snap_type("y mandatory"),
                scroll_snap_align("start"),
                scroll_snap_stop("always"),
                scroll_margin(px(20)),
                scroll_margin_top(px(10)),
                scroll_padding(px(0), px(20)),
                overscroll_behavior("contain"),
                overscroll_behavior_x("none"),
                overscroll_behavior_y("auto")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Scroll Properties ===");
        System.out.println(result);

        assert result.contains("scroll-behavior: smooth");
        assert result.contains("scroll-snap-type: y mandatory");
        assert result.contains("overscroll-behavior: contain");
    }

    @Test
    void testPerformanceHints() {
        var css = rules(
            div.____(
                will_change("transform", "opacity"),
                contain("layout style"),
                content_visibility("auto")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Performance Hints ===");
        System.out.println(result);

        assert result.contains("will-change: transform, opacity");
        assert result.contains("contain: layout style");
        assert result.contains("content-visibility: auto");
    }

    @Test
    void testInteractionProperties() {
        var css = rules(
            div.____(
                user_select("none"),
                pointer_events("none"),
                touch_action("pan-y pinch-zoom")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Interaction Properties ===");
        System.out.println(result);

        assert result.contains("user-select: none");
        assert result.contains("pointer-events: none");
        assert result.contains("touch-action: pan-y");
    }

    @Test
    void testAspectRatio() {
        var css = rules(
            div.____(
                aspect_ratio(16, 9)
            ),

            img.____(
                aspect_ratio("1 / 1")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Aspect Ratio ===");
        System.out.println(result);

        assert result.contains("aspect-ratio: 16 / 9");
        assert result.contains("aspect-ratio: 1 / 1");
    }

    @Test
    void testLogicalProperties() {
        var css = rules(
            div.____(
                // Margin
                margin_inline(px(20)),
                margin_inline_start(px(10)),
                margin_inline_end(px(10)),
                margin_block(px(30)),
                margin_block_start(px(15)),
                margin_block_end(px(15)),

                // Padding
                padding_inline(px(20)),
                padding_block(px(10)),

                // Border
                border_inline("1px solid black"),
                border_block_start_color("red"),
                border_inline_end_width(px(2)),

                // Positioning
                inset(px(0)),
                inset_inline_start(px(20)),
                inset_block_end(px(10)),

                // Size
                inline_size(percent(100)),
                block_size("auto"),
                min_inline_size(px(200)),
                max_block_size(px(500))
            )
        );

        String result = css.toString();
        System.out.println("\n=== Logical Properties ===");
        System.out.println(result);

        assert result.contains("margin-inline: 20px");
        assert result.contains("padding-block: 10px");
        assert result.contains("border-inline: 1px solid black");
        assert result.contains("inline-size: 100%");
    }

    @Test
    void testContainerQueries() {
        var css = rulesFrom(
            // Define container (using P properties)
            div.____(
                P.container_type("inline-size"),
                P.container_name("card")
            ),

            // Anonymous container query
            ContainerQuery.container(
                ContainerQuery.minWidth(px(400)),
                div.____( font_size(px(18)) )
            ),

            // Named container query with range (using instance methods)
            ContainerQuery.container(
                "card",
                ContainerQuery.minWidth(px(500)).and(ContainerQuery.maxWidth(px(800))),
                div.____(
                    display(GRID),
                    grid_template_columns("repeat(2, 1fr)")
                )
            ),

            // Aspect ratio query
            ContainerQuery.container(
                ContainerQuery.aspectRatio("16/9"),
                div.____( padding(px(20)) )
            )
        );

        String result = css.toString();
        System.out.println("\n=== Container Queries ===");
        System.out.println(result);

        assert result.contains("container-type: inline-size");
        assert result.contains("@container");
        assert result.contains("(min-width: 400px)");
    }

    @Test
    void testTextDecorationAndOutline() {
        var css = rules(
            div.____(
                text_decoration_color("red"),
                text_decoration_style("wavy"),
                text_decoration_thickness(px(2)),
                text_underline_offset(px(3)),
                outline_offset(px(4))
            )
        );

        String result = css.toString();
        System.out.println("\n=== Text Decoration & Outline ===");
        System.out.println(result);

        assert result.contains("text-decoration-color: red");
        assert result.contains("text-decoration-style: wavy");
        assert result.contains("outline-offset: 4px");
    }

    @Test
    void testGridNamedAreas() {
        var css = rules(
            div.____(
                grid_template_areas(
                    "\"header header header\"\n" +
                    "\"sidebar main main\"\n" +
                    "\"footer footer footer\""
                ),
                grid_area("header")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Grid Named Areas ===");
        System.out.println(result);

        assert result.contains("grid-template-areas:");
        assert result.contains("grid-area: header");
    }

    @Test
    void testLayer() {
        var css = rulesFrom(
            // Layer order declaration
            Layer.layerOrder("reset", "base", "components", "utilities"),

            // Anonymous layer
            Layer.layer(
                $all.____( box_sizing(BORDER_BOX) )
            ),

            // Named layer
            Layer.layer("reset",
                body.____( margin(ZERO), padding(ZERO) )
            ),

            // Nested layer
            Layer.layer("components.button",
                div.____( padding(rem(1)) )
            )
        );

        String result = css.toString();
        System.out.println("\n=== @layer ===");
        System.out.println(result);

        assert result.contains("@layer reset, base, components, utilities");
        assert result.contains("@layer reset");
        assert result.contains("@layer components.button");
    }

    @Test
    void testColorMix() {
        var css = rules(
            div.____(
                color(V.colorMix("in srgb", "red", "50%", "blue")),
                background(V.colorMix("in oklch", "blue", "30%", "red")),
                border_color(V.colorMix("in srgb", "red", "blue")) // 50/50 default
            )
        );

        String result = css.toString();
        System.out.println("\n=== color-mix() ===");
        System.out.println(result);

        assert result.contains("color-mix(in srgb, red 50%, blue)");
        assert result.contains("color-mix(in oklch, blue 30%, red)");
    }

    @Test
    void testTextWrap() {
        var css = rules(
            h1.____(
                P.text_wrap("balance")
            ),

            p.____(
                P.text_wrap("pretty")
            )
        );

        String result = css.toString();
        System.out.println("\n=== text-wrap ===");
        System.out.println(result);

        assert result.contains("text-wrap: balance");
        assert result.contains("text-wrap: pretty");
    }

    @Test
    void testSubgrid() {
        var css = rules(
            div.____(
                display(GRID),
                grid_template_columns(V.SUBGRID)
            ),

            div.child(div).____(
                grid_template_rows(V.SUBGRID)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Subgrid ===");
        System.out.println(result);

        assert result.contains("grid-template-columns: subgrid");
        assert result.contains("grid-template-rows: subgrid");
    }

    @Test
    void testRelativeColorSyntax() {
        var css = rules(
            div.____(
                // RGB relative colors
                color(V.rgbFrom("var(--primary)", "r g b / 0.5")),
                background(V.rgbFrom("#ff0000", "r g b / 0.8")),

                // HSL relative colors
                border_color(V.hslFrom("var(--accent)", "h s l / 0.3")),

                // OKLCH (modern color space)
                box_shadow(V.oklchFrom("blue", "l c h / 0.6"))
            )
        );

        String result = css.toString();
        System.out.println("\n=== Relative Color Syntax ===");
        System.out.println(result);

        assert result.contains("rgb(from var(--primary) r g b / 0.5)");
        assert result.contains("hsl(from var(--accent) h s l / 0.3)");
        assert result.contains("oklch(from blue l c h / 0.6)");
    }

    @Test
    void testCompleteRealWorldExample() {
        var css = rulesFrom(
            // Font face
            FontFace.fontFace(
                FontFace.fontFamily("Inter"),
                FontFace.src(FontFace.url("fonts/inter.woff2")),
                FontFace.fontDisplay(SWAP)
            ),

            // Layer order
            Layer.layerOrder("reset", "base", "components"),

            // Reset layer
            Layer.layer("reset",
                $all.____(
                    box_sizing(BORDER_BOX),
                    margin_block(ZERO),
                    padding_inline(ZERO)
                )
            ),

            // Base layer with logical properties
            Layer.layer("base",
                body.____(
                    scroll_behavior("smooth"),
                    user_select("auto")
                )
            ),

            // Container query
            div.____(
                P.container_type("inline-size")
            ),

            ContainerQuery.container(
                ContainerQuery.minWidth(px(600)),
                div.____(
                    display(GRID),
                    grid_template_areas("\"header\"\n\"main\""),
                    gap(rem(2))
                )
            ),

            // Feature query with backdrop filter
            Supports.supports(
                Supports.property("backdrop-filter", "blur(10px)"),
                div.____(
                    backdrop_filter("blur(10px)"),
                    background(V.rgbFrom("white", "r g b / 0.8"))
                )
            )
        );

        String result = css.toString();
        System.out.println("\n=== Complete Real World Example ===");
        System.out.println(result);

        // Verify all features are present
        assert result.contains("@font-face");
        assert result.contains("@layer reset, base, components");
        assert result.contains("@container");
        assert result.contains("@supports");
        assert result.contains("backdrop-filter");
        assert result.contains("scroll-behavior");
        assert result.contains("rgb(from white r g b / 0.8)");

        System.out.println("\n✅ ALL TESTS PASSED!");
    }
}
