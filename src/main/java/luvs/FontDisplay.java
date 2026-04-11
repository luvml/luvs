package luvs;

/**
 * Values for the {@code font-display} descriptor in {@code @font-face}.
 *
 * <p>Controls how a font face is displayed based on whether and when it is downloaded and ready to use.
 */
public enum FontDisplay {
    /**
     * Default behavior - block for a short period, then swap.
     */
    AUTO("auto"),

    /**
     * Block rendering until font loads (up to 3s), then swap.
     * Use when font is critical to design.
     */
    BLOCK("block"),

    /**
     * Swap immediately with fallback, replace when font loads.
     * Best for performance and avoiding layout shift.
     */
    SWAP("swap"),

    /**
     * Very short block period (~100ms), then swap.
     * Compromise between BLOCK and SWAP.
     */
    FALLBACK("fallback"),

    /**
     * Render with fallback immediately, only use custom font if cached.
     * Use for optional decorative fonts.
     */
    OPTIONAL("optional");

    private final String value;

    FontDisplay(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
