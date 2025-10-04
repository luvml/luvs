    package luvs;

// LuvML Element and Attribute factories
import static luvml.A.class_;
import static luvml.A.href;
import static luvml.E.*;
import static luvml.T.text;
//import static luvml.A.*;
import luvml.o.HtmlRenderer;

// LuvS CSS DSL factories
import static luvs.P.*; // Properties
import static luvs.S.*; // Selectors
import static luvs.V.*; // Values
import static luvs.Css.*; // Core factories

public class SampleCssUsage {

    public static void main(String[] args) {
        
        // Define CSS rules using the new, more expressive DSL
        var myStyles = style(
            rule(
                tag("body"),
                font_family("Arial", "sans-serif"),
                margin(ZERO),
                padding(ZERO),
                background_color("#f0f0f0")
            ),
            rule(
                cls("container"),
                width(percent(80)),
                margin(px(20), AUTO), // Mix units and keywords
                padding(px(20)),
                border(px(1), "solid", "#ccc"),
                background_color("white")
            ),
            // Use combinators for more complex selectors
            rule(
                descendant(cls("container"), tag("h1")),
                color("#333")
            ),
            // Group multiple selectors
            rule(
                grouping(hover(tag("a")), focus(tag("a"))),
                color("red"),
                text_decoration("underline")
            )
        );

        // Build the HTML document using luvml
        var document = html(
            head(
                title("LuvS CSS DSL Demo"),
                style(myStyles.toString())
            ),
            body(
                div(
                    class_("container"),
                    h1("Welcome to the Revised LuvS CSS DSL!"),
                    p(
                        // Inline styles are now much cleaner
                        styleAttr(
                           color("green"),
                           font_weight("bold"),
                           padding(em(0.5))
                        ),
                        text("This paragraph has type-safe inline styles.") // currently luvml doesn't allow luvs style mixing of string and xml/html fragments frag_i
                    ),
                    p(text("This is a link: "), a(href("#")), text("Hover or focus me!"))
                )
            )
        );

        // Render the final HTML
        String renderedHtml = HtmlRenderer.asFormattedString(document);
        System.out.println(renderedHtml);
    }
}