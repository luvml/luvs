# LuvS Tutorial

A concise guide to LuvS - a type-safe Java CSS generation library. LuvS lets you write CSS rules in Java with compile-time checking, IDE navigation (ctrl+click, find-all-references, rename-symbol), and full composability.

## Maven Coordinates

```xml
<dependency>
    <groupId>io.github.luvml</groupId>
    <artifactId>luvs</artifactId>
    <version>2.0</version>
</dependency>
```

GitHub repo: https://github.com/luvml/luvml

**Related Projects**:
- **[luvml](../luvml/luvml_tutorial.md)** - Type-safe HTML generation. LuvS generates `<style>` blocks that plug directly into luvml pages.

## Core Concepts

LuvS has four main entry points, each a static utility class:

| Class | Role | Example |
|-------|------|---------|
| `P`   | CSS **P**roperties | `color(RED)`, `margin(px(10))` |
| `V`   | CSS **V**alues     | `px(10)`, `rem(1.5)`, `FLEX`, `BOLD` |
| `S`   | CSS **S**electors (static helpers) | `descendant(...)`, `grouping(...)` |
| `Selector` | Fluent selector builder | `selector(container, ">", div)` |

Plus supporting types: `CssClass`, `HtmlTag`, `CssRule`, `CssRules`, `CssVariable`, `Keyframes`.

### Static Imports

```java
import static luvs.P.*;           // Properties: color(), margin(), display(), etc.
import static luvs.V.*;           // Values: px(), rem(), FLEX, BOLD, RED, etc.
import static luvs.S.*;           // Selector combinators: descendant(), grouping(), etc.
import static luvs.Selector.selector;  // Fluent selector builder
import static luvs.HtmlTag.*;     // HTML tag selectors: div, span, input, etc.
import static luvs.CssRule.rule;  // Explicit rule creation
import static luvs.CssRules.*;    // rules(), rulesFrom(), forEachRule(), etc.
import static luvs.CssProp.*;     // Property name constants: TRANSFORM, ALL, etc.
```

## Properties (`P`)

Properties mirror CSS property names in `snake_case`. Each returns a `CssProperty` (an immutable name-value pair).

```java
// Font & text
color(RED)                          // color: red;
font_size(px(16))                   // font-size: 16px;
font_weight(BOLD)                   // font-weight: bold;
text_align(CENTER)                  // text-align: center;
line_height(1.6)                    // line-height: 1.6;
font_family("Arial", "sans-serif")  // font-family: Arial, sans-serif;

// Box model
margin(px(20), AUTO)                // margin: 20px auto;
padding(rem(1), rem(2))             // padding: 1rem 2rem;
border(px(1), SOLID, "#ccc")        // border: 1px solid #ccc;
border_radius(px(8))                // border-radius: 8px;

// Layout
display(FLEX)                       // display: flex;
position(ABSOLUTE)                  // position: absolute;
width(percent(100))                 // width: 100%;
z_index(10)                         // z-index: 10;

// Flexbox
flex_direction(COLUMN)              // flex-direction: column;
justify_content(SPACE_BETWEEN)      // justify-content: space-between;
align_items(AI_CENTER)              // align-items: center;
gap(rem(1))                         // gap: 1rem;

// Grid
grid_template_columns(grid_repeat(AUTO_FIT, grid_minmax(px(200), fr(1))))
// grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
```

### The `important()` helper

```java
background_color(important(PRIMARY))  // background-color: #667eea !important;
```

### The `prop()` escape hatch

For any CSS property not covered by a dedicated method:

```java
prop("appearance", "none")     // appearance: none;
prop("scroll-behavior", "smooth")
```

## Values (`V`)

### Units

```java
px(16)        // 16px
rem(1.5)      // 1.5rem
em(2)         // 2em
percent(80)   // 80%
vh(100)       // 100vh
vw(50)        // 50vw
fr(1)         // 1fr  (CSS grid)
s(0.3)        // 0.3s (time)
ms(200)       // 200ms
deg(45)       // 45deg (angle)
```

### Keywords

```java
AUTO      // "auto"
ZERO      // "0"
INHERIT   // "inherit"
INITIAL   // "initial"
```

### Type-Safe Enum Constants

`V` re-exports commonly used enum constants so a single `import static luvs.V.*` gives you:

```java
// Colors:     RED, BLUE, WHITE, BLACK, GRAY, TRANSPARENT, ...
// Display:    FLEX, GRID, BLOCK, INLINE, NONE, ...
// Position:   ABSOLUTE, RELATIVE, FIXED, STICKY, STATIC
// FontWeight: BOLD, NORMAL, W100..W900
// TextAlign:  LEFT, RIGHT, CENTER, JUSTIFY
// Cursor:     POINTER, MOVE, GRAB, NOT_ALLOWED, ...
// And more:   FlexDirection, JustifyContent, AlignItems, Overflow, etc.
```

Some enum values have prefixed names to avoid conflicts between different enums that share the same word:

```java
JC_CENTER     // JustifyContent.CENTER (prefix JC_ to avoid clash with TextAlign.CENTER)
AI_CENTER     // AlignItems.CENTER
AI_FLEX_START // AlignItems.FLEX_START
BC_COLLAPSE   // BorderCollapse.COLLAPSE
TD_NONE       // TextDecoration.NONE (vs Display.NONE)
OF_COVER      // ObjectFit.COVER
RS_BOTH       // Resize.BOTH
```

### Calc Expressions

