package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import luvx.DelegatedCharSeq;

/**
 * A Style is a collection of CSS rules and keyframes.
 * Create with: style(rule1, rule2, keyframes1, ...)
 * Use with: E.style(myStyle)
 */
public final class Style implements DelegatedCharSeq {

    private final CssRule[] rules;
    private final Keyframes[] keyframes;

    public Style(CssRule... rules) {
        this.rules = rules;
        this.keyframes = new Keyframes[0];
    }

    public Style(Object... items) {
        this.rules = Arrays.stream(items)
            .filter(item -> item instanceof CssRule)
            .map(item -> (CssRule) item)
            .toArray(CssRule[]::new);

        this.keyframes = Arrays.stream(items)
            .filter(item -> item instanceof Keyframes)
            .map(item -> (Keyframes) item)
            .toArray(Keyframes[]::new);
    }

    @Override
    public String delegatedCharSeqVal() {
        if (keyframes.length == 0) {
            return Arrays.stream(rules)
                .map(CssRule::toString)
                .collect(Collectors.joining("\n\n"));
        }

        String keyframesStr = Arrays.stream(keyframes)
            .map(Keyframes::toString)
            .collect(Collectors.joining("\n\n"));

        String rulesStr = Arrays.stream(rules)
            .map(CssRule::toString)
            .collect(Collectors.joining("\n\n"));

        return keyframesStr + "\n\n" + rulesStr;
    }

    /**
     * Factory method for creating styles.
     */
    public static Style style(CssRule... rules) {
        return new Style(rules);
    }

    /**
     * Factory method for creating styles with keyframes.
     */
    public static Style style(Object... items) {
        return new Style(items);
    }
}