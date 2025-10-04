package luvs;

import luvx.NamedEnumCharSeq;

/**
 * Marker interface for CSS variable name enums.
 * Enums implementing this become reusable, type-safe CSS variable names.
 *
 * Usage:
 * <pre>
 * public enum AppVars implements CssVariable {
 *     primary_color,
 *     spacing_unit;
 * }
 *
 * // Define in :root
 * root.____( primary_color.def("#007bff") )
 *
 * // Use in properties
 * color(primary_color.ref())
 * </pre>
 */
public interface CssVariable extends NamedEnumCharSeq {

    /**
     * Returns CSS variable name (--enum-name).
     */
    default String getVarName() {
        return "--" + ((Enum<?>) this).name().replace('_', '-');
    }

    /**
     * For use in class_() attribute (returns plain name).
     */
    @Override
    default String delegatedCharSeqVal() {
        return ((Enum<?>) this).name();
    }

    /**
     * Defines this CSS variable with a value.
     * Usage: primary_color.def("#007bff")
     * Produces: --primary-color: #007bff;
     */
    default CssProperty def(CharSequence value) {
        return new CssProperty(getVarName(), value);
    }

    /**
     * References this CSS variable.
     * Usage: color(primary_color.ref())
     * Produces: color: var(--primary-color);
     */
    default String ref() {
        return "var(" + getVarName() + ")";
    }

    /**
     * References this CSS variable with a fallback.
     * Usage: color(primary_color.ref("#000"))
     * Produces: color: var(--primary-color, #000);
     */
    default String ref(CharSequence fallback) {
        return "var(" + getVarName() + ", " + fallback + ")";
    }
}
