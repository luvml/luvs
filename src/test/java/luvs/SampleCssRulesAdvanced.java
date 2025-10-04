package luvs;

import static luvml.E.*;
import static luvs.C.*;
import static luvml.T.text;
import static luvs.Selector.selector;
import static luvs.HtmlTag.*;
import static luvs.P.*;
import static luvs.V.*;  // Single import for ALL CSS functions and type-safe values!
import static luvs.Css.style;
import static luvs.SampleCssRulesAdvanced.Styles.*;

import luvml.o.HtmlRenderer;

/**
 * Advanced CSS DSL examples showcasing all new features:
 * - CSS variables as enums
 * - Chainable calc() arithmetic
 * - Advanced selector chaining with pseudo-elements
 * - Type-safe colors and property values
 * - Math functions (min, max, clamp)
 * - Color functions (rgb, hsl, etc.)
 * - Gradient functions
 * - Chainable transforms and filters
 */
public class SampleCssRulesAdvanced {

    // ========== CSS Variables as Enums ==========
    public enum AppVars implements CssVariable {
        primary_color,
        spacing_unit,
        header_height;
    }

    // ========== CSS Class Names (just names, no rules in enum) ==========
    public enum Styles implements CssClass {
        containerx,
        card,
        btn,
        gradient_bg,
        animated_box;
    }

    public static Style advancedAppStyle() {
        return style(
            // === CSS Variables ===
            selector(":root").____(
                AppVars.primary_color.def("#007bff"),
                AppVars.spacing_unit.def(px(20)),
                AppVars.header_height.def(px(60))
            ),

            // === Chainable Calc Arithmetic ===
            containerx.____(
                width(percent(100).minus(px(40))),  // calc(100% - 40px)
                max_width(px(1200)),
                margin(ZERO, AUTO),
                padding(AppVars.spacing_unit.ref())
            ),

            // === Type-Safe Property Values ===
            card.____(
                display(FLEX),
                flex_direction(COLUMN),
                background_color(WHITE),
                border_radius(px(8)),
                padding(em(2)),
                // Math functions
                width(clamp(px(300), percent(50), px(600)))
            ),

            // === Advanced Selector Chaining with Pseudo-Elements ===
            card.child(div, Styles.gradient_bg).hover().before().____(
                content("''"),
                position(ABSOLUTE),
                width(percent(100)),
                height(percent(100)),
                // Gradient with type-safe angle
                background(linearGradientWithAngle(deg(45), RED, GREEN, BLUE)),
                P.opacity(0.3)
            ),

            // === Chainable Transforms ===
            btn.hover().____(
                transform(
                    scale(1.05)
                        .rotate(deg(2))
                        .translateY(px(-2))
                ),
                // Type-safe colors
                background_color(BLUE),
                color(WHITE)
            ),

            // === Chainable Filters ===
            Styles.animated_box.____(
                filter(
                    blur(px(5))
                        .brightness(1.2)
                        .contrast(0.9)
                ),
                // Color functions
                background_color(rgba(255, 100, 50, 0.8))
            ),

            // === Complex Pseudo-Selectors ===
            selector(containerx).child(p).nthChild("2n+1").not(".excluded").____(
                color(rgb(100, 100, 100)),
                font_weight(BOLD)
            ),

            // === Pseudo-Elements ===
            btn.after().____(
                content("' →'"),
                margin_left(em(0.5))
            ),

            // === HSL Colors ===
            selector(".highlight").____(
                background_color(hsl(120, percent(100), percent(50))),
                color(BLACK)
            ),

            // === Using CSS Variables ===
            selector("header").____(
                height(AppVars.header_height.ref()),
                background_color(AppVars.primary_color.ref()),
                color(WHITE),
                // Min/Max functions
                padding(ZERO, max(px(20), vw(5)))
            ),

            // === Radial Gradient ===
            gradient_bg.____(
                background(radialGradient(WHITE, LIGHT_BLUE, BLUE)),
                min_height(vh(100))
            )
        );
    }

    // ========== Demo usage ==========
    public static void main(String[] a) {
        var appStyle = advancedAppStyle();

        var html = html(
            head(
                luvml.E.style(appStyle.toString())
            ),
            body(
                div(
                    class_(containerx),

                    luvml.E.header(
                        p("Advanced CSS DSL Demo")
                    ),

                    div(
                        class_(card),
                        p(class_("highlight"), text("Type-safe CSS with enums!")),
                        div(
                            class_(gradient_bg),
                            text("Gradient background")
                        )
                    ),

                    luvml.E.button(
                        class_(btn),
                        text("Hover me!")
                    ),

                    div(
                        class_(Styles.animated_box),
                        text("Filtered content with blur and brightness")
                    )
                )
            )
        );

        System.out.println(HtmlRenderer.asFormattedString(html));
    }
}
