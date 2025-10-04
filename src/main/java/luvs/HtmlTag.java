package luvs;

import luvx.DelegatedCharSeq;

/**
 * Type-safe HTML element tags for use in CSS selectors.
 * Implements DelegatedCharSeq to work seamlessly with selector varargs.
 */
public enum HtmlTag implements DelegatedCharSeq {
    // Common elements
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
        return name();
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

    /**
     * Pseudo-class :hover
     */
    public Selector hover() {
        return Selector.selector(this + ":hover");
    }

    /**
     * Pseudo-class :focus
     */
    public Selector focus() {
        return Selector.selector(this + ":focus");
    }

    /**
     * Pseudo-class :active
     */
    public Selector active() {
        return Selector.selector(this + ":active");
    }

    /**
     * Pseudo-class :first-child
     */
    public Selector firstChild() {
        return Selector.selector(this + ":first-child");
    }

    /**
     * Pseudo-class :last-child
     */
    public Selector lastChild() {
        return Selector.selector(this + ":last-child");
    }

    /**
     * Pseudo-class :nth-child(n)
     */
    public Selector nthChild(CharSequence n) {
        return Selector.selector(this + ":nth-child(" + n + ")");
    }

    /**
     * Pseudo-class :nth-of-type(n)
     */
    public Selector nthOfType(CharSequence n) {
        return Selector.selector(this + ":nth-of-type(" + n + ")");
    }

    /**
     * Pseudo-class :not(selector)
     * Auto-detects CssClass and adds dot prefix
     */
    public Selector not(CharSequence selector) {
        if (selector instanceof CssClass) {
            return Selector.selector(this + ":not(" + ((CssClass) selector).getSelector() + ")");
        }
        return Selector.selector(this + ":not(" + selector + ")");
    }

    // ========== Pseudo-elements ==========

    /**
     * Pseudo-element ::before
     */
    public Selector before() {
        return Selector.selector(this + "::before");
    }

    /**
     * Pseudo-element ::after
     */
    public Selector after() {
        return Selector.selector(this + "::after");
    }

    /**
     * Pseudo-element ::first-line
     */
    public Selector firstLine() {
        return Selector.selector(this + "::first-line");
    }

    /**
     * Pseudo-element ::first-letter
     */
    public Selector firstLetter() {
        return Selector.selector(this + "::first-letter");
    }

    /**
     * Pseudo-element ::selection
     */
    public Selector selection() {
        return Selector.selector(this + "::selection");
    }

    /**
     * Pseudo-element ::placeholder
     */
    public Selector placeholder() {
        return Selector.selector(this + "::placeholder");
    }

    // ========== Attribute Selectors ==========

    /**
     * Attribute selector [attr]
     */
    public Selector withAttr(CharSequence attr) {
        return Selector.selector(this + "[" + attr + "]");
    }

    /**
     * Attribute selector [attr="value"]
     */
    public Selector withAttr(CharSequence attr, CharSequence value) {
        return Selector.selector(this + "[" + attr + "=\"" + value + "\"]");
    }

    /**
     * Direct rule creation shortcut.
     * Usage: div.rule(color("red"), ...)
     */
    public CssRule rule(CssProperty... properties) {
        return new CssRule(name(), properties);
    }
}
