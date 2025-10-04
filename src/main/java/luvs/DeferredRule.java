package luvs;

import java.util.function.Supplier;

/**
 * Lazy CSS rule that resolves selector at render time.
 * Solves forward-reference problem when enum constants need to reference each other.
 */
public final class DeferredRule extends CssRule {

    private final Supplier<CharSequence> selectorSupplier;
    private final CssProperty[] properties;

    public DeferredRule(Supplier<CharSequence> selectorSupplier, CssProperty... properties) {
        super("", properties); // Placeholder
        this.selectorSupplier = selectorSupplier;
        this.properties = properties;
    }

    @Override
    public String delegatedCharSeqVal() {
        // Resolve selector at render time
        CharSequence resolvedSelector = selectorSupplier.get();
        return new CssRule(resolvedSelector, properties).delegatedCharSeqVal();
    }

    /**
     * Factory for deferred rules using enum constants.
     */
    public static DeferredRule deferred(Supplier<CharSequence> selectorSupplier, CssProperty... properties) {
        return new DeferredRule(selectorSupplier, properties);
    }
}
