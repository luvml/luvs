package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import luvx.DelegatedCharSeq;

/**
 * A Style is a collection of CSS rules and keyframes.
 * Create with: style(rule1, rule2, keyframes1, ...)
 * Use with: E.style(myStyle)
 */
public final class CssRules implements DelegatedCharSeq {

    private final CssRule[] rules;
    private final Keyframes[] keyframes;

    public CssRules(CssRule... rules) {
        this.rules = rules;
        this.keyframes = new Keyframes[0];
    }

    public CssRules(Object... items) {
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
    public static CssRules rules(CssRule... rules) {
        return new CssRules(rules);
    }

    /**
     * Factory method for creating styles with keyframes.
     * @deprecated not a nice design to be honest, temporarily in place
     */
    @Deprecated
    public static CssRules rules(Object... items) {
        return new CssRules(items);
    }
}