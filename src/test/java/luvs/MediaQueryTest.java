package luvs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static luvs.MQ.*;
import static luvs.P.*;
import static luvs.V.*;
import static luvs.CssRule.rule;
import static luvs.CssRules.rulesFrom;
import static luvs.CssComment.comment;
import static org.junit.jupiter.api.Assertions.*;

class MediaQueryTest {

    // ========== MediaCondition Rendering ==========

    @Nested
    class ConditionRendering {

        @Test
        void minWidth() {
            assertEquals("(min-width: 768px)", MQ.minWidth(px(768)).toString());
        }

        @Test
        void maxWidth() {
            assertEquals("(max-width: 1200px)", MQ.maxWidth(px(1200)).toString());
        }

        @Test
        void minHeight() {
            assertEquals("(min-height: 100vh)", MQ.minHeight(vh(100)).toString());
        }

        @Test
        void maxHeight() {
            assertEquals("(max-height: 600px)", MQ.maxHeight(px(600)).toString());
        }

        @Test
        void prefersColorScheme() {
            assertEquals("(prefers-color-scheme: dark)", MQ.prefersColorScheme(DARK).toString());
            assertEquals("(prefers-color-scheme: light)", MQ.prefersColorScheme(LIGHT).toString());
        }

        @Test
        void prefersReducedMotion() {
            assertEquals("(prefers-reduced-motion: reduce)", MQ.prefersReducedMotion().toString());
        }

        @Test
        void prefersContrast() {
            assertEquals("(prefers-contrast: more)", MQ.prefersContrast("more").toString());
        }

        @Test
        void orientation() {
            assertEquals("(orientation: portrait)", MQ.orientation(PORTRAIT).toString());
            assertEquals("(orientation: landscape)", MQ.orientation(LANDSCAPE).toString());
        }

        @Test
        void mediaTypes() {
            assertEquals("screen", MQ.screen().toString());
            assertEquals("print", MQ.print().toString());
            assertEquals("all", MQ.all().toString());
        }

        @Test
        void genericFeature() {
            assertEquals("(resolution: 2dppx)", feature("resolution", "2dppx").toString());
        }

        @Test
        void rawCondition() {
            assertEquals("(color)", condition("(color)").toString());
        }
    }

    // ========== Condition Combinators ==========

    @Nested
    class ConditionCombinators {

        @Test
        void and() {
            var cond = MQ.minWidth(px(768)).and(MQ.maxWidth(px(1200)));
            assertEquals("(min-width: 768px) and (max-width: 1200px)", cond.toString());
        }

        @Test
        void or() {
            var cond = MQ.screen().or(MQ.print());
            assertEquals("screen, print", cond.toString());
        }

        @Test
        void not() {
            var cond = MQ.not(MQ.print());
            assertEquals("not print", cond.toString());
        }

        @Test
        void mediaTypeAndFeature() {
            var cond = MQ.screen().and(MQ.minWidth(px(768)));
            assertEquals("screen and (min-width: 768px)", cond.toString());
        }

        @Test
        void chainedAnd() {
            var cond = MQ.screen()
                .and(MQ.minWidth(px(768)))
                .and(MQ.maxWidth(px(1200)));
            assertEquals("screen and (min-width: 768px) and (max-width: 1200px)", cond.toString());
        }
    }

    // ========== MediaQuery Rendering ==========

    @Nested
    class MediaQueryRendering {

        @Test
        void singleRule() {
            var mq = media(MQ.minWidth(px(768)),
                rule(".container", max_width(px(1200)))
            );

            String expected = """
                @media (min-width: 768px) {
                    .container {
                        max-width: 1200px;
                    }
                }""";
            assertEquals(expected, mq.toString());
        }

        @Test
        void multipleRules() {
            var mq = media(MQ.minWidth(px(768)),
                rule(".container", max_width(px(1200))),
                rule(".sidebar", display(V.BLOCK))
            );

            String expected = """
                @media (min-width: 768px) {
                    .container {
                        max-width: 1200px;
                    }

                    .sidebar {
                        display: block;
                    }
                }""";
            assertEquals(expected, mq.toString());
        }

        @Test
        void emptyMediaQuery() {
            var mq = media(MQ.minWidth(px(768)));
            assertEquals("@media (min-width: 768px) {\n}", mq.toString());
        }

        @Test
        void darkModeExample() {
            var mq = media(MQ.prefersColorScheme(DARK),
                rule("body", background_color("#1a1a1a"), color("#eee"))
            );

            assertTrue(mq.toString().contains("@media (prefers-color-scheme: dark)"));
            assertTrue(mq.toString().contains("background-color: #1a1a1a;"));
            assertTrue(mq.toString().contains("color: #eee;"));
        }
    }

    // ========== Nesting Prevention ==========

    @Nested
    class NestingPrevention {

        @Test
        void throwsOnNestedMediaQuery() {
            var inner = media(MQ.maxWidth(px(600)),
                rule(".small", font_size(px(12)))
            );

            assertThrows(IllegalArgumentException.class, () ->
                media(MQ.minWidth(px(768)), inner)
            );
        }

        @Test
        void throwsWithDescriptiveMessage() {
            var inner = media(MQ.maxWidth(px(600)),
                rule(".small", font_size(px(12)))
            );

            var ex = assertThrows(IllegalArgumentException.class, () ->
                media(MQ.minWidth(px(768)), inner)
            );
            assertTrue(ex.getMessage().contains("nested"));
        }
    }

    // ========== CssRules Integration ==========

    @Nested
    class CssRulesIntegration {

        @Test
        void mediaQueryInRulesFrom() {
            var mq = media(MQ.minWidth(px(768)),
                rule(".container", padding(rem(2)))
            );

            var styles = rulesFrom(
                rule("body", margin(V.ZERO)),
                mq
            );

            String result = styles.toString();
            assertTrue(result.contains("body {"));
            assertTrue(result.contains("@media (min-width: 768px)"));
            assertTrue(result.contains(".container {"));
        }

        @Test
        void acceptsCssRulesContent() {
            // Media query should accept CssRules (flattens them)
            var baseRules = CssRules.rules(
                rule(".a", color("red")),
                rule(".b", color("blue"))
            );

            var mq = media(MQ.minWidth(px(768)), baseRules);
            String result = mq.toString();
            assertTrue(result.contains(".a {"));
            assertTrue(result.contains(".b {"));
        }

        @Test
        void multipleMediaQueriesInRulesFrom() {
            var tablet = media(MQ.minWidth(px(768)),
                rule(".nav", display(V.FLEX))
            );
            var dark = media(MQ.prefersColorScheme(DARK),
                rule("body", background_color("#111"))
            );

            var styles = rulesFrom(
                rule("body", margin(V.ZERO)),
                tablet,
                dark
            );

            String result = styles.toString();
            assertTrue(result.contains("@media (min-width: 768px)"));
            assertTrue(result.contains("@media (prefers-color-scheme: dark)"));
        }
    }
}
