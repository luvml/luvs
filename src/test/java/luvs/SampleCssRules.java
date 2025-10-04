package luvs;

import static luvml.E.*;
import static luvs.C.*;
import static luvml.T.text;
import static luvs.Selector.selector;
import static luvs.HtmlTag.*;
import static luvs.P.*;
import static luvs.V.*;
import static luvs.Css.style;
import luvml.o.HtmlRenderer;

/**
 * Example: CSS class names (just names, no rules in enum).
 */
public enum SampleCssRules implements CssClass {
    // Just names - reusable CSS class constants
    center,
    bold_justified,
    container,
    highlight;

    // ========== All CSS rules defined together ==========
    // Clean separation: names above, rules here

    public static Style appStyle() {
        return style(
            // Basic class rules
            center.____(
                text_align("center")
            ),

            bold_justified.____(
                font_weight("bold"),
                text_align("justify")
            ),

            container.____(
                width(percent(80)),
                margin(px(20), AUTO),
                padding(px(20)),
                background_color("white")
            ),

            highlight.____(
                background_color("yellow"),
                padding(em(0.5)),
                border_radius(px(4))
            ),

            // Complex selectors - different approaches
            selector(center, ">", div).____(
                color("blue"),
                margin(px(10))
            ),

            selector(container, ">", p).____(
                font_size(px(14)),
                line_height("1.6")
            ),

            center.child(div).hover().____(
                background_color("#efefef")
            ),

            input.focus().____(
                border_color("blue"),
                outline("none")
            ),

            selector(container).child(p).firstChild().____(
                font_weight("bold"),
                margin_top(ZERO)
            )
        );
    }

    // ========== Demo usage ==========
    public static void main(String[] a) {

        // All CSS rules in one place
        var appStyle = appStyle();

        // Build HTML using type-safe enum class names
        var html = html(
            head(
                luvml.E.style(appStyle.toString())
            ),
            body(
                div(
                    class_(center, bold_justified, "custom_string"),
                    p("This text is centered, bold, and justified"),
                    div("This child div gets blue text from .center > div rule. Hover to see effect!")
                ),
                div(
                    class_(container),
                    p(class_(highlight), text("First paragraph - bold with no top margin")),
                    p("Second paragraph gets rules from .container > p")
                )
            )
        );

        // Render
        System.out.println(HtmlRenderer.asFormattedString(html));
    }
}