`CssUnit` values returned by `px()`, `rem()`, `percent()`, etc. support arithmetic that generates CSS `calc()`:

```java
width(percent(100).minus(px(40)))     // width: calc(100% - 40px);
height(vh(100).minus(rem(4)))         // height: calc(100vh - 4rem);
```

### Math Functions

```java
min(percent(100), px(500))            // min(100%, 500px)
max(vh(50), px(300))                  // max(50vh, 300px)
clamp(px(300), percent(50), px(600))  // clamp(300px, 50%, 600px)
```

### Color Functions

```java
rgb(255, 0, 0)                             // rgb(255, 0, 0)
rgba(0, 0, 0, 0.5)                         // rgba(0, 0, 0, 0.5)
hsl(120, percent(100), percent(50))        // hsl(120, 100%, 50%)
hsla(120, percent(100), percent(50), 0.8)  // hsla(120, 100%, 50%, 0.8)
```

### Gradients

```java
linearGradient(RED, BLUE)                         // linear-gradient(red, blue)
linearGradientWithAngle(deg(135), RED, BLUE)      // linear-gradient(135deg, red, blue)
radialGradient(WHITE, LIGHT_BLUE, BLUE)           // radial-gradient(white, lightblue, blue)

// Gradient color stops
linearGradientWithAngle(deg(135), stop(PRIMARY, 0), stop(SECONDARY, 100))
// linear-gradient(135deg, #667eea 0%, #764ba2 100%)
```

### Transforms (Chainable)

```java
transform(scale(1.05))                                  // transform: scale(1.05);
transform(scale(1.05).rotate(deg(2)).translateY(px(-2))) // transform: scale(1.05) rotate(2deg) translateY(-2px);
```

Available: `scale`, `rotate`, `translate`, `translateX/Y/Z`, `skew`, `skewX/Y`, `rotateX/Y/Z`.

### Filters (Chainable)

```java
filter(blur(px(5)).brightness(1.2).contrast(0.9))
// filter: blur(5px) brightness(1.2) contrast(0.9);
```

Available: `blur`, `brightness`, `contrast`, `grayscale`, `hueRotate`, `invert`, `opacity`, `saturate`, `sepia`, `dropShadow`.

### Transitions

```java
// Single property
transition(BACKGROUND, s(0.3))                       // transition: background 0.3s;

// Single property with timing function
transition(BACKGROUND, s(0.3), EASE_IN_OUT)          // transition: background 0.3s ease-in-out;

// Multiple properties (property-duration pairs)
transition(TRANSFORM, s(0.2), BOX_SHADOW, s(0.2))   // transition: transform 0.2s, box-shadow 0.2s;
```

Property name constants are in `CssProp`: `BACKGROUND`, `TRANSFORM`, `BOX_SHADOW`, `ALL`, `COLOR`, `OPACITY`, etc.

Timing function constants: `EASE`, `LINEAR`, `EASE_IN`, `EASE_OUT`, `EASE_IN_OUT`, `STEP_START`, `STEP_END`. Also `TimingFunction.cubicBezier(0.4, 0, 0.2, 1)` and `TimingFunction.steps(4)` for custom curves.

## Selectors

There are multiple ways to build CSS selectors, from simple to complex.

### HtmlTag Enum

Type-safe HTML tag names. These are enum constants that work directly as selectors:

```java
import static luvs.HtmlTag.*;

body.____(                  // body { ... }
    margin(ZERO)
)

input.focus().____(         // input:focus { ... }
    border_color("blue")
)

a.hover().____(             // a:hover { ... }
    color(RED)
)
```

Available tags: `body`, `div`, `span`, `p`, `a`, `h1`-`h6`, `ul`, `ol`, `li`, `table`, `tr`, `td`, `th`, `thead`, `tbody`, `form`, `input`, `button`, `label`, `select`, `textarea`, `section`, `article`, `header`, `footer`, `nav`, `main`, `img`, `code`, `pre`, `details`, `summary`, `video`, `audio`, and more.

Special selectors (prefixed with `$` to distinguish from HTML tags):
- `$all` maps to the CSS universal selector `*`
- `$root` maps to the CSS `:root` pseudo-class

### CssClass Enum

Define your CSS class names as a Java enum implementing `CssClass`:

```java
public enum Styles implements CssClass {
    container,
    card,
    highlight,
    btn;
}
```

Each enum constant becomes a type-safe CSS class name. The enum name *is* the class name.

```java
// Creating rules
container.____(             // .container { ... }
    width(percent(80)),
    margin(ZERO, AUTO)
)

// Using in luvml HTML
div(class_(container, card), ...)   // <div class="container card">

// Combining with luvml - class_() accepts CssClass enums and strings
div(class_(container, "legacy-class"), ...)
```

**Key point**: `CssClass` enums are just names. CSS rules are defined separately. This means you can define class names in one place and rules in another, or co-locate them - your choice. If you are sharing code at server side (SSR) and client side (teavm) you might want to keeo the CssClass enum accessible also to the teavm side, whereas the definition of those css classes you might want to just keep to the server side, this is just an example usecase. 

### Selector (Fluent Builder)

For complex selectors, use the `Selector` class:

```java
// Explicit parts
selector(container, ">", div).____(...)      // .container > div { ... }
selector(container).child(p).____(...)       // .container > p { ... }
selector(container).descendant(a).____(...)  // .container a { ... }

// String-based (escape hatch)
selector(":root").____(...)                  // :root { ... }
selector("header").____(...)                 // header { ... }
```

