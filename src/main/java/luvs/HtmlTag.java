package luvs;

import luvx.DelegatedCharSeq;

/**
 * Type-safe HTML element tags for use in CSS selectors.
 * Implements DelegatedCharSeq to work seamlessly with selector varargs.
 */
public enum HtmlTag implements DelegatedCharSeq,
                                AttributeSelector,
                                PseudoClassMixin,
                                NegatedPseudoClassMixin,
                                PseudoElementMixin,
                                CombinatorMixin {
    // Special CSS selectors
    $root,        // :root pseudo-class
    $all,         // Universal selector: *
    $$backdrop,   // ::backdrop pseudo-element

    // Common elements
    body,
    div, span, p, a,
    h1, h2, h3, h4, h5, h6,
    ul, ol, li,
    table, tr, td, th, thead, tbody, tfoot,
    form, input, button, label, select, option, textarea,
    section, article, header, footer, nav, main, aside,
    img, figure, figcaption,
    strong, em, code, pre,
    blockquote, cite, q,
    dl, dt, dd,
    iframe, canvas, svg,
    video, audio, source,
    details, summary,
    mark, time, progress, meter;

    @Override
    public String delegatedCharSeqVal() {
        return switch(this){
            case $all -> "*";
            case $root -> ":root";
            case $$backdrop -> "::backdrop";
            default -> name();
        };
    }

    @Override
    public CharSequence getSelectorString() {
        return delegatedCharSeqVal();
    }
    
    public CssRule ____(CssProperty... properties) {
        return asSelector().____(properties);
    }

    // ========== DSL Chaining Methods ==========

    /**
     * Creates selector starting with this tag.
     * Usage: div.asSelector() or p.asSelector()
     */
    public Selector asSelector() {
        return Selector.selector(this);
    }

    /**
     * Compound selector: tag.className (no space)
     * Usage: tr.and(categorized_row).hover() → "tr.categorized_row:hover"
     */
    public Selector and(CssClass cssClass) {
        return Selector.selector(this.toString() + cssClass.getSelector());
    }

    /**
     * Compound selector with multiple classes: tag.class1.class2 (no spaces)
     * Usage: div.and(active, selected) → "div.active.selected"
     */
    public Selector and(CssClass... cssClasses) {
        StringBuilder sb = new StringBuilder(this.toString());
        for (CssClass cls : cssClasses) {
            sb.append(cls.getSelector());
        }
        return Selector.selector(sb.toString());
    }

    /**
     * Child combinator: parent > this
     * Usage: div.childOf(container) generates ".container > div"
     */
    public Selector childOf(CssClass parent) {
        return Selector.selector(parent, ">", this);
    }

    /**
     * Descendant combinator: parent this
     * Usage: div.descendantOf(container) generates ".container div"
     */
    public Selector descendantOf(CssClass parent) {
        return Selector.selector(parent, this);
    }

    // Pseudo-class, negated pseudo-class, and pseudo-element methods inherited from mixin interfaces
    // Attribute selector methods inherited from AttributeSelector interface

    // ========== Common Input Type Shortcuts ==========
    // Shorthand methods for frequently used input[type="..."] selectors

    /**
     * input[type="text"]
     */
    public Selector typeText() {
        return __type("text");
    }

    /**
     * input[type="number"]
     */
    public Selector typeNumber() {
        return __type("number");
    }

    /**
     * input[type="email"]
     */
    public Selector typeEmail() {
        return __type("email");
    }

    /**
     * input[type="password"]
     */
    public Selector typePassword() {
        return __type("password");
    }

    /**
     * input[type="checkbox"]
     */
    public Selector typeCheckbox() {
        return __type("checkbox");
    }

    /**
     * input[type="radio"]
     */
    public Selector typeRadio() {
        return __type("radio");
    }

    /**
     * input[type="date"]
     */
    public Selector typeDate() {
        return __type("date");
    }

    /**
     * input[type="time"]
     */
    public Selector typeTime() {
        return __type("time");
    }

    /**
     * input[type="file"]
     */
    public Selector typeFile() {
        return __type("file");
    }

    /**
     * input[type="range"]
     */
    public Selector typeRange() {
        return __type("range");
    }

    /**
     * input[type="search"]
     */
    public Selector typeSearch() {
        return __type("search");
    }

    /**
     * input[type="tel"]
     */
    public Selector typeTel() {
        return __type("tel");
    }

    /**
     * input[type="url"]
     */
    public Selector typeUrl() {
        return __type("url");
    }

    /**
     * button[type="submit"] or input[type="submit"]
     */
    public Selector typeSubmit() {
        return __type("submit");
    }

    /**
     * button[type="button"] or input[type="button"]
     */
    public Selector typeButton() {
        return __type("button");
    }

    /**
     * button[type="reset"] or input[type="reset"]
     */
    public Selector typeReset() {
        return __type("reset");
    }

    /**
     * Direct rule creation shortcut.
     * Usage: div.rule(color("red"), ...)
     */
    public CssRule rule(CssProperty... properties) {
        return new CssRule(name(), properties);
    }
}
