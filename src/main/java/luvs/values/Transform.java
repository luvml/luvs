package luvs.values;

import luvx.DelegatedCharSeq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Chainable CSS transform functions.
 *
 * Usage:
 * <pre>
 * transform(rotate(deg(45)))
 * transform(rotate(deg(45)).scale(1.5).translateX(px(10)))
 * </pre>
 */
public final class Transform implements DelegatedCharSeq {
    private final List<String> functions;

    private Transform(List<String> functions) {
        this.functions = functions;
    }

    // Public constructor for varargs (allows mixing enums, functions, strings)
    public Transform(CharSequence... functions) {
        this.functions = new ArrayList<>();
        for (CharSequence func : functions) {
            this.functions.add(func.toString());
        }
    }

    // ========== Chainable Methods ==========

    public Transform rotate(CharSequence angle) {
        return chain("rotate(" + angle + ")");
    }

    public Transform rotateX(CharSequence angle) {
        return chain("rotateX(" + angle + ")");
    }

    public Transform rotateY(CharSequence angle) {
        return chain("rotateY(" + angle + ")");
    }

    public Transform rotateZ(CharSequence angle) {
        return chain("rotateZ(" + angle + ")");
    }

    public Transform scale(Number value) {
        return chain("scale(" + value + ")");
    }

    public Transform scale(Number x, Number y) {
        return chain("scale(" + x + ", " + y + ")");
    }

    public Transform scaleX(Number value) {
        return chain("scaleX(" + value + ")");
    }

    public Transform scaleY(Number value) {
        return chain("scaleY(" + value + ")");
    }

    public Transform translate(CharSequence x, CharSequence y) {
        return chain("translate(" + x + ", " + y + ")");
    }

    public Transform translateX(CharSequence value) {
        return chain("translateX(" + value + ")");
    }

    public Transform translateY(CharSequence value) {
        return chain("translateY(" + value + ")");
    }

    public Transform translateZ(CharSequence value) {
        return chain("translateZ(" + value + ")");
    }

    public Transform skew(CharSequence x, CharSequence y) {
        return chain("skew(" + x + ", " + y + ")");
    }

    public Transform skewX(CharSequence angle) {
        return chain("skewX(" + angle + ")");
    }

    public Transform skewY(CharSequence angle) {
        return chain("skewY(" + angle + ")");
    }

    private Transform chain(String function) {
        List<String> newFunctions = new ArrayList<>(this.functions);
        newFunctions.add(function);
        return new Transform(newFunctions);
    }

    @Override
    public String delegatedCharSeqVal() {
        return String.join(" ", functions);
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }
}