### Pseudo-classes (Chainable)

Pseudo-class methods are available on `HtmlTag`, `CssClass`, and `Selector`:

```java
btn.hover().____(...)                        // .btn:hover { ... }
btn.focus().____(...)                        // .btn:focus { ... }
btn.active().____(...)                       // .btn:active { ... }
btn.disabled().____(...)                     // .btn:disabled { ... }
input.checked().____(...)                    // input:checked { ... }
li.firstChild().____(...)                    // li:first-child { ... }
li.nthChild("2n+1").____(...)               // li:nth-child(2n+1) { ... }
btn.not(":disabled").____(...)              // .btn:not(:disabled) { ... }
```

Negated pseudo-classes have dedicated methods too:

```java
btn.notDisabled().____(...)                  // .btn:not(:disabled) { ... }
btn.notHover().____(...)                     // .btn:not(:hover) { ... }
```

### Pseudo-elements

```java
btn.before().____(content("'>'"), ...)       // .btn::before { content: '>'; ... }
btn.after().____(content("' \u2192'"), ...)   // .btn::after { content: ' \u2192'; ... }
p.firstLine().____(font_weight(BOLD))        // p::first-line { font-weight: bold; }
input.placeholder().____(color(GRAY))        // input::placeholder { color: gray; }
```

### Attribute Selectors

Available on `HtmlTag`, `CssClass`, and `Selector`:

```java
// Attribute existence
input.withAttr("disabled").____(...)                // input[disabled] { ... }

// Attribute with value
input.withAttr("type", "checkbox").____(...)         // input[type="checkbox"] { ... }

// Shorthand __ syntax
input.__("readonly").____(...)                       // input[readonly] { ... }
input.__("type", "number").____(...)                 // input[type="number"] { ... }

// Type shorthand
input.__type("checkbox").____(...)                   // input[type="checkbox"] { ... }
input.typeCheckbox().____(...)                        // input[type="checkbox"] { ... } (HtmlTag only)

// Data attribute shorthand
div.__data("theme", "dark").____(...)                // div[data-theme="dark"] { ... }

// Substring matching
a.attrStartsWith("href", "https").____(...)          // a[href^="https"] { ... }
a.attrEndsWith("href", ".pdf").____(...)             // a[href$=".pdf"] { ... }
input.attrSubstring("name", "email").____(...)       // input[name*="email"] { ... }

// Chaining
div.__("data-theme", "dark").__("data-variant", "compact").____(...)
// div[data-theme="dark"][data-variant="compact"] { ... }
```

### Combinators

Direct chaining from `HtmlTag` and `CssClass`:

```java
container.child(div).____(...)           // .container > div { ... }
container.descendant(a).____(...)        // .container a { ... }
container.adjacent(p).____(...)          // .container + p { ... }
container.sibling(div).____(...)         // .container ~ div { ... }
```

Compound selectors (no space):

```java
btn.and(active).____(...)                // .btn.active { ... }
tr.and(categorized_row).____(...)        // tr.categorized_row { ... }
tr.and(categorized_row).hover().____(...)// tr.categorized_row:hover { ... }

// Using S.compound for more control
compound(tr, categorized_row).hover().____(...)  // tr.categorized_row:hover { ... }
```

Grouping (comma-separated selectors):

```java
// Using S.grouping
selector(grouping(
    matches_table.descendant(th),
    matches_table.descendant(td)
)).____(
    padding(rem(0.75)),
    text_align(LEFT)
)
// .matches_table th, .matches_table td { padding: 0.75rem; text-align: left; }
```

### The `____()` Method

The `____()` method (four underscores) creates a `CssRule` from a selector and properties. It's available on `CssClass`, `HtmlTag`, and `Selector`. It is the primary way to create rules. There is also an alias `.rule(...)` if you prefer.

```java
container.____(                      // .container { ... }
    display(FLEX),
    gap(rem(1))
)

// Equivalent:
container.rule(display(FLEX), gap(rem(1)))
```

**NOTE** : From the stand point of common convention followed in this world `____()` might seem odd. But it is a cosmetically chosen so, because anything like `add()` would hurt the eye and divert attention from the core logic; here you expected a css equivalent of `{` which we represent as `.____(` ; if you want to use verbose alternatives, such overloaded functions are also provided.

## CSS Rules and Rule Collections

### CssRule

A single CSS rule (selector + properties):

```java
// Via ____() on a selector (preferred)
body.____(margin(ZERO), padding(ZERO))

// Via static factory
rule("body", margin(ZERO), padding(ZERO))

// Via CssRule.rule()
CssRule.rule(tag("body"), margin(ZERO), padding(ZERO))
```

### CssRules

A collection of CSS rules. This is what you typically return from style methods:

```java
import static luvs.CssRules.rules;

public static CssRules appRules() {
    return rules(
        body.____(margin(ZERO), padding(ZERO)),
        container.____(width(percent(80)), margin(ZERO, AUTO)),
        btn.hover().____(background(PRIMARY_DARK))
    );
}
```

### CssRuleFrag (Sealed Interface)

`CssRuleFrag` is a sealed interface with two implementations: `CssRule` and `CssRules`. This enables `rulesFrom()` to accept a mix of individual rules and rule collections:

```java
public static CssRules allStyles() {
    return rulesFrom(
        baseRules(),         // CssRules
        headerRules(),       // CssRules
        someSpecificRule,    // CssRule
        footerRules()        // CssRules
    );
}
```

### Data-Driven Rules with `forEachRule`

Generate rules from data collections:

