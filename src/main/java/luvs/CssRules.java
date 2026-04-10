package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import luvx.DelegatedCharSeq;

/**
 * A Style is a collection of CSS rules and keyframes.
 * Create with: style(rule1, rule2, keyframes1, ...)
 * Use with: E.style(myStyle)
 */
public final class CssRules implements CssRuleFrag {

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

    /**
     * Concatenates multiple CssRule arrays into a single array.
     * Useful for combining static and dynamically-generated rules.
     *
     * Usage:
     * <pre>
     * return rules(concat(
     *     staticRules(),
     *     dynamicRules(),
     *     moreStaticRules()
     * ));
     * </pre>
     */
    public static CssRule[] concat(CssRule[]... arrays) {
        return Arrays.stream(arrays)
            .flatMap(Arrays::stream)
            .toArray(CssRule[]::new);
    }

    /**
     * Creates a rule fragment from varargs. Useful for grouping static rules.
     *
     * Usage:
     * <pre>
     * return rules(
     *     frag(header.____(...), nav.____(...)),
     *     forEachRule(categories, cat -> ...),
     *     frag(footer.____(...))
     * );
     * </pre>
     */
    public static CssRule[] rulesFrags(CssRule... rules) {
        return rules;
    }

    /**
     * Maps a collection to CSS rules using a lambda.
     * Useful for data-driven rule generation.
     *
     * Usage:
     * <pre>
     * forEachRule(CATEGORY_COLORS, cat ->
     *     selector(cat_btn + "[data-category-id=\"" + cat.id() + "\"]").____(
     *         background_color(cat.color())
     *     )
     * )
     * </pre>
     */
    public static <T> CssRules forEachRule(java.util.Collection<T> collection, java.util.function.Function<T, CssRule> mapper) {
        return new CssRules(collection.stream()
            .map(mapper)
            .toArray(CssRule[]::new));
    }

    /**
     * Maps an array to CSS rules using a lambda.
     */
    @SafeVarargs
    public static <T> CssRules forEachRule(java.util.function.Function<T, CssRule> mapper, T... items) {
        return new CssRules(Arrays.stream(items)
            .map(mapper)
            .toArray(CssRule[]::new));
    }

    /**
     * Creates rules from mixed fragments and arrays.
     * Accepts both CssRule and CssRule[] objects, flattens them into a single CssRules.
     *
     * Usage:
     * <pre>
     * return rulesFrom(
     *     frag(header.____(...), nav.____(...)),
     *     forEachRule(categories, cat -> ...),
     *     frag(footer.____(...))
     * );
     * </pre>
     */
    public static CssRules rulesFrom(CssRuleFrag ... fragments) {
        return new CssRules(Arrays.stream(fragments)
            .flatMap(frag -> switch (frag) {
                    case CssRule rule -> java.util.stream.Stream.of(rule);
                    case CssRules rules -> Arrays.stream(rules.rules);
                }
            )
            .toArray(CssRule[]::new)
        );
    }
}