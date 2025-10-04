package luvs.values;

import luvx.DelegatedCharSeq;

/**
 * Type-safe CSS color constants.
 * Provides named colors, transparent, and currentColor.
 *
 * Usage:
 * <pre>
 * color(Color.RED)
 * background_color(Color.TRANSPARENT)
 * border_color(Color.CURRENT_COLOR)
 * </pre>
 */
public enum Color implements DelegatedCharSeq {
    // CSS Named Colors (most common)
    BLACK("black"),
    WHITE("white"),
    RED("red"),
    GREEN("green"),
    BLUE("blue"),
    YELLOW("yellow"),
    ORANGE("orange"),
    PURPLE("purple"),
    PINK("pink"),
    BROWN("brown"),
    GRAY("gray"),
    GREY("grey"),

    // Extended named colors (exhaustive list in dedicated enum)
    DARK_RED("darkred"),
    DARK_GREEN("darkgreen"),
    DARK_BLUE("darkblue"),
    DARK_ORANGE("darkorange"),
    DARK_GRAY("darkgray"),
    DARK_GREY("darkgrey"),
    LIGHT_GRAY("lightgray"),
    LIGHT_GREY("lightgrey"),
    LIGHT_BLUE("lightblue"),
    LIGHT_GREEN("lightgreen"),
    LIGHT_PINK("lightpink"),
    LIGHT_CORAL("lightcoral"),
    LIGHT_YELLOW("lightyellow"),

    NAVY("navy"),
    TEAL("teal"),
    CYAN("cyan"),
    MAGENTA("magenta"),
    MAROON("maroon"),
    OLIVE("olive"),
    LIME("lime"),
    AQUA("aqua"),
    SILVER("silver"),
    GOLD("gold"),
    INDIGO("indigo"),
    VIOLET("violet"),
    CRIMSON("crimson"),
    CORAL("coral"),
    SALMON("salmon"),
    KHAKI("khaki"),
    LAVENDER("lavender"),
    PLUM("plum"),
    BEIGE("beige"),
    IVORY("ivory"),
    SNOW("snow"),
    AZURE("azure"),
    TOMATO("tomato"),
    ORCHID("orchid"),
    TAN("tan"),
    WHEAT("wheat"),
    PERU("peru"),
    CHOCOLATE("chocolate"),
    SIENNA("sienna"),

    // Special values
    TRANSPARENT("transparent"),
    CURRENT_COLOR("currentColor"),
    INHERIT("inherit"),
    INITIAL("initial");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String delegatedCharSeqVal() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