```java
// From a Collection
forEachRule(CATEGORY_COLORS, cat ->
    cat_btn.__data("category-id", cat.id()).____(
        background_color(cat.color())
    )
)

// From varargs
forEachRule(cat ->
    cat_btn.__data("category-id", cat.id()).____(
        background_color(cat.color())
    ),
    category1, category2, category3
)
```

This is something plain CSS files cannot do. Similar to how Sass/Less have loops, but with full Java expressiveness and type safety.

### Rendering to `<style>` Block

When used with luvml, a `CssRules` object renders into a `<style>` tag:

```java
import static luvml.E.*;
import static luvml.C.*;    // for style()

var page = html(
    head(
        // style() from luvml.C wraps CssRules in a <style> tag
        style(appRules())
    ),
    body(...)
);
```

The `style()` method from `luvml.C` accepts `CssRuleFrag` (which both `CssRule` and `CssRules` implement, since they implement `CharSequence` via `DelegatedCharSeq`) and wraps it in a `<style>` element.

### Inline Styles

You can also use luvs properties as inline `style` attributes in luvml:

```java
import static luvml.C.styleAttr;

p(
    styleAttr(color("green"), font_weight(BOLD), padding(em(0.5))),
    text("Styled paragraph")
)
// <p style="color: green; font-weight: bold; padding: 0.5em;">Styled paragraph</p>
```

## CSS Variables

Define CSS variables as an enum implementing `CssVariable`:

```java
public enum AppVars implements CssVariable {
    primary_color,
    spacing_unit,
    header_height;
}
```

Underscores in enum names map to hyphens: `primary_color` becomes `--primary-color`.

```java
// Define in :root
$root.____(
    AppVars.primary_color.def("#007bff"),
    AppVars.spacing_unit.def(px(20)),
    AppVars.header_height.def(px(60))
)

// Use with .ref()
color(AppVars.primary_color.ref())               // color: var(--primary-color);
padding(AppVars.spacing_unit.ref())               // padding: var(--spacing-unit);

// With fallback
color(AppVars.primary_color.ref("#000"))           // color: var(--primary-color, #000);

// If you static import AppVars.*
color(primary_color.ref("#000"))           // color: var(--primary-color, #000);

```

There is also a non-enum approach using `V.var()`:

```java
color(var("primary-color"))               // color: var(--primary-color);
color(var("primary-color", "#000"))       // color: var(--primary-color, #000);
custom_var("theme-bg", "#fff")            // --theme-bg: #fff;
```

The enum approach is recommended because it gives you compile-time safety, IDE navigation, and find-all-references.

## Keyframes

```java
import static luvs.Keyframes.*;

var fadeIn = keyframes("fadeIn",
    from(opacity(0)),
    to(opacity(1))
);

// Or with percentage steps
var bounce = keyframes("bounce",
    frame(0, transform(translateY(ZERO))),
    frame(50, transform(translateY(px(-20)))),
    frame(100, transform(translateY(ZERO)))
);
```

`Keyframes` implements `CharSequence` (delegating to the animation name), so you can pass it directly to `animation()`:

```java
// Typed overloads - the keyframes object IS the name reference
animation(fadeIn, s(0.5))                            // animation: fadeIn 0.5s;
animation(fadeIn, s(0.5), EASE_IN_OUT)               // animation: fadeIn 0.5s ease-in-out;
animation(fadeIn, s(0.5), EASE_IN_OUT, s(0.2))       // animation: fadeIn 0.5s ease-in-out 0.2s;

// String fallback still available
animation("fadeIn 0.5s ease-in-out infinite")
```

No string duplication - rename the keyframes and all `animation()` calls update automatically.

## Organization Patterns

LuvS gives you freedom to organize CSS however you want. Here are common patterns.

### Pattern 1: Enum with Companion Style Method

Define class names as an enum, CSS rules in a static method in the same file. This is the **recommended pattern** for most cases - it co-locates names and rules while keeping them separate.

```java
public enum AppStyles implements CssClass {
    container, card, header, btn;

    public static CssRules appRules() {
        return rules(
            container.____(width(percent(80)), margin(ZERO, AUTO)),
            card.____(background(WHITE), border_radius(px(8)), padding(rem(1.5))),
            header.____(background(PRIMARY), color(WHITE)),
            btn.____(display(INLINE_BLOCK), padding(rem(0.5), rem(1)))
        );
    }
}
```

This pattern is similar to how Tailwind co-locates styles with components, but with real abstractions. You can ctrl+click from usage to definition, rename with IDE refactoring, and the compiler catches misspelled class names.

### Pattern 2: Dedicated Style Files

For larger apps, organize styles into separate files by concern (similar to traditional CSS files, but with all the IDE benefits):

```java
// Colors.java - central color palette
public final class Colors {
    public static final String PRIMARY = "#667eea";
    public static final String PRIMARY_DARK = "#5568d3";
    public static final String TEXT_DARK = "#333";
    // ...
}

// AppStyles.java - main layout and component styles
public enum AppStyles implements CssClass {
    header, nav, main, card, footer;

    public static CssRules appRules() { ... }
}

// FormStyles.java - form-specific styles
public enum FormStyles implements CssClass {
    form_container, input_field, submit_btn;

    public static CssRules formRules() { ... }
}
```

Then assemble:

```java
var page = html(
    head(
        style(rulesFrom(
            AppStyles.appRules(),
            FormStyles.formRules()
        ))
    ),
    body(...)
);
```

