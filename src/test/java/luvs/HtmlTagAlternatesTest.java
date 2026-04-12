package luvs;

import org.junit.jupiter.api.Test;

import static luvs.CssRules.*;
import static luvs.HtmlTag.*;
import static luvs.P.*;
import static luvs.V.*;

/**
 * Tests for underscore alternate tag names (e.g., div_ vs div)
 * These alternates prevent naming conflicts when using both luvml.E and luvs.HtmlTag static imports
 */
class HtmlTagAlternatesTest {

    @Test
    void testAlternatesProduceSameOutput() {
        // Both div and div_ should produce the same CSS selector
        var css1 = rules(div.____(color(RED)));
        var css2 = rules(div_.____(color(RED)));

        assert css1.toString().equals(css2.toString());
        assert css1.toString().contains("div {");

        System.out.println("=== Alternates Test ===");
        System.out.println("div output:  " + css1.toString().trim());
        System.out.println("div_ output: " + css2.toString().trim());
        System.out.println("✅ Both produce identical CSS!");
    }

    @Test
    void testCommonAlternates() {
        var css = rules(
            // Using underscore alternates
            div_.____(display(FLEX)),
            p_.____(margin(px(10))),
            h1_.____(font_size(rem(2))),
            span_.____(color("#333")),
            a_.____(text_decoration("none")),
            img_.____(width(percent(100))),
            input_.____(border(px(1), SOLID, "#ccc")),
            button_.____(padding(rem(0.5), rem(1))),
            table_.____(border_collapse(BC_COLLAPSE))
        );

        String result = css.toString();
        System.out.println("\n=== Common Alternates ===");
        System.out.println(result);

        // Verify all produce lowercase tag names in CSS
        assert result.contains("div {");
        assert result.contains("p {");
        assert result.contains("h1 {");
        assert result.contains("span {");
        assert result.contains("a {");
        assert result.contains("img {");
        assert result.contains("input {");
        assert result.contains("button {");
        assert result.contains("table {");

        System.out.println("✅ All alternates work correctly!");
    }

    @Test
    void testSelectorChaining() {
        // Alternates work with selector chaining too
        var css = rules(
            div_.child(p_).____(margin(ZERO)),
            h4_.hover().____(color(BLUE)),
            input_.typeCheckbox().____(width(px(20)))
        );

        String result = css.toString();
        System.out.println("\n=== Chaining with Alternates ===");
        System.out.println(result);

        assert result.contains("div > p {");
        assert result.contains("h4:hover {");
        assert result.contains("input[type=\"checkbox\"] {");

        System.out.println("✅ Chaining works with alternates!");
    }

    @Test
    void testNoConflictScenario() {
        // This demonstrates the scenario where you'd need alternates:
        // When both luvml.E.* and luvs.HtmlTag.* are imported
        // Use div_ to avoid confusion with div() method from luvml.E

        var css = rules(
            // If luvml.E was imported, div() would be the element factory method
            // So use div_ for the HtmlTag selector
            div_.____(
                display(FLEX),
                gap(rem(1))
            ),

            // Same for other common tags
            p_.____(line_height(1.6)),
            h4_.____(font_weight(BOLD))
        );

        String result = css.toString();
        System.out.println("\n=== No Conflict Scenario ===");
        System.out.println(result);
        System.out.println("✅ Alternates prevent naming conflicts!");
    }
}
