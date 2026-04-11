package luvs;

import org.junit.jupiter.api.Test;

import static luvs.CssRules.*;
import static luvs.HtmlTag.*;
import static luvs.P.*;
import static luvs.V.*;

/**
 * Tests for missing standard CSS properties (added after audit)
 */
class MissingPropertiesTest {

    @Test
    void testCriticalProperties() {
        var css = rules(
            // Text overflow & word breaking
            div.____(
                text_overflow(ELLIPSIS),
                word_break(BREAK_WORD),
                overflow_wrap(BREAK_WORD),
                white_space("nowrap")
            ),

            // Multi-line ellipsis (webkit)
            p.____(
                display("-webkit-box"),
                webkit_line_clamp("3"),
                webkit_box_orient(VERTICAL),
                text_overflow(ELLIPSIS),
                overflow(HIDDEN)
            ),

            // Object position
            img.____(
                object_fit("cover"),
                object_position("center top")
            ),

            // Form styling
            input.____(
                appearance(NONE),
                caret_color("blue"),
                accent_color("green")
            )
        );

        String result = css.toString();
        System.out.println("=== Critical Properties ===");
        System.out.println(result);

        assert result.contains("text-overflow: ellipsis");
        assert result.contains("word-break: break-word");
        assert result.contains("-webkit-line-clamp: 3");
        assert result.contains("-webkit-box-orient: vertical");
        assert result.contains("object-position: center top");
        assert result.contains("appearance: none");
        assert result.contains("caret-color: blue");
        assert result.contains("accent-color: green");
    }