This is similar to splitting CSS across multiple `.css` files, but with the advantage of compile-time safety and IDE navigation across all files.

### Pattern 3: Co-located Component Styles

Define styles right next to the HTML component that uses them, similar to scoped styles in Vue or styled-components:

```java
public class CardComponent {
    enum S implements CssClass { card, card_title, card_body; }

    public static Frag_I<?> render(String title, String content) {
        return div(class_(S.card),
            h2(class_(S.card_title), title),
            div(class_(S.card_body), content)
        );
    }

    public static CssRules styles() {
        return rules(
            S.card.____(background(WHITE), border_radius(px(8))),
            S.card_title.____(color(PRIMARY), font_size(rem(1.5))),
            S.card_body.____(color(TEXT_MEDIUM))
        );
    }
}
```

### Pattern 4: Mix and Match

Since `rulesFrom()` composes `CssRuleFrag` objects, you can freely combine:

```java
CssRules allStyles = rulesFrom(
    globalResetRules(),                             // from a utility
    AppStyles.appRules(),                            // from a dedicated style file
    CardComponent.styles(),                          // co-located with component
    forEachRule(categories, cat -> ...),             // data-driven rules
    body.____(font_family("Arial", "sans-serif"))    // one-off inline rule
);
```

## Utility-First Patterns (Tailwind / UnoCSS Style)

Tailwind and UnoCSS popularized utility-first CSS: small, composable class names that each map to a few CSS properties. In luvs, you can build the same system - but the "class names" are Java constants (compile-time safe, ctrl+click, find-all-references) and the "rules" are generated by Java functions (parameterized, loopable, composable).

### The Utility Class (`Uc`)

The key building block is a class that serves dual purpose: it's a **class name** (for use in HTML `class_()`) and a **rule generator** (for CSS output). Define it once in your project:

```java
public final class Uc implements CharSequence {
    private static final java.util.LinkedHashSet<Uc> registry = new java.util.LinkedHashSet<>();

    private final String name;
    private final CssProperty[] properties;

    private Uc(String name, CssProperty... properties) {
        this.name = name;
        this.properties = properties;
        registry.add(this);
    }

    // For class_() — returns just the class name
    @Override public String toString() { return name; }

    // CharSequence delegation to name
    @Override public int length() { return name.length(); }
    @Override public char charAt(int i) { return name.charAt(i); }
    @Override public CharSequence subSequence(int s, int e) { return name.subSequence(s, e); }

    // Generates: .p_4 { padding: 1rem; }
    public CssRule rule() { return new CssRule("." + name, properties); }

    // Generates rules for ALL utilities that have been used
    public static CssRules allRules() {
        return CssRules.rules(registry.stream()
            .map(Uc::rule).toArray(CssRule[]::new));
    }

    // Factory
    public static Uc uc(String name, CssProperty... props) { return new Uc(name, props); }
}
```

The registry auto-collects — only utilities actually instantiated in your code get CSS generated. No purge step needed.

### Utility Factory Functions ($ prefix)

Define shorthand functions that create `Uc` instances. Use `$` prefix to avoid clashing with luvml HTML element methods (`p()` is paragraph in luvml):

```java
import static luvs.P.*;
import static luvs.V.*;
import static myapp.Uc.uc;

public final class U {
    private U() {}

    private static final double[] SCALE = {0, 0.25, 0.5, 0.75, 1, 1.25, 1.5, 2, 2.5, 3, 4, 5, 6};

    // Spacing (scale-based: $p(4) → padding: 1rem)
    public static Uc $p(int n)  { return uc("p_" + n, padding(rem(SCALE[n]))); }
    public static Uc $px(int n) { return uc("px_" + n, padding_left(rem(SCALE[n])), padding_right(rem(SCALE[n]))); }
    public static Uc $py(int n) { return uc("py_" + n, padding_top(rem(SCALE[n])), padding_bottom(rem(SCALE[n]))); }
    public static Uc $m(int n)  { return uc("m_" + n, margin(rem(SCALE[n]))); }
    public static Uc $gap(int n){ return uc("gap_" + n, gap(rem(SCALE[n]))); }

    // Layout
    public static Uc $flex()   { return uc("flex", display(FLEX)); }
    public static Uc $grid()   { return uc("grid", display(GRID)); }
    public static Uc $hidden() { return uc("hidden", display(NONE)); }

    // Rounding
    public static Uc $rounded(int px) { return uc("rounded_" + px, border_radius(V.px(px))); }

    // Elevation
    public static Uc $shadow(int level) {
        return switch (level) {
            case 1 -> uc("shadow_sm", box_shadow(ZERO, V.px(1), V.px(3), rgba(0,0,0,0.1)));
            case 2 -> uc("shadow_md", box_shadow(ZERO, V.px(4), V.px(6), rgba(0,0,0,0.1)));
            case 3 -> uc("shadow_lg", box_shadow(ZERO, V.px(10), V.px(15), rgba(0,0,0,0.15)));
            default -> uc("shadow_none", box_shadow("none"));
        };
    }
}
```

Usage — two sides of the same coin:

```java
import static myapp.U.*;

// HTML side: $p(4) is a class name in class_()
div(class_($p(4), $flex(), $rounded(8), $shadow(2)),
    h1(class_($m(0)), "Title")
)

// CSS side: generate rules for everything that was used
style(Uc.allRules())
// Produces: .p_4 { padding: 1rem; } .flex { display: flex; } .rounded_8 { border-radius: 8px; } ...
```

