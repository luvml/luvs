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

    // Document structure
    html, head, body, title, base, link, meta, style,

    // Sections
    section, article, aside, nav, header, footer, main, hgroup,
    h1, h2, h3, h4, h5, h6,

    // Grouping content
    div, p, hr, pre, blockquote,
    ol, ul, li, dl, dt, dd,
    figure, figcaption,

    // Text semantics
    a, abbr, b, bdi, bdo, br, cite, code, data, dfn, em, i,
    kbd, mark, q, rp, rt, ruby, s, samp, small, span, strong,
    sub, sup, time, u, var, wbr,

    // Edits
    del, ins,

    // Embedded content
    area, audio, img, map, track, video,
    embed, iframe, object, param, picture, portal, source,

    // Canvas and graphics
    canvas, svg,

    // Tabular data
    table, caption, colgroup, col, tbody, thead, tfoot, tr, td, th,

    // Forms
    form, label, input, button, select, datalist, optgroup, option,
    textarea, output, progress, meter, fieldset, legend,

    // Interactive elements
    details, summary, dialog,

    // Scripting
    script, noscript, template, slot,

    // Other valid HTML5 tags
    address, search, menu,

    // ========== Alternate names (with _ suffix) ==========
    // Use these when primary names conflict with luvml.E static methods
    // Example: import static luvml.E.*; import static luvs.HtmlTag.*;
    //          div() is luvml method, div_ is HtmlTag selector

    // Document structure alternates
    html_, head_, body_, title_, base_, link_, meta_, style_,

    // Sections alternates
    section_, article_, aside_, nav_, header_, footer_, main_, hgroup_,
    h1_, h2_, h3_, h4_, h5_, h6_,

    // Grouping content alternates
    div_, p_, hr_, pre_, blockquote_,
    ol_, ul_, li_, dl_, dt_, dd_,
    figure_, figcaption_,

    // Text semantics alternates
    a_, abbr_, b_, bdi_, bdo_, br_, cite_, code_, data_, dfn_, em_, i_,
    kbd_, mark_, q_, rp_, rt_, ruby_, s_, samp_, small_, span_, strong_,
    sub_, sup_, time_, u_, var_, wbr_,

    // Edits alternates
    del_, ins_,

    // Embedded content alternates
    area_, audio_, img_, map_, track_, video_,
    embed_, iframe_, object_, param_, picture_, portal_, source_,

    // Canvas and graphics alternates
    canvas_, svg_,

    // Tabular data alternates
    table_, caption_, colgroup_, col_, tbody_, thead_, tfoot_, tr_, td_, th_,

    // Forms alternates
    form_, label_, input_, button_, select_, datalist_, optgroup_, option_,
    textarea_, output_, progress_, meter_, fieldset_, legend_,

    // Interactive elements alternates
    details_, summary_, dialog_,

    // Scripting alternates
    script_, noscript_, template_, slot_,

    // Other alternates
    address_, search_, menu_;

    @Override
    public String delegatedCharSeqVal() {
        return switch(this){
            case $all -> "*";
            case $root -> ":root";
            case $$backdrop -> "::backdrop";
            default -> {
                String name = name();
                // Strip trailing _ from alternate names (div_ -> div)
                yield name.endsWith("_") ? name.substring(0, name.length() - 1) : name;
            }
        };
    }

    @Override
    public CharSequence getSelectorString() {
        return delegatedCharSeqVal();
    }
    
    public CssRule ____(CssPropertyFrag... properties) {
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
    public CssRule rule(CssPropertyFrag... properties) {
        return new CssRule(name(), properties);
    }
}