    @Test
    void testTypographyProperties() {
        var css = rules(
            div.____(
                text_transform(UPPERCASE),
                letter_spacing(px(2)),
                word_spacing(px(5)),
                text_indent(rem(2)),
                text_align_last("center"),
                text_shadow("2px 2px 4px rgba(0,0,0,0.5)"),
                hyphens(AUTO),
                vertical_align(MIDDLE)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Typography Properties ===");
        System.out.println(result);

        assert result.contains("text-transform: uppercase");
        assert result.contains("letter-spacing: 2px");
        assert result.contains("hyphens: auto");
        assert result.contains("vertical-align: middle");
    }

    @Test
    void testListProperties() {
        var css = rules(
            ul.____(
                list_style_type(DISC),
                list_style_position(OUTSIDE),
                list_style_image("url('marker.png')")
            )
        );

        String result = css.toString();
        System.out.println("\n=== List Properties ===");
        System.out.println(result);

        assert result.contains("list-style-type: disc");
        assert result.contains("list-style-position: outside");
        assert result.contains("list-style-image");
    }

    @Test
    void testOutlineProperties() {
        var css = rules(
            div.____(
                outline("2px solid blue"),
                outline_style("dashed"),
                outline_color("red"),
                outline_width(px(3)),
                outline_offset(px(5))
            )
        );

        String result = css.toString();
        System.out.println("\n=== Outline Properties ===");
        System.out.println(result);

        assert result.contains("outline-style: dashed");
        assert result.contains("outline-color: red");
        assert result.contains("outline-width: 3px");
        assert result.contains("outline-offset: 5px");
    }

    @Test
    void testMultiColumnLayout() {
        var css = rules(
            div.____(
                column_count("3"),
                column_gap(px(30)),
                column_width(px(200)),
                column_rule("1px solid #ccc"),
                column_span(ALL)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Multi-column Layout ===");
        System.out.println(result);

        assert result.contains("column-count: 3");
        assert result.contains("column-gap: 30px");
        assert result.contains("column-rule: 1px solid #ccc");
        assert result.contains("column-span: all");
    }

    @Test
    void testScrollbarProperties() {
        var css = rules(
            div.____(
                scrollbar_width(THIN),
                scrollbar_color("blue lightgray")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Scrollbar Properties (Firefox) ===");
        System.out.println(result);

        assert result.contains("scrollbar-width: thin");
        assert result.contains("scrollbar-color: blue lightgray");
    }

    @Test
    void testBackgroundProperties() {
        var css = rules(
            div.____(
                background_position("center"),
                background_size("cover"),
                background_repeat("no-repeat"),
                background_attachment("fixed"),
                background_origin("border-box"),
                background_clip("padding-box")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Background Properties ===");
        System.out.println(result);

        assert result.contains("background-position: center");
        assert result.contains("background-size: cover");
        assert result.contains("background-repeat: no-repeat");
    }

    @Test
    void testTransformProperties() {
        var css = rules(
            div.____(
                transform("rotate(45deg)"),
                transform_origin("center top"),
                transform_style(PRESERVE_3D),
                perspective(px(1000)),
                perspective_origin("50% 50%"),
                backface_visibility("hidden")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Transform Properties ===");
        System.out.println(result);

        assert result.contains("transform-origin: center top");
        assert result.contains("transform-style: preserve-3d");
        assert result.contains("perspective: 1000px");
    }

    @Test
    void testGridPropertiesExtended() {
        var css = rules(
            div.____(
                grid_column_start("1"),
                grid_column_end("3"),
                grid_row_start("2"),
                grid_row_end("span 2"),
                grid_auto_rows("minmax(100px, auto)"),
                grid_auto_columns("1fr"),
                grid_auto_flow("dense"),
                row_gap(px(20)),
                column_gap(px(30))
            )
        );

        String result = css.toString();
        System.out.println("\n=== Extended Grid Properties ===");
        System.out.println(result);

        assert result.contains("grid-column-start: 1");
        assert result.contains("grid-auto-flow: dense");
        assert result.contains("row-gap: 20px");
        assert result.contains("column-gap: 30px");
    }

    @Test
    void testMiscellaneousProperties() {
        var css = rules(
            div.____(
                direction(LTR),
                writing_mode("vertical-rl"),
                tab_size("4"),
                quotes("\"« \" \" »\""),
                orphans("3"),
                widows("2"),
                mix_blend_mode(MULTIPLY),
                isolation(ISOLATE),
                image_rendering(CRISP_EDGES)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Miscellaneous Properties ===");
        System.out.println(result);

        assert result.contains("direction: ltr");
        assert result.contains("tab-size: 4");
        assert result.contains("mix-blend-mode: multiply");
        assert result.contains("image-rendering: crisp-edges");
    }

    @Test
    void testTableProperties() {
        var css = rules(
            table.____(
                border_spacing(px(10)),
                caption_side(TOP),
                empty_cells("show"),
                table_layout(AUTO)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Table Properties ===");
        System.out.println(result);

        assert result.contains("border-spacing: 10px");
        assert result.contains("caption-side: top");
        assert result.contains("table-layout: auto");
    }

    @Test
    void testRealWorldMultiLineEllipsis() {
        // This is the VERY COMMON pattern that was missing!

        // Create a test enum for the class name
        enum TestClass implements CssClass {
            card_description
        }

        var css = rules(
            TestClass.card_description.____(
                display("-webkit-box"),
                webkit_line_clamp("2"),
                webkit_box_orient(VERTICAL),
                overflow(HIDDEN),
                text_overflow(ELLIPSIS)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Real-World: Multi-line Ellipsis ===");
        System.out.println(result);
        System.out.println("\nThis pattern is used on 90% of card-based UIs!");

        assert result.contains("-webkit-line-clamp: 2");
        assert result.contains("-webkit-box-orient: vertical");
        assert result.contains("text-overflow: ellipsis");

        System.out.println("\n✅ ALL MISSING PROPERTIES TESTS PASSED!");
    }

    @Test
    void testPhase3AlignmentAndShorthandProperties() {
        // Test CSS classes
        enum TestClasses implements CssClass {
            grid_container
        }

        // Test alignment properties (Grid/Flexbox)
        var css = rules(
            div.____(
                // Flexbox alignment
                display("flex"),
                align_self("center"),
                align_content("space-between"),
                justify_items("start"),
                justify_self("end"),

                // Alignment shorthands
                place_items("center"),
                place_content("center start"),
                place_self("auto center")
            ),

            TestClasses.grid_container.____(
                display("grid"),
                // Grid template shorthand
                grid_template("'header header' 100px 'sidebar main' 1fr / 200px 1fr")
            ),

            p.____(
                // Font shorthand
                font("italic small-caps bold 16px/1.5 Georgia, serif"),

                // Text decoration line
                text_decoration_line("underline"),

                // Overscroll behavior logical variants
                overscroll_behavior_block("contain"),
                overscroll_behavior_inline("auto")
            )
        );

        String result = css.toString();
        System.out.println("\n=== Phase 3: Alignment & Shorthand Properties ===");
        System.out.println(result);

        // Verify alignment properties
        assert result.contains("align-self: center");
        assert result.contains("align-content: space-between");
        assert result.contains("justify-items: start");
        assert result.contains("justify-self: end");
        assert result.contains("place-items: center");
        assert result.contains("place-content: center start");
        assert result.contains("place-self: auto center");

        // Verify grid template
        assert result.contains("grid-template:");

        // Verify font shorthand
        assert result.contains("font: italic small-caps bold 16px/1.5 Georgia, serif");

        // Verify text-decoration-line
        assert result.contains("text-decoration-line: underline");

        // Verify overscroll logical variants
        assert result.contains("overscroll-behavior-block: contain");
        assert result.contains("overscroll-behavior-inline: auto");

        System.out.println("\n✅ PHASE 3 PROPERTIES TEST PASSED!");
    }

    @Test
    void testAdditionalCommonProperties() {
        // Test very common properties that were missing
        var css = rules(
            div.____(
                // Visibility
                visibility(VISIBLE),

                // Float & clear
                float_(LEFT),
                clear(BOTH),

                // Flexbox order
                order(2),

                // Border components
                border_width(px(2)),
                border_style("solid"),

                // Animation control
                animation_play_state(PAUSED),

                // List shorthand
                list_style("disc inside"),

                // All property (reset)
                all(UNSET)
            )
        );

        String result = css.toString();
        System.out.println("\n=== Additional Common Properties ===");
        System.out.println(result);

        assert result.contains("visibility: visible");
        assert result.contains("float: left");
        assert result.contains("clear: both");
        assert result.contains("order: 2");
        assert result.contains("border-width: 2px");
        assert result.contains("border-style: solid");
        assert result.contains("animation-play-state: paused");
        assert result.contains("list-style: disc inside");
        assert result.contains("all: unset");

        System.out.println("\n✅ ADDITIONAL COMMON PROPERTIES TEST PASSED!");
    }
}
