package luvs.values;

import luvx.DelegatedCharSeq;
import java.util.ArrayList;
import java.util.List;

/**
 * Chainable CSS filter functions.
 *
 * Usage:
 * <pre>
 * filter(blur(px(5)))
 * filter(blur(px(5)).brightness(1.2).contrast(0.8))
 * </pre>
 */
public final class Filter implements DelegatedCharSeq {
    private final List<String> functions;

    private Filter(List<String> functions) {
        this.functions = functions;
    }

    // Public constructor for varargs (allows mixing enums, functions, strings)
    public Filter(CharSequence... functions) {
        this.functions = new ArrayList<>();
        for (CharSequence func : functions) {
            this.functions.add(func.toString());
        }
    }

    // ========== Chainable Methods ==========

    public Filter blur(CharSequence radius) {
        return chain("blur(" + radius + ")");
    }

    public Filter brightness(Number amount) {
        return chain("brightness(" + amount + ")");
    }

    public Filter contrast(Number amount) {
        return chain("contrast(" + amount + ")");
    }

    public Filter dropShadow(CharSequence offsetX, CharSequence offsetY, CharSequence blurRadius, CharSequence color) {
        return chain("drop-shadow(" + offsetX + " " + offsetY + " " + blurRadius + " " + color + ")");
    }

    public Filter grayscale(Number amount) {
        return chain("grayscale(" + amount + ")");
    }

    public Filter hueRotate(CharSequence angle) {
        return chain("hue-rotate(" + angle + ")");
    }

    public Filter invert(Number amount) {
        return chain("invert(" + amount + ")");
    }

    public Filter opacity(Number amount) {
        return chain("opacity(" + amount + ")");
    }

    public Filter saturate(Number amount) {
        return chain("saturate(" + amount + ")");
    }

    public Filter sepia(Number amount) {
        return chain("sepia(" + amount + ")");
    }

    private Filter chain(String function) {
        List<String> newFunctions = new ArrayList<>(this.functions);
        newFunctions.add(function);
        return new Filter(newFunctions);
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
