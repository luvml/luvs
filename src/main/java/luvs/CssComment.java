package luvs;

import java.util.Objects;

/**
 * Represents a CSS comment line.
 * Single-line CSS comments are rendered as slash-asterisk comment asterisk-slash format.
 *
 * Usage:
 * <pre>
 *   comment("This is a comment")
 *   comment("Another comment")
 * </pre>
 */
public final class CssComment implements CssRuleFrag {

    private final String text;
    private final boolean block;

    public CssComment(String text, boolean block) {
        this.block = block;
        this.text = Objects.requireNonNull(text, "Comment text cannot be null");
    }

    /**
     * Factory method for creating CSS comments.
     */
    public static CssComment comment(String text) {
        return new CssComment(text, false);
    }
    
    public static CssComment commentBlock(String text) {
        return new CssComment(text, true);
    }
    
    public static CssComment comment(String ... texts) {
        return comment(String.join("", texts));
    }
    
    public static CssComment commentBlock(String ... texts) {
        return commentBlock(String.join("\n", texts));
    }

    @Override
    public String delegatedCharSeqVal() {
        return "/* " + text + " */" /*+ (block?"\n":"")*/;
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
    
    public boolean isBlock(){
        return block;
    }
}