Compare to Tailwind `class="p-4 flex rounded-lg shadow-md"` — same brevity, but parameterized and compile-time checked.

### Pre-declared Utility Enum (Full Tailwind Pattern)

For a Tailwind-like experience where all utility classes are pre-declared, use an enum with a switch mapping:

```java
public enum Tw implements CssClass {
    // Layout
    flex, inline_flex, block, inline_block, grid, hidden,
    items_center, items_start, items_end,
    justify_center, justify_between, justify_around,
    flex_col, flex_row, flex_wrap_,

    // Typography
    text_center, text_left, text_right,
    font_bold, font_medium, font_normal,
    text_xs, text_sm, text_base, text_lg, text_xl, text_2xl,
    truncate,

    // Borders & Effects
    rounded_sm, rounded, rounded_md, rounded_lg, rounded_full,
    shadow_sm, shadow, shadow_md, shadow_lg;

    CssProperty[] props() {
        return switch (this) {
            case flex         -> a(display(FLEX));
            case inline_flex  -> a(display(INLINE_FLEX));
            case block        -> a(display(BLOCK));
            case inline_block -> a(display(INLINE_BLOCK));
            case grid         -> a(display(GRID));
            case hidden       -> a(display(NONE));
            case items_center -> a(align_items(AI_CENTER));
            case items_start  -> a(align_items(AI_FLEX_START));
            case items_end    -> a(align_items(AI_FLEX_END));
            case justify_center  -> a(justify_content(JC_CENTER));
            case justify_between -> a(justify_content(SPACE_BETWEEN));
            case justify_around  -> a(justify_content(SPACE_AROUND));
            case flex_col     -> a(flex_direction(COLUMN));
            case flex_row     -> a(flex_direction(ROW));
            case flex_wrap_   -> a(flex_wrap(FW_WRAP));
            case text_center  -> a(text_align(CENTER));
            case text_left    -> a(text_align(LEFT));
            case text_right   -> a(text_align(RIGHT));
            case font_bold    -> a(font_weight(BOLD));
            case font_medium  -> a(font_weight(W500));
            case font_normal  -> a(font_weight(NORMAL));
            case text_xs      -> a(font_size(rem(0.75)));
            case text_sm      -> a(font_size(rem(0.875)));
            case text_base    -> a(font_size(rem(1)));
            case text_lg      -> a(font_size(rem(1.125)));
            case text_xl      -> a(font_size(rem(1.25)));
            case text_2xl     -> a(font_size(rem(1.5)));
            case truncate     -> a(overflow(HIDDEN), prop("text-overflow", "ellipsis"),
                                   white_space(WS_NOWRAP));
            case rounded_sm   -> a(border_radius(px(2)));
            case rounded      -> a(border_radius(px(4)));
            case rounded_md   -> a(border_radius(px(6)));
            case rounded_lg   -> a(border_radius(px(8)));
            case rounded_full -> a(border_radius(percent(50)));
            case shadow_sm    -> a(box_shadow(ZERO, px(1), px(2), rgba(0,0,0,0.05)));
            case shadow       -> a(box_shadow(ZERO, px(1), px(3), rgba(0,0,0,0.1)));
            case shadow_md    -> a(box_shadow(ZERO, px(4), px(6), rgba(0,0,0,0.1)));
            case shadow_lg    -> a(box_shadow(ZERO, px(10), px(15), rgba(0,0,0,0.1)));
        };
    }

    private static CssProperty[] a(CssProperty... p) { return p; }

    /** Generates CSS for all utilities. */
    public static CssRules allRules() {
        return rules(java.util.Arrays.stream(values())
            .map(tw -> tw.____(tw.props()))
            .toArray(CssRule[]::new));
    }
}
```

Use in HTML just like Tailwind — but type-safe:

```java
// Tailwind: <div class="flex items-center justify-between rounded-lg shadow-md">
div(class_(flex, items_center, justify_between, rounded_lg, shadow_md), ...)
```

Misspell a class name? Compile error. Rename `shadow_md`? IDE renames all usages.

### Shortcuts (Composed Utility Classes)

UnoCSS "shortcuts" combine multiple utilities under one name. In luvs, define classes whose rules compose utility functions:

```java
enum Shortcut implements CssClass {
    btn, card, input_field;

    public static CssRules allRules() {
        return rules(
            btn.____(
                display(INLINE_BLOCK), padding(rem(0.5), rem(1)), font_weight(W500),
                border_radius(px(8)), box_shadow(ZERO, px(1), px(3), rgba(0,0,0,0.1)),
                cursor(POINTER), transition(BACKGROUND, s(0.2))
            ),

            card.____(
                background(WHITE), border_radius(px(8)), padding(rem(1.5)),
                box_shadow(ZERO, px(2), px(8), rgba(0,0,0,0.1))
            ),

            input_field.____(
                padding(rem(0.5), rem(0.75)), border(px(1), SOLID, "#ddd"),
                border_radius(px(4)), font_size(rem(1)),
                transition(BORDER_COLOR, s(0.2))
            )
        );
    }
}
```

### Rule Generators (Reusable Multi-Rule Patterns)

For patterns that produce multiple rules (base + hover + disabled etc.), return `CssRules`:

