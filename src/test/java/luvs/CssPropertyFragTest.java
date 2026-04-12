package luvs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static luvs.P.*;
import static luvs.V.*;

class CssPropertyFragTest {

    @Test
    void testSingleCssProperty() {
        CssPropertyFrag prop = color(RED);
        assertEquals("color: red;", prop.toString());
    }

    @Test
    void testCssPropertyFragsComposition() {
        CssPropertyFrags frags = new CssPropertyFrags()
            .____(color(RED))
            .____(font_size(px(16)))
            .____(margin(ZERO));

        String result = frags.delegatedCharSeqVal();
        assertTrue(result.contains("color: red;"));
        assertTrue(result.contains("font-size: 16px;"));
        assertTrue(result.contains("margin: 0;"));
    }

    @Test
    void testCssPropertyFragsFlattening() {
        CssPropertyFrags frags = new CssPropertyFrags()
            .____(color(RED))
            .____(font_size(px(16)));

        var flattened = frags.flattened();
        assertEquals(2, flattened.size());
        assertEquals("color: red;", flattened.get(0).delegatedCharSeqVal());
        assertEquals("font-size: 16px;", flattened.get(1).delegatedCharSeqVal());
    }

    @Test
    void testNestedCssPropertyFrags() {
        CssPropertyFrags inner = new CssPropertyFrags()
            .____(color(RED))
            .____(font_size(px(16)));

        CssPropertyFrags outer = new CssPropertyFrags()
            .____(margin(ZERO))
            .____(inner)
            .____(padding(px(10)));

        var flattened = outer.flattened();
        assertEquals(4, flattened.size());
        assertEquals("margin: 0;", flattened.get(0).delegatedCharSeqVal());
        assertEquals("color: red;", flattened.get(1).delegatedCharSeqVal());
        assertEquals("font-size: 16px;", flattened.get(2).delegatedCharSeqVal());
        assertEquals("padding: 10px;", flattened.get(3).delegatedCharSeqVal());
    }

    @Test
    void testCssRuleWithPropertyFrags() {
        // Helper function that returns multiple properties
        CssPropertyFrags webkitLineClamp = webkitLineClamp(2);

        CssRule rule = CssRule.rule(".text",
            color(BLUE),
            webkitLineClamp,
            margin(px(10))
        );

        String result = rule.delegatedCharSeqVal();
        System.out.println("Generated CSS:");
        System.out.println(result);
        System.out.println("---");

        assertTrue(result.contains(".text {"), "Should contain selector");
        assertTrue(result.contains("color: blue;"), "Should contain color");
        assertTrue(result.contains("display: -webkit-box;"), "Should contain display");
        assertTrue(result.contains("-webkit-line-clamp: 2;"), "Should contain line-clamp");
        assertTrue(result.contains("-webkit-box-orient: vertical;"), "Should contain box-orient");
        assertTrue(result.contains("margin: 10px;"), "Should contain margin");
        assertTrue(result.contains("}"), "Should contain closing brace");
    }

    @Test
    void testWebkitLineClampHelper() {
        CssPropertyFrags clamp = webkitLineClamp(3);
        var flattened = clamp.flattened();

        assertEquals(4, flattened.size());
        assertEquals("display: -webkit-box;", flattened.get(0).delegatedCharSeqVal());
        assertEquals("-webkit-line-clamp: 3;", flattened.get(1).delegatedCharSeqVal());
        assertEquals("-webkit-box-orient: vertical;", flattened.get(2).delegatedCharSeqVal());
        assertEquals("overflow: hidden;", flattened.get(3).delegatedCharSeqVal());
    }

    // Helper function demonstrating reusable property composition using props()
    private static CssPropertyFrags webkitLineClamp(int lines) {
        return props(
            display("-webkit-box"),
            prop("-webkit-line-clamp", String.valueOf(lines)),
            prop("-webkit-box-orient", "vertical"),
            overflow(HIDDEN)
        );
    }
}
