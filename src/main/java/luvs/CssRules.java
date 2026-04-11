package luvs;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Style is a collection of CSS rules, keyframes, comments, and empty lines.
 * Create with: style(rule1, rule2, keyframes1, comment(...), emptyLine(), ...)
 * Use with: E.style(myStyle)
 */
public final class CssRules implements CssRuleFrag {

    private final CssRule[] rules;
    private final Keyframes[] keyframes;
    private final CssRuleFrag[] orderedFragments;

    public CssRules(CssRule... rules) {
        this.rules = rules;
        this.keyframes = new Keyframes[0];
        this.orderedFragments = rules;
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

        // Store all CssRuleFrag items in order (CssRule, CssComment, CssEmptyLine)
        this.orderedFragments = Arrays.stream(items)
            .filter(item -> item instanceof CssRuleFrag)
            .map(item -> (CssRuleFrag) item)
            .toArray(CssRuleFrag[]::new);
    }

    @Override
    public String delegatedCharSeqVal() {
        // If there are keyframes, output them first, then ordered fragments
        if (keyframes.length > 0) {
            String keyframesStr = Arrays.stream(keyframes)
                .map(Keyframes::toString)
                .collect(Collectors.joining("\n\n"));

            String fragmentsStr = renderOrderedFragments();

            return fragmentsStr.isEmpty()
                ? keyframesStr
                : keyframesStr + "\n\n" + fragmentsStr;
        }

        // No keyframes, just render ordered fragments
        return renderOrderedFragments();
    }
    
    @Override
    public String toString(){
        return delegatedCharSeqVal();
    }
    

    private String renderOrderedFragments() {
        StringBuilder sb = new StringBuilder();
        boolean firstItem = true;

        for (CssRuleFrag frag : orderedFragments) {
            switch (frag) {
                case CssEmptyLine ignored -> {
                    // Empty lines just add a newline
                    sb.append("\n");
                }
                case CssComment cmnt -> {
                    // Comments on their own line
                    if (!firstItem) {
                        // the comments are either block or line
                        sb.append("\n");
                        if (cmnt.isBlock()) sb.append("\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case CssRule ignored -> {
                    // Rules with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case MediaQuery ignored -> {
                    // Media queries with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case FontFace ignored -> {
                    // @font-face rules with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case Supports ignored -> {
                    // @supports rules with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case ContainerQuery ignored -> {
                    // @container queries with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case Layer ignored -> {
                    // @layer rules with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case Layer.LayerOrder ignored -> {
                    // @layer order declarations with double newline separation
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
                case CssRules ignored -> {
                    // Nested CssRules (from rulesFrom)
                    if (!firstItem) {
                        sb.append("\n\n");
                    }
                    sb.append(frag.delegatedCharSeqVal());
                    firstItem = false;
                }
            }
        }

        return sb.toString();
    }

    CssRule[] getRules() {
        return rules;
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
     * Accepts CssRule, CssRules, CssComment, and CssEmptyLine objects, preserving order.
     *
     * Usage:
     * <pre>
     * return rulesFrom(
     *     comment("Section: Header"),
     *     frag(header.____(...), nav.____(...)),
     *     emptyLine(),
     *     forEachRule(categories, cat -> ...),
     *     frag(footer.____(...))
     * );
     * </pre>
     */
    public static CssRules rulesFrom(CssRuleFrag ... fragments) {
        Object[] expandedItems = Arrays.stream(fragments)
            .flatMap(frag -> switch (frag) {
                    case CssRule rule -> Stream.of(rule);
                    case CssRules rules -> Arrays.stream(rules.orderedFragments);
                    case MediaQuery mq -> Stream.of(mq);
                    case FontFace ff -> Stream.of(ff);
                    case Supports sup -> Stream.of(sup);
                    case ContainerQuery cq -> Stream.of(cq);
                    case Layer layer -> Stream.of(layer);
                    case Layer.LayerOrder lo -> Stream.of(lo);
                    case CssComment comment -> Stream.of(comment);
                    case CssEmptyLine emptyLine -> Stream.of(emptyLine);
                }
            )
            .toArray();
        return new CssRules(expandedItems);
    }
}