```java
static CssRules hoverLift(CssClass cls) {
    return rules(
        cls.____(transition(TRANSFORM, s(0.2), BOX_SHADOW, s(0.2))),
        cls.hover().____(
            transform(translateY(px(-4))),
            box_shadow(ZERO, px(4), px(16), rgba(0,0,0,0.15))
        )
    );
}

static CssRules buttonVariant(CssClass cls, CharSequence bg, CharSequence hoverBg) {
    return rules(
        cls.____(
            display(INLINE_BLOCK), padding(rem(0.5), rem(1)), background(bg),
            color(WHITE), border_radius(px(4)), cursor(POINTER),
            transition(BACKGROUND, s(0.3))
        ),
        cls.hover().____(background(hoverBg)),
        cls.disabled().____(P.opacity(0.5), cursor(NOT_ALLOWED))
    );
}
```

### Assembling Everything

Assemble with `rulesFrom()` — it accepts any mix of `CssRules` and `CssRule` (`CssRuleFrag`):

```java
public static CssRules allAppStyles() {
    return rulesFrom(
        Tw.allRules(),                          // utility classes
        Shortcut.allRules(),                    // shortcuts
        hoverLift(case_card),                   // rule generators
        buttonVariant(btn_primary, PRIMARY, PRIMARY_DARK),
        buttonVariant(btn_danger, DANGER, DANGER_DARK),
        forEachRule(CATEGORIES, cat ->          // data-driven
            cat_btn.__data("category-id", cat.id()).____( background_color(cat.color()) )
        ),
        AppStyles.appRules()                    // app-specific
    );
}
```

Organize sub-sections as private methods returning `CssRules`, then assemble in one `rulesFrom()` call — keeps large stylesheets readable.

### What you get vs Tailwind / UnoCSS

| | Tailwind / UnoCSS | luvs |
|---|---|---|
| Small composable utilities | Yes (string class names) | Yes (enum constants or `Uc` objects) |
| Utility-first workflow | Yes | Yes |
| Co-located with HTML | Yes | Yes |
| Compile-time typo checking | No | Yes |
| ctrl+click to definition | No | Yes |
| find-all-references, rename | No | Yes |
| Parameterized utilities | No (`p-4` is fixed) | Yes (`$p(4)`, `$shadow(2)`) |
| Data-driven rule generation | No | Yes (`forEachRule`) |
| Purge step needed | Yes | No (only used classes exist) |
| Runtime cost | Varies | None (compile-time) |

## Complete Example

Putting it all together:

```java
import static luvml.E.*;
import static luvml.C.*;
import static luvml.T.text;
import static luvs.P.*;
import static luvs.V.*;
import static luvs.Selector.selector;
import static luvs.HtmlTag.*;
import static luvs.CssRules.*;
import static luvs.CssProp.*;
import luvml.o.HtmlRenderer;

public class TodoApp {

    enum Cls implements CssClass {
        app, todo_list, todo_item, completed, add_btn;
    }

    enum Theme implements CssVariable {
        bg_color, text_color, accent;
    }

    static CssRules styles() {
        return rules(
            // CSS variables
            $root.____(
                Theme.bg_color.def("#f5f5f5"),
                Theme.text_color.def("#333"),
                Theme.accent.def("#4a90d9")
            ),

            // Reset
            $all.____(
                margin(ZERO), padding(ZERO), box_sizing(BORDER_BOX)
            ),

            body.____(
                font_family("system-ui", "sans-serif"),
                background_color(Theme.bg_color.ref()),
                color(Theme.text_color.ref())
            ),

            // Layout
            Cls.app.____(
                max_width(px(600)),
                margin(rem(2), AUTO),
                padding(ZERO, rem(1))
            ),

            // List
            Cls.todo_list.____(
                display(FLEX),
                flex_direction(COLUMN),
                gap(rem(0.5))
            ),

            // Items
            Cls.todo_item.____(
                display(FLEX),
                align_items(AI_CENTER),
                padding(rem(1)),
                background(WHITE),
                border_radius(px(8)),
                box_shadow(ZERO, px(1), px(3), rgba(0, 0, 0, 0.1)),
                transition(TRANSFORM, s(0.2), BOX_SHADOW, s(0.2))
            ),

            Cls.todo_item.hover().____(
                transform(translateY(px(-2))),
                box_shadow(ZERO, px(4), px(8), rgba(0, 0, 0, 0.15))
            ),

            // Completed state
            Cls.completed.____(
                P.opacity(0.6),
                text_decoration("line-through")
            ),

            // Button
            Cls.add_btn.____(
                background(Theme.accent.ref()),
                color(WHITE),
                border("none"),
                padding(rem(0.75), rem(1.5)),
                border_radius(px(8)),
                cursor(POINTER),
                font_size(rem(1)),
                transition(BACKGROUND, s(0.3))
            ),

            Cls.add_btn.hover().____(
                P.opacity(0.9)
            ),

            Cls.add_btn.disabled().____(
                P.opacity(0.5),
                cursor(NOT_ALLOWED)
            )
        );
    }

    public static void main(String[] args) {
        var page = html(
            head(
                title("Todo App"),
                style(styles())
            ),
            body(
                div(class_(Cls.app),
                    h1("My Todos"),
                    div(class_(Cls.todo_list),
                        div(class_(Cls.todo_item), text("Learn LuvS")),
                        div(class_(Cls.todo_item, Cls.completed), text("Set up project"))
                    ),
                    luvml.E.button(class_(Cls.add_btn), text("Add Todo"))
                )
            )
        );

        System.out.println("<!DOCTYPE html>");
        System.out.println(HtmlRenderer.asFormattedString(page));
    }
}
```

## Quick Reference

### Property methods that accept type-safe enums

