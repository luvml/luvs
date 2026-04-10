package luvs;

/**
 * CSS property name constants for use in transitions, animations, etc.
 * Avoids string literals when referencing property names.
 */
public final class CssProp {
    private CssProp() {} // Utility class

    // Common animated/transitioned properties
    public static final String BACKGROUND = "background";
    public static final String BACKGROUND_COLOR = "background-color";
    public static final String COLOR = "color";
    public static final String OPACITY = "opacity";

    public static final String TRANSFORM = "transform";
    public static final String TRANSLATE = "translate";
    public static final String SCALE = "scale";
    public static final String ROTATE = "rotate";

    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String MAX_WIDTH = "max-width";
    public static final String MAX_HEIGHT = "max-height";
    public static final String MIN_WIDTH = "min-width";
    public static final String MIN_HEIGHT = "min-height";

    public static final String PADDING = "padding";
    public static final String MARGIN = "margin";
    public static final String BORDER = "border";
    public static final String BORDER_COLOR = "border-color";
    public static final String BORDER_WIDTH = "border-width";
    public static final String BORDER_RADIUS = "border-radius";

    public static final String BOX_SHADOW = "box-shadow";
    public static final String TEXT_SHADOW = "text-shadow";

    // Position properties (prefixed to avoid conflict with Position enum values)
    public static final String PROP_TOP = "top";
    public static final String PROP_RIGHT = "right";
    public static final String PROP_BOTTOM = "bottom";
    public static final String PROP_LEFT = "left";

    public static final String FONT_SIZE = "font-size";
    public static final String FONT_WEIGHT = "font-weight";
    public static final String LINE_HEIGHT = "line-height";
    public static final String LETTER_SPACING = "letter-spacing";

    public static final String ALL = "all";
}
