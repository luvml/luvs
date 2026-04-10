package luvs;

/**
 * Represents an empty line in CSS output for better readability.
 *
 * Usage:
 *   emptyLine()
 */
public final class CssEmptyLine implements CssRuleFrag {

    private static final CssEmptyLine INSTANCE = new CssEmptyLine();

    private CssEmptyLine() {
        // Private constructor for singleton pattern
    }

    /**
     * Factory method for creating an empty line.
     * Returns a singleton instance.
     */
    public static CssEmptyLine emptyLine() {
        return INSTANCE;
    }

    @Override
    public String delegatedCharSeqVal() {
        return "";
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}