| Property | Enum type | Common values |
|----------|-----------|---------------|
| `display()` | `Display` | `FLEX`, `GRID`, `BLOCK`, `NONE` |
| `position()` | `Position` | `ABSOLUTE`, `RELATIVE`, `FIXED`, `STICKY` |
| `font_weight()` | `FontWeight` | `BOLD`, `NORMAL`, `W100`-`W900` |
| `text_align()` | `TextAlign` | `LEFT`, `RIGHT`, `CENTER`, `JUSTIFY` |
| `flex_direction()` | `FlexDirection` | `ROW`, `COLUMN`, `ROW_REVERSE` |
| `justify_content()` | `JustifyContent` | `FLEX_START`, `SPACE_BETWEEN`, `JC_CENTER` |
| `align_items()` | `AlignItems` | `AI_CENTER`, `STRETCH`, `BASELINE` |
| `overflow()` | `Overflow` | `HIDDEN`, `SCROLL`, `OV_AUTO` |
| `cursor()` | `Cursor` | `POINTER`, `GRAB`, `NOT_ALLOWED` |
| `border_collapse()` | `BorderCollapse` | `BC_COLLAPSE`, `BC_SEPARATE` |
| `box_sizing()` | `BoxSizing` | `BORDER_BOX`, `CONTENT_BOX` |
| `object_fit()` | `ObjectFit` | `OF_COVER`, `OF_CONTAIN` |
| `resize()` | `Resize` | `VERTICAL`, `HORIZONTAL`, `RS_BOTH` |
| `font_style()` | `FontStyle` | `FS_ITALIC`, `FS_NORMAL` |
| `white_space()` | `WhiteSpace` | `WS_NOWRAP`, `WS_PRE` |
| `flex_wrap()` | `FlexWrap` | `FW_WRAP`, `FW_NOWRAP` |
| `flex()` | `Flex` | `FLEX_1`, `FLEX_AUTO`, `FLEX_NONE` |
| `color()` | `Color` | `RED`, `BLUE`, `WHITE`, `BLACK`, etc. |
| `background_color()` | `Color` | Same as above |
| `text_decoration()` | - | `TD_NONE`, `UNDERLINE`, `LINE_THROUGH` |
| `border()` | - | `SOLID`, `DASHED`, `DOTTED` (BorderStyle) |
| `transition()` | `TimingFunction` | `EASE`, `LINEAR`, `EASE_IN_OUT` |
| `animation()` | `TimingFunction` | Same as above; also `cubicBezier()`, `steps()` |

All property methods also accept `CharSequence` (raw strings) as a fallback, so you are never blocked.

### Selector chaining summary

Starting from `HtmlTag` or `CssClass`:
```
.hover()  .focus()  .active()  .disabled()  .checked()  .firstChild()  .nthChild()
.not()    .notHover()  .notDisabled()  ...
.before() .after()  .placeholder()  .firstLine()  .firstLetter()  .selection()
.child()  .descendant()  .adjacent()  .sibling()
.and()    .withAttr()  .__()  .__type()  .__data()
.attrStartsWith()  .attrEndsWith()  .attrSubstring()  .attrContains()  .attrDashMatch()
```

All return `Selector`, which itself supports further chaining of pseudo-classes, pseudo-elements, and attribute selectors.

## Real-World Notes

Observations from using luvs in production.

### Line Count

In a real project, the Java DSL was ~32% more lines than the CSS it replaced. The increase is **not** from Java verbosity in the property lines themselves — the DSL maps 1:1 to CSS and CSS properties are short, so each `color(RED)` or `padding(rem(1))` is about the same length as `color: red` or `padding: 1rem`. The extra lines come from:

- **Enum declarations** — each class name is one word on its own line (`container, card, header, btn;`), whereas CSS has no equivalent declaration
- **Method signatures and structure** — organizing rules into named methods (`videoSectionRules()`, `modalRules()`) adds a few lines per group
- **Imports** — with wildcard imports (`import static luvs.P.*`) these are minimal, typically 5-6 lines

The line increase buys logical decomposition that flat CSS lacks.

### Centralized Color Palette

CSS variables (`:root { --primary: #667eea }`) solve color duplication within a single CSS file. But plain CSS doesn't have a way to share constants across multiple `.css` files — each file is independent. Sass has shared variables across partials, but that's a preprocessor, not CSS.

In luvs, since styles are Java, a shared `Colors.java` file works across any number of style files naturally:

```java
public final class Colors {
    public static final String PRIMARY = "#667eea";
    public static final String BORDER_LIGHT = "#ddd";
    public static final String TEXT_MUTED = "#666";
    // ...
}
```

Any style file does `import static Colors.*` and uses the same palette. A new component picks from the existing constants instead of hardcoding a slightly-off `#6a7fcb`. This is just standard Java — nothing special about luvs here — but it's a benefit of styles being code rather than a separate string-based language.

### The `prop()` Escape Hatch in Practice

Some vendor-prefixed or newer CSS properties aren't in the DSL yet:

```java
prop("-webkit-line-clamp", "3"),
prop("-webkit-box-orient", "vertical")
```

This is expected — CSS has hundreds of properties and vendor extensions. The `prop()` fallback means you're never blocked; you just lose type safety for that one property.

### Trade-off: No Instant Edit-Refresh

CSS changes need a recompile. In a typical Maven/Spring Boot project with hot-reload (e.g., spring-boot-devtools), this is a few seconds. For projects without hot-reload, this is the main ergonomic cost compared to editing a `.css` file and refreshing the browser.
