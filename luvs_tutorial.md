# LuvS Tutorial

Type-safe Java CSS generation with compile-time checking, IDE navigation (ctrl+click, find-all-references, rename-symbol), and full composability.

**Maven:** `io.github.luvml:luvs:2.0` • **GitHub:** https://github.com/luvml/luvml • **Related:** [luvml](../luvml/luvml_tutorial.md) for type-safe HTML

## Comprehensive CSS Coverage

**394 CSS properties** - All standard properties, modern features (2020-2024), logical properties (inline/block), SVG (20+ properties), masking (9 properties), print (@page support), vendor-specific extensions.

**Modern at-rules** - @font-face, @supports, @container, @layer, @page, @media with full nesting support, logical operators (and/or/not), feature queries.

**Complete feature set** - Transforms (chainable), filters (chainable), gradients (linear/radial with stops), calc expressions (arithmetic), color functions (rgb/rgba/hsl/hsla/color-mix/relative color syntax), transitions, animations, keyframes, CSS variables, media queries (breakpoints, dark mode, reduced motion), container queries, cascade layers, pseudo-classes (hover/focus/disabled/checked/nth-child/...), pseudo-elements (before/after/placeholder/first-line/...), attribute selectors (existence/value/substring matching), combinators (child/descendant/adjacent/sibling/compound/grouping).

**Verified against** - W3Schools (332+ core properties ✓), CSS-Tricks top properties ✓, MDN comprehensive lists ✓.

## Core Concepts

Four main entry points: **P** (properties like `color()`, `margin()`), **V** (values like `px(10)`, `rem(1.5)`, enum constants `FLEX`, `BOLD`), **S** (selector combinators), **Selector** (fluent builder). Supporting types: `CssClass`, `HtmlTag`, `CssRule`, `CssRules`, `CssVariable`, `Keyframes`, `MQ`, `FontFace`, `Supports`, `ContainerQuery`, `Layer`.

**Static imports** (use what you need):
```java
import static luvs.P.*;            // Properties
import static luvs.V.*;            // Values & constants
import static luvs.HtmlTag.*;      // HTML tag selectors (div, span, input, ...)
import static luvs.CssRules.*;     // rules(), rulesFrom(), forEachRule()
import static luvs.CssProp.*;      // Property name constants (TRANSFORM, ALL, ...)
import static luvs.MQ.*;           // Media queries
import static luvs.FontFace.*;     // @font-face
import static luvs.Supports.*;     // @supports
import static luvs.ContainerQuery.*;  // @container
import static luvs.Layer.*;        // @layer
```

## Properties (`P`)

Properties mirror CSS names in `snake_case`, returning `CssProperty` (immutable name-value pair). **394 properties** covering standard CSS, modern features, logical properties, SVG, masking, print, and vendor-specific.

```java
// Core examples (typography, box model, layout, flexbox, grid)
color(RED), font_size(px(16)), font_weight(BOLD), text_align(CENTER), line_height(1.6)
margin(px(20), AUTO), padding(rem(1), rem(2)), border(px(1), SOLID, "#ccc"), border_radius(px(8))
display(FLEX), position(ABSOLUTE), width(percent(100)), z_index(10)
flex_direction(COLUMN), justify_content(SPACE_BETWEEN), align_items(AI_CENTER), gap(rem(1))
grid_template_columns(grid_repeat(AUTO_FIT, grid_minmax(px(200), fr(1))))

// Alignment (flexbox/grid): align_self, align_content, justify_items, justify_self, place_*
// Shorthands: font(), grid_template(), flex_flow(), list_style(), border_width(), border_style()
// Modern: aspect_ratio(), backdrop_filter(), clip_path(), scroll_*, overscroll_*, will_change(), contain()
// Logical: margin_inline*, padding_block*, border_inline*, inset_*, inline_size, block_size
// Transforms: rotate(), scale(), translate() (individual), transform() (combined)
// SVG: fill(), stroke(), stroke_width(), stroke_dasharray(), paint_order(), mask*
// Print: size(), marks(), bleed() (for @page)
// Misc: visibility(), float_(), clear(), order(), animation_play_state(), counter_*, all()
```

**Helpers:** `important(value)` → `value !important`, `prop(name, value)` → escape hatch for any property not covered.

## Values (`V`)

**Units:** `px()`, `rem()`, `em()`, `percent()`, `vh()`, `vw()`, `vmin()`, `vmax()`, `fr()` (grid), `s()`, `ms()` (time), `deg()` (angle), `cm()`, `mm()`, `in()`, `pt()`, `pc()` (print)
**Keywords:** `AUTO`, `ZERO`, `INHERIT`, `INITIAL`, `UNSET`, `REVERT`
**Enum constants** (via `import static luvs.V.*`): colors (`RED`, `BLUE`, `WHITE`, `TRANSPARENT`, ...), display (`FLEX`, `GRID`, `BLOCK`, `NONE`, ...), position (`ABSOLUTE`, `RELATIVE`, `FIXED`, `STICKY`), font-weight (`BOLD`, `NORMAL`, `W100`-`W900`), text-align, cursor, flex/grid alignment, overflow, etc. Prefixed to avoid conflicts: `JC_CENTER` (JustifyContent), `AI_CENTER` (AlignItems), `BC_COLLAPSE`, `TD_NONE`, `OF_COVER`, `RS_BOTH`.

**Calc:** `width(percent(100).minus(px(40)))` → `calc(100% - 40px)`, arithmetic on `CssUnit` values
**Math:** `min()`, `max()`, `clamp()`
**Colors:** `rgb()`, `rgba()`, `hsl()`, `hsla()`, `colorMix()` (color-mix in various spaces), `rgbFrom()`, `hslFrom()`, `oklchFrom()` (relative color syntax)
**Gradients:** `linearGradient()`, `linearGradientWithAngle()`, `radialGradient()`, `stop()` for color stops
**Transforms:** chainable `scale()`, `rotate()`, `translate()`, `translateX/Y/Z()`, `skew()`, `skewX/Y()`, `rotateX/Y/Z()` → `transform(scale(1.05).rotate(deg(2)))`
**Filters:** chainable `blur()`, `brightness()`, `contrast()`, `grayscale()`, `hueRotate()`, `invert()`, `saturate()`, `sepia()`, `dropShadow()` → `filter(blur(px(5)).brightness(1.2))`
**Transitions:** `transition(BACKGROUND, s(0.3), EASE_IN_OUT)` with property name constants from `CssProp` (`TRANSFORM`, `ALL`, `COLOR`, ...) and timing functions (`EASE`, `LINEAR`, `EASE_IN_OUT`, `cubicBezier()`, `steps()`)

## Selectors

Build selectors via fluent DSL on `HtmlTag` enum, `CssClass` enum, or `Selector` class. **Best practice:** use the fluent API (not verbose `selector(...)` calls), add CSS comments when learning.

```java
// Fluent DSL examples (✅ GOOD - readable, type-safe, IDE-friendly)
video_chip.disabled().____(...)                              // .video_chip:disabled
container.child(div).____(...)                               // .container > div
nav.descendant(a.hover()).____(...)                          // nav a:hover
input.typeCheckbox().____(...)                               // input[type="checkbox"]
video_chip.child(input.typeCheckbox().checked()).____(...)
// .video_chip > input[type="checkbox"]:checked

// Avoid verbose selector(...) calls (❌ hard to read, not type-safe)
// Use fluent DSL for readability, type safety, IDE navigation (find-all-references, rename-symbol)
```

**HtmlTag enum:** Type-safe tag selectors - `body`, `div`, `span`, `p`, `a`, `h1`-`h6`, `ul`, `ol`, `li`, `table`, `form`, `input`, `button`, `label`, `select`, `textarea`, `section`, `article`, `header`, `footer`, `nav`, `main`, `img`, `code`, `pre`, `details`, `summary`, `video`, `audio`, etc. Special: `$all` (`*`), `$root` (`:root`), `$$backdrop` (`::backdrop`).

**CssClass enum:** Define class names as `enum Styles implements CssClass { container, card, btn; }`. Enum name = class name. Rules defined separately (flexibility for SSR/client code sharing). Use in HTML via `div(class_(container, card), ...)` → `<div class="container card">`.

**Selector class:** Rarely needed - use fluent DSL instead. Only for edge cases: `selector("[dir='rtl']").____(...)`, `selector(":is(h1, h2, h3)").____(...)`. Prefer `$root.____(...)`, `container.child(div).____(...)` over `selector(...)` calls.

**Pseudo-classes** (chainable on `HtmlTag`, `CssClass`, `Selector`): `hover()`, `focus()`, `active()`, `disabled()`, `checked()`, `firstChild()`, `lastChild()`, `nthChild()`, `not()`, `notHover()`, `notDisabled()`, etc.

**Pseudo-elements:** `before()`, `after()`, `firstLine()`, `firstLetter()`, `placeholder()`, `selection()`

**Attribute selectors:** `withAttr(name)`, `withAttr(name, value)`, shorthand `__(name)`, `__(name, value)`, `__type(value)`, `typeCheckbox()` (HtmlTag-specific), `__data(key, value)`, substring matching `attrStartsWith()`, `attrEndsWith()`, `attrSubstring()`, `attrContains()`, `attrDashMatch()`. Chainable: `div.__("data-theme", "dark").__("data-variant", "compact").____(...)`

### Combinators

**Use the fluent DSL methods below** - avoid constructing verbose `selector(part1, ">", part2)` calls manually. The fluent API is more readable and type-safe.

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

A single CSS rule (selector + properties). **Always use the `____()` method** on selectors:

```java
body.____(margin(ZERO), padding(ZERO))  // body { margin: 0; padding: 0; }
```

**CSS Output:**
```css
body {
    margin: 0;
    padding: 0;
}
```

That's it! Don't use verbose constructors like `CssRule.rule(...)` or `rule(...)` - they exist for internal use but defeat the purpose of the fluent DSL.

### CssRules

A collection of CSS rules. This is what you typically return from style methods:

```java
import static luvs.CssRules.rules;
import static com.myapp.Styles.*;  // Assuming Styles enum defines container, btn

public static CssRules appRules() {
    return rules(
        body.____(margin(ZERO), padding(ZERO)),
        container.____(width(percent(80)), margin(ZERO, AUTO)),
        btn.hover().____(background(PRIMARY_DARK))
    );
}
```

**CSS Output:**
```css
body {
    margin: 0;
    padding: 0;
}

.container {
    width: 80%;
    margin: 0 auto;
}

.btn:hover {
    background: #5568d3;
}
```

### CssRuleFrag (Sealed Interface)

`CssRuleFrag` is a sealed interface that permits: `CssRule`, `CssRules`, `CssComment`, `CssEmptyLine`, and `MediaQuery`. This enables `rulesFrom()` to accept a mix of rules, media queries, comments, and empty lines:

```java
public static CssRules allStyles() {
    return rulesFrom(
        comment("Base styles section"),
        baseRules(),         // CssRules
        emptyLine(),
        comment("Header styles"),
        headerRules(),       // CssRules
        someSpecificRule,    // CssRule
        emptyLine(),
        footerRules()        // CssRules
    );
}
```

### CSS Comments and Empty Lines

Organize your CSS with comments and visual separation:

```java
import static luvs.CssComment.comment;
import static luvs.CssComment.commentBlock;
import static luvs.CssEmptyLine.emptyLine;

var css = rules(
    commentBlock("=== Reset and Base Styles ==="),
    $all.____(margin(ZERO), padding(ZERO)),
    body.____(font_family("system-ui", "sans-serif")),
    emptyLine(),

    comment("Layout Components"),
    container.____(max_width(px(1200)), margin(ZERO, AUTO)),
    emptyLine(),
    emptyLine(),

    comment("Interactive Elements"),
    btn.hover().____(transform(scale(1.05)))
);
```

**Output:**
```css
/* === Reset and Base Styles === */
* {
    margin: 0;
    padding: 0;
}

body {
    font-family: system-ui, sans-serif;
}


/* Layout Components */
.container {
    max-width: 1200px;
    margin: 0 auto;
}



/* Interactive Elements */
.btn:hover {
    transform: scale(1.05);
}
```

Comments render as `/* text */` and empty lines add visual separation. Both preserve their exact position in the output, making generated CSS more readable and maintainable.

**`comment()` vs `commentBlock()`:**

Both render identically as `/* text */`. The difference is **spacing** when rendered inside `CssRules`:

- `comment("text")` — single newline before it (tight, sits close to the next rule)
- `commentBlock("text")` — double newline before it (extra blank line, stands out as a section header)

Varargs differ too: `comment("a", "b")` concatenates → `/* ab */`, while `commentBlock("line1", "line2")` joins with newlines → `/* line1\nline2 */`.

```java
// In a rulesFrom() or rules() context:
rulesFrom(
    rule1,
    comment("Inline note"),       // single blank line before — tight
    rule2,
    commentBlock("=== Section ==="),  // double blank line before — visual break
    rule3
)
```

Output:
```css
.rule1 { ... }

/* Inline note */

.rule2 { ... }


/* === Section === */

.rule3 { ... }
```

**Use cases:**
- Section headers when merging multiple style files
- Documenting complex selectors or property combinations
- Visual separation between logically distinct rule groups
- Leaving placeholders or notes in generated CSS

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

**Use static imports for clean syntax:**

```java
import static com.myapp.AppVars.*;

// Define in :root
$root.____(
    primary_color.def("#007bff"),
    spacing_unit.def(px(20)),
    header_height.def(px(60))
)
```

**CSS Output:**
```css
:root {
    --primary-color: #007bff;
    --spacing-unit: 20px;
    --header-height: 60px;
}
```

**Use with .ref():**

```java
color(primary_color.ref())               // color: var(--primary-color);
padding(spacing_unit.ref())               // padding: var(--spacing-unit);

// With fallback
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

## Media Queries (`MQ`)

Use `import static luvs.MQ.*;` for the full media query DSL.

### Basic Breakpoints

```java
import static luvs.MQ.*;

var tablet = media(minWidth(px(768)),
    container.____(padding(rem(2)), max_width(px(1200))),
    sidebar.____(display(BLOCK))
);

var mobile = media(maxWidth(px(767)),
    sidebar.____(display(NONE)),
    nav.____(flex_direction(COLUMN))
);
```

Renders as:
```css
@media (min-width: 768px) {
    .container {
        padding: 2rem;
        max-width: 1200px;
    }

    .sidebar {
        display: block;
    }
}
```

### Condition Helpers

All dimension helpers accept any `CharSequence` value (e.g., `px()`, `rem()`, `em()`):

| Method | Output |
|--------|--------|
| `minWidth(px(768))` | `(min-width: 768px)` |
| `maxWidth(px(1200))` | `(max-width: 1200px)` |
| `minHeight(vh(100))` | `(min-height: 100vh)` |
| `maxHeight(px(600))` | `(max-height: 600px)` |
| `prefersColorScheme(DARK)` | `(prefers-color-scheme: dark)` |
| `prefersColorScheme(LIGHT)` | `(prefers-color-scheme: light)` |
| `prefersReducedMotion()` | `(prefers-reduced-motion: reduce)` |
| `orientation(PORTRAIT)` | `(orientation: portrait)` |
| `orientation(LANDSCAPE)` | `(orientation: landscape)` |
| `screen()` | `screen` |
| `print()` | `print` |

### Combining Conditions

Chain `.and()` and `.or()` for compound conditions:

```java
// Range: tablet to desktop
var mid = media(minWidth(px(768)).and(maxWidth(px(1200))),
    container.____(padding(rem(1)))
);
// → @media (min-width: 768px) and (max-width: 1200px) { ... }

// Media type + feature
var screenTablet = media(screen().and(minWidth(px(768))),
    sidebar.____(display(FLEX))
);
// → @media screen and (min-width: 768px) { ... }

// OR (comma-separated)
var screenOrPrint = media(screen().or(print()),
    body.____(font_family("Georgia, serif"))
);
// → @media screen, print { ... }

// NOT
var notPrint = media(not(print()),
    nav.____(display(FLEX))
);
// → @media not print { ... }
```

### Dark Mode

```java
import static com.myapp.AppVars.*;

var darkMode = media(prefersColorScheme(DARK),
    $root.____(
        primary_color.def("#90caf9"),
        bg_color.def("#121212")
    ),
    body.____(background_color("#121212"), color("#e0e0e0"))
);
```

**CSS Output:**
```css
@media (prefers-color-scheme: dark) {
    :root {
        --primary-color: #90caf9;
        --bg-color: #121212;
    }

    body {
        background-color: #121212;
        color: #e0e0e0;
    }
}
```

### Escape Hatches

For anything not covered by the DSL:

```java
// Raw condition string
media(condition("(hover: hover) and (pointer: fine)"),
    btn.____(padding(px(4), px(8)))
);

// Generic feature builder
media(feature("resolution", "2dppx"),
    img.____(width(percent(50)))
);
```

### Using with `rulesFrom()`

Media queries integrate with `rulesFrom()` like any other `CssRuleFrag`:

```java
return rulesFrom(
    comment("Base styles"),
    body.____(margin(ZERO), font_family("system-ui, sans-serif")),
    container.____(max_width(px(1200)), margin(ZERO, AUTO)),

    comment("Responsive"),
    media(maxWidth(px(767)),
        container.____(padding(px(16))),
        sidebar.____(display(NONE))
    ),
    media(minWidth(px(768)),
        container.____(padding(px(32))),
        sidebar.____(display(BLOCK))
    ),

    comment("Accessibility"),
    media(prefersReducedMotion(),
        $all.____(transition("none"), animation("none"))
    )
);
```

Media queries also accept `CssRules` blocks directly, which are flattened:

```java
CssRules headerRules = rules(
    header.____(padding(rem(1))),
    nav.____(display(FLEX))
);

var tablet = media(minWidth(px(768)), headerRules);
```

### Nesting Prevention

Nesting `@media` inside `@media` is prevented at runtime:

```java
var inner = media(maxWidth(px(600)), rule(".small", font_size(px(12))));

// This throws IllegalArgumentException at runtime:
media(minWidth(px(768)), inner);  // ERROR: "Media queries cannot be nested"
```

### Qualified Access (`MQ.`)

If you need to disambiguate from other imports, use qualified access:

```java
// Instead of wildcard import:
import luvs.MQ;

var tablet = MQ.media(MQ.minWidth(px(768)),
    container.____(padding(rem(2)))
);
```

## Font Faces (`FontFace`)

Define custom fonts with `@font-face`:

```java
import static luvs.FontFace.*;
import static luvs.FontDisplay.*;

var customFont = fontFace(
    fontFamily("MyCustomFont"),
    src(
        url("fonts/myfont.woff2"),
        format("woff2")
    ),
    fontWeight("400"),
    fontStyle("normal"),
    fontDisplay(SWAP)
);
```

**CSS Output:**
```css
@font-face {
    font-family: "MyCustomFont";
    src: url("fonts/myfont.woff2"), format("woff2");
    font-weight: 400;
    font-style: normal;
    font-display: swap;
}
```

### Multiple Sources with Fallbacks

```java
fontFace(
    fontFamily("Inter"),
    src(
        local("Inter"),
        urlFormat("fonts/inter.woff2", "woff2"),
        urlFormat("fonts/inter.woff", "woff"),
        url("fonts/inter.ttf")
    ),
    fontWeight(300, 900), // Variable font weight range
    unicodeRange("U+0000-00FF")
);
```

### Font Descriptors

- `fontFamily(name)` - Font family name (required, auto-quoted)
- `src(sources...)` - Font sources (required)
- `fontWeight(weight)` or `fontWeight(min, max)` - Weight or range
- `fontStyle(style)` - Font style (normal, italic, oblique)
- `fontDisplay(display)` - Loading behavior (SWAP, BLOCK, FALLBACK, OPTIONAL, AUTO)
- `unicodeRange(range)` - Unicode character range
- `fontStretch(value)` - Font stretch property
- `fontVariant(value)` - Font variant property

### Helper Functions

- `url(path)` - Font URL
- `format(type)` - Format hint ("woff2", "woff", "truetype", "opentype", "svg")
- `local(name)` - Local font name
- `urlFormat(path, format)` - Combined URL and format

## Feature Queries (`Supports`)

Progressive enhancement with `@supports`:

```java
import static luvs.Supports.*;

// Basic feature check
supports(
    property("display", "grid"),
    container.____(display(GRID))
);
```

**CSS Output:**
```css
@supports (display: grid) {
    .container {
        display: grid;
    }
}
```

### Logical Operators

```java
// AND operator (instance method)
supports(
    property("display", "flex").and(property("gap", "1rem")),
    div.____( display(FLEX), gap(rem(1)) )
);
// → @supports (display: flex) and (gap: 1rem) { ... }

// OR operator
supports(
    property("backdrop-filter", "blur(10px)")
        .or(property("-webkit-backdrop-filter", "blur(10px)")),
    modal.____( backdrop_filter("blur(10px)") )
);
// → @supports (backdrop-filter: blur(10px)) or (-webkit-backdrop-filter: blur(10px)) { ... }

// NOT operator (static method)
supports(
    SupportsCondition.not(property("display", "grid")),
    div.____( display(FLEX) )
);
// → @supports not (display: grid) { ... }
```

### Condition Types

- `property(name, value)` - Property-value check
- `selector(selector)` - Selector support check
- `condition(raw)` - Raw condition string (escape hatch)

## Container Queries (`ContainerQuery`)

Container-based responsive design:

```java
import static luvs.ContainerQuery.*;

// Define a container
div.____(
    P.container_type("inline-size"),  // Make this a query container
    P.container_name("card")          // Optional name
);

// Anonymous container query
container(
    minWidth(px(400)),
    card.____(
        display(GRID),
        grid_template_columns("1fr 1fr")
    )
);
```

**CSS Output:**
```css
div {
    container-type: inline-size;
    container-name: card;
}

@container (min-width: 400px) {
    .card {
        display: grid;
        grid-template-columns: 1fr 1fr;
    }
}
```

### Named Containers

```java
// Query a specific named container
container(
    "sidebar",  // Container name
    minWidth(px(300)),
    nav.____(display(BLOCK))
);
// → @container sidebar (min-width: 300px) { ... }
```

### Container Conditions

Size conditions:
- `minWidth(value)`, `maxWidth(value)`
- `minHeight(value)`, `maxHeight(value)`
- `minInlineSize(value)`, `maxInlineSize(value)`
- `minBlockSize(value)`, `maxBlockSize(value)`

Other conditions:
- `aspectRatio(ratio)` - e.g., `aspectRatio("16/9")`
- `orientation(value)` - "portrait" or "landscape"
- `condition(raw)` - Raw condition string

### Logical Operators

```java
// AND operator (instance method)
container(
    minWidth(px(400)).and(maxWidth(px(800))),
    card.____(padding(rem(2)))
);

// OR operator
container(
    minWidth(px(600)).or(orientation("landscape")),
    grid.____(grid_template_columns("repeat(3, 1fr)"))
);

// NOT operator (static method)
container(
    ContainerCondition.not(minWidth(px(400))),
    card.____(flex_direction(COLUMN))
);
```

### Container Properties

Set these on elements to make them query containers:

```java
div.____(
    P.container_type("inline-size"),  // size | inline-size | normal
    P.container_name("myContainer"),  // Optional name
    P.container("inline-size / myContainer")  // Shorthand
);
```

## Cascade Layers (`Layer`)

Control CSS specificity with `@layer`:

```java
import static luvs.Layer.*;

// Layer order declaration (define priority)
layerOrder("reset", "base", "components", "utilities");
// → @layer reset, base, components, utilities;

// Anonymous layer
layer(
    $all.____(box_sizing(BORDER_BOX))
);
// → @layer { * { box-sizing: border-box; } }

// Named layer
layer("reset",
    body.____(margin(ZERO), padding(ZERO))
);
// → @layer reset { body { margin: 0; padding: 0; } }

// Nested layers
layer("components.button",
    btn.____(padding(rem(1)))
);
// → @layer components.button { .btn { padding: 1rem; } }
```

**Complete Example:**
```java
rulesFrom(
    // 1. Declare layer order (lowest to highest priority)
    layerOrder("reset", "base", "theme", "components", "utilities"),

    // 2. Define layers
    layer("reset",
        $all.____(margin_block(ZERO), padding_inline(ZERO))
    ),

    layer("base",
        body.____(font_family("system-ui"), line_height("1.5"))
    ),

    layer("components",
        btn.____(padding(rem(0.5), rem(1)), border_radius(px(4)))
    ),

    // 3. Unlayered styles have highest priority
    div.important().____(color(RED))  // Outside any layer = highest priority
);
```

**CSS Output:**
```css
@layer reset, base, theme, components, utilities;

@layer reset {
    * {
        margin-block: 0;
        padding-inline: 0;
    }
}

@layer base {
    body {
        font-family: system-ui;
        line-height: 1.5;
    }
}

@layer components {
    .btn {
        padding: 0.5rem 1rem;
        border-radius: 4px;
    }
}

.important {
    color: red;
}
```

## New CSS Properties

### Backdrop Filter & Clip Path

```java
// Backdrop filter (glassmorphism)
modal.____(
    backdrop_filter("blur(10px) saturate(180%)")
); // .modal { backdrop-filter: blur(10px) saturate(180%); }

// Clip path (complex shapes)
polygon.____(
    clip_path("polygon(50% 0%, 100% 50%, 50% 100%, 0% 50%)")
); // .polygon { clip-path: polygon(50% 0%, 100% 50%, 50% 100%, 0% 50%); }
```

### Scroll Properties

```java
// Smooth scrolling
body.____(
    scroll_behavior("smooth")
);

// Scroll snap
container.____(
    scroll_snap_type("y mandatory"),
    scroll_snap_align("start"),
    scroll_snap_stop("always"),
    scroll_margin(px(20)),
    scroll_padding(px(10), px(20)),
    overscroll_behavior("contain")
);
```

### Performance Hints

```java
animated_element.____(
    will_change("transform", "opacity"),  // Hint browser about changes
    contain("layout style"),              // Containment for performance
    content_visibility("auto")            // Render only visible content
);
```

### Interaction Properties

```java
text_element.____(
    user_select("none"),       // Prevent text selection
    pointer_events("none"),    // Disable pointer interactions
    touch_action("pan-y")      // Touch gesture handling
);
```

### Aspect Ratio

```java
// Video container with 16:9 ratio
video_wrapper.____(
    aspect_ratio(16, 9)  // Helper method
);

// Image with square ratio
avatar.____(
    aspect_ratio("1 / 1")  // String format
);
```

### Logical Properties

Modern, internationalization-friendly properties:

```java
article.____(
    // Margins (adapt to writing direction)
    margin_inline(px(20)),         // Left/right in LTR, top/bottom in vertical
    margin_inline_start(px(10)),   // Left in LTR, right in RTL
    margin_block(px(30)),          // Top/bottom in horizontal writing

    // Padding
    padding_inline(px(20)),
    padding_block(px(10)),

    // Borders
    border_inline("1px solid black"),
    border_block_start_color("red"),

    // Positioning
    inset_inline_start(px(0)),     // left in LTR, right in RTL
    inset_block_end(px(10)),       // bottom in horizontal

    // Size
    inline_size(percent(100)),     // width in horizontal
    block_size("auto"),            // height in horizontal
    max_inline_size(px(600))
);
```

### Text Decoration

```java
link.____(
    text_decoration_color("blue"),
    text_decoration_style("wavy"),
    text_decoration_thickness(px(2)),
    text_underline_offset(px(3))
);
```

### Grid Named Areas

```java
layout.____(
    grid_template_areas(
        "\"header header header\"\n" +
        "\"sidebar main main\"\n" +
        "\"footer footer footer\""
    )
);

header_el.____(
    grid_area("header")
);
```

## Advanced CSS Features

### Color Functions

**color-mix()** - Mix colors in various color spaces:

```java
import static luvs.V.*;

div.____(
    color(colorMix("in srgb", "red", "50%", "blue")),
    // → color: color-mix(in srgb, red 50%, blue);

    background(colorMix("in oklch", "blue", "blue")),  // 50/50 default
    // → background: color-mix(in oklch, blue, blue);

    border_color(colorMix("in hsl", "red", "30%", "yellow", "70%"))
    // → border-color: color-mix(in hsl, red 30%, yellow 70%);
);
```

Color spaces: `"in srgb"`, `"in oklch"`, `"in hsl"`, `"in hwb"`, `"in lab"`, `"in oklab"`, `"in lch"`

**Relative Color Syntax** - Derive new colors from existing:

```java
div.____(
    // Derive from RGB
    color(rgbFrom("var(--primary)", "r g b / 0.5")),
    // → color: rgb(from var(--primary) r g b / 0.5);

    // Derive from HSL with modifications
    background(hslFrom("blue", "h s calc(l * 1.2)")),
    // → background: hsl(from blue h s calc(l * 1.2));

    // Modern OKLCH color space
    border_color(oklchFrom("var(--accent)", "l c h / 0.8")),
    // → border-color: oklch(from var(--accent) l c h / 0.8);

    // OKLAB
    box_shadow(oklabFrom("#ff0000", "l a b / 0.6"))
    // → box-shadow: oklab(from #ff0000 l a b / 0.6);
);
```

### Text Wrapping

```java
h1.____(
    P.text_wrap("balance")  // Balance line lengths in headings
);

p.____(
    P.text_wrap("pretty")   // Better line breaks for paragraphs
);
```

### Subgrid

```java
parent.____(
    display(GRID),
    grid_template_columns("repeat(3, 1fr)")
);

// Child inherits parent's grid tracks
child.____(
    display(GRID),
    grid_template_columns(V.SUBGRID),  // Aligns with parent
    grid_template_rows(V.SUBGRID)
);
```

## Property Composition

Compose multiple properties into reusable fragments with `props()`. Useful for vendor prefixes, common patterns, design system utilities.

```java
// Define helper using props() - clean and concise
public static CssPropertyFrags webkitLineClamp(int lines) {
    return props(
        display("-webkit-box"),
        prop("-webkit-line-clamp", String.valueOf(lines)),
        prop("-webkit-box-orient", "vertical"),
        overflow(HIDDEN)
    );
}

// Use alongside regular properties (flattens transparently)
card_description.____(
    color("#666"),
    webkitLineClamp(3),  // Expands to 4 properties
    margin(rem(1))
)
```

**Common patterns:**

```java
// Flexbox centering
public static CssPropertyFrags flexCenter() {
    return props(display(FLEX), justify_content(JC_CENTER), align_items(AI_CENTER));
}

// Absolute overlay (covers entire parent)
public static CssPropertyFrags absoluteCover() {
    return props(position(ABSOLUTE), top(ZERO), right(ZERO), bottom(ZERO), left(ZERO));
}

// Vendor-prefixed grid
public static CssPropertyFrags vendorGrid(String cols) {
    return props(
        display("-ms-grid"),
        prop("-ms-grid-columns", cols),
        display(GRID),
        grid_template_columns(cols)
    );
}

// Fragments can nest
public static CssPropertyFrags cardBase() {
    return props(background(WHITE), flexCenter(), padding(rem(2)));  // flexCenter() is another fragment
}
```

## Custom CSS Functions (Pro-Tip)

You can create reusable CSS patterns as Java methods. They work with **static values only** (not runtime), but are great for maintaining consistency:

```java
// Custom button variant generator
public static CssRule buttonVariant(CssClass btnClass, String color, String hoverColor) {
    return btnClass.____(
        background(color),
        color(WHITE),
        border(NONE),
        padding(rem(0.75), rem(1.5)),
        border_radius(px(6)),
        cursor(POINTER),
        transition("background 0.2s"),

        P.hover().____( background(hoverColor) )
    );
}

// Usage
rulesFrom(
    buttonVariant(btn_primary, "#007bff", "#0056b3"),
    buttonVariant(btn_danger, "#dc3545", "#bd2130")
);
```

**CSS Output:**
```css
.btn_primary {
    background: #007bff;
    color: white;
    border: none;
    padding: 0.75rem 1.5rem;
    border-radius: 6px;
    cursor: pointer;
    transition: background 0.2s;
}

.btn_primary:hover {
    background: #0056b3;
}

.btn_danger {
    background: #dc3545;
    /* ... same structure ... */
}
```

**More Examples:**

```java
// Responsive spacing utility
public static CssRule spacing(CssClass cls, int sm, int md, int lg) {
    return rulesFrom(
        cls.____(padding(px(sm))),

        media(minWidth(px(768)),
            cls.____(padding(px(md)))
        ),

        media(minWidth(px(1024)),
            cls.____(padding(px(lg)))
        )
    ).getRules()[0]; // Extract as single rule
}

// Card elevation levels
public static CssProperty elevation(int level) {
    return box_shadow(
        ZERO,
        px(level * 2),
        px(level * 4),
        rgba(0, 0, 0, 0.1 * level)
    );
}

// Usage
card_1.____(elevation(1));  // Subtle shadow
card_2.____(elevation(3));  // Medium shadow
card_3.____(elevation(5));  // Strong shadow
```

This approach gives you:
- **Reusability** - Define once, use everywhere
- **Type safety** - Compiler catches errors
- **Refactorability** - IDE rename works across all usages
- **Composability** - Combine functions to build complex patterns

## Organization Patterns

LuvS gives you freedom to organize CSS however you want. Here are common patterns.

### Pattern 1: Enum with Companion Style Method

Define class names as an enum, CSS rules in a static method in the same file. This is the **recommended pattern** for most cases - it co-locates names and rules while keeping them separate.

```java
public enum AppStyles implements CssClass {
    container, card, header, btn;

    public static CssRules appRules() {
        return rules(

            container.____( // .container
                width(percent(80)),
                margin(ZERO, AUTO)
            ),

            card.____( // .card
                background(WHITE),
                border_radius(px(8)),
                padding(rem(1.5))
            ),

            header.____( // .header
                background(PRIMARY),
                color(WHITE)
            ),

            btn.____( // .btn
                display(INLINE_BLOCK),
                padding(rem(0.5), rem(1))
            )
        );
    }
}
```

**Note:** With IDE support like rainbow braces (each `(` `)` pair gets a different color), this code is actually very readable. Even without it, the structure is clear and easy to scan.

This pattern is similar to how Tailwind co-locates styles with components, but with real abstractions. You can ctrl+click from usage to definition, rename with IDE refactoring, and the compiler catches misspelled class names.

### Pattern 2: Dedicated Style Files

For larger apps, organize styles into separate files by concern (similar to traditional CSS files, but with all the IDE benefits):

```java
// Colors.java - central color palette
public final class Colors {
    public static final String
        PRIMARY = "#667eea",
        PRIMARY_DARK = "#5568d3",
        TEXT_DARK = "#333",
        BORDER_LIGHT = "#ddd",
        BG_LIGHT = "#f5f5f5"
    ;
    // ... more colors
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

Build utility-first CSS like Tailwind/UnoCSS but with Java's compile-time safety, IDE navigation (ctrl+click, find-all-references), parameterization, and data-driven generation.

**Approach 1: Dynamic utilities with auto-registry (`Uc` class)**
Define a `Uc` class (copy once to your project) that implements `CharSequence` for use in `class_()` and tracks instances in a static registry. Factory functions create utilities on-demand:

```java
public static Uc $p(int n) { return uc("p_" + n, padding(rem(SCALE[n]))); }  // $p(4) → .p_4 { padding: 1rem; }
public static Uc $flex()   { return uc("flex", display(FLEX)); }
public static Uc $shadow(int level) { return switch(level) { case 1 -> uc("shadow_sm", box_shadow(...)); ... }; }
```

**Usage:** `div(class_($p(4), $flex(), $rounded(8), $shadow(2)), ...)` → auto-generates CSS for only the utilities used. No purge step needed.

**Approach 2: Pre-declared enum (full Tailwind pattern)**
Define all utilities upfront as enum constants with a switch mapping:

```java
public enum Tw implements CssClass {
    flex, grid, hidden, items_center, justify_between, text_center, font_bold, rounded_lg, shadow_md;
    
    CssProperty[] props() {
        return switch (this) {
            case flex -> a(display(FLEX));
            case items_center -> a(align_items(AI_CENTER));
            case rounded_lg -> a(border_radius(px(8)));
            // ... ~50-100 utilities total
        };
    }
    
    public static CssRules allRules() {
        return rules(Arrays.stream(values()).map(tw -> tw.____(tw.props())).toArray(CssRule[]::new));
    }
}
```

**Usage:** `div(class_(flex, items_center, justify_between, rounded_lg), ...)` — identical to Tailwind but type-safe (compile error on typos, IDE refactoring works).

**Shortcuts (UnoCSS-style):** Combine utilities into named components:

```java
enum Shortcut implements CssClass {
    btn, card;
    public static CssRules allRules() {
        return rules(
            btn.____(display(INLINE_BLOCK), padding(rem(0.5), rem(1)), border_radius(px(8)), ...),
            card.____(background(WHITE), padding(rem(1.5)), box_shadow(...))
        );
    }
}
```

**Rule generators:** Create multi-rule patterns (base + hover + disabled):

```java
static CssRules buttonVariant(CssClass cls, String bg, String hoverBg) {
    return rules(
        cls.____(background(bg), padding(...), cursor(POINTER), transition(BACKGROUND, s(0.3))),
        cls.hover().____(background(hoverBg)),
        cls.disabled().____(opacity(0.5), cursor(NOT_ALLOWED))
    );
}
```

**vs Tailwind/UnoCSS:** Same workflow (small composable classes, co-located with HTML) but adds compile-time checking, IDE navigation, parameterized utilities (`$p(n)` vs fixed `p-4`), data-driven generation (`forEachRule`), no purge step, zero runtime cost.

Putting it all together:

```java
import static luvml.E.*;
import static luvml.C.*;
import static luvml.T.text;
import static luvs.P.*;
import static luvs.V.*;
import static luvs.HtmlTag.*;
import static luvs.CssRules.*;
import static luvs.CssProp.*;
import luvml.o.HtmlRenderer;

// Static import inner enums for cleaner syntax (no Cls. or Theme. prefix needed)
import static TodoApp.Cls.*;
import static TodoApp.Theme.*;

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
            $root.____( // :root
                bg_color.def("#f5f5f5"),
                text_color.def("#333"),
                accent.def("#4a90d9")
            ),

            // Reset
            $all.____( // *
                margin(ZERO), padding(ZERO), box_sizing(BORDER_BOX)
            ),

            body.____( // body
                font_family("system-ui", "sans-serif"),
                background_color(bg_color.ref()),
                color(text_color.ref())
            ),

            // Layout
            app.____( // .app
                max_width(px(600)),
                margin(rem(2), AUTO),
                padding(ZERO, rem(1))
            ),

            // List
            todo_list.____( // .todo_list
                display(FLEX),
                flex_direction(COLUMN),
                gap(rem(0.5))
            ),

            // Items
            todo_item.____( // .todo_item
                display(FLEX),
                align_items(AI_CENTER),
                padding(rem(1)),
                background(WHITE),
                border_radius(px(8)),
                box_shadow(ZERO, px(1), px(3), rgba(0, 0, 0, 0.1)),
                transition(TRANSFORM, s(0.2), BOX_SHADOW, s(0.2))
            ),

            todo_item.hover().____( // .todo_item:hover
                transform(translateY(px(-2))),
                box_shadow(ZERO, px(4), px(8), rgba(0, 0, 0, 0.15))
            ),

            // Completed state
            completed.____( // .completed
                P.opacity(0.6),
                text_decoration("line-through")
            ),

            // Button
            add_btn.____( // .add_btn
                background(accent.ref()),
                color(WHITE),
                border("none"),
                padding(rem(0.75), rem(1.5)),
                border_radius(px(8)),
                cursor(POINTER),
                font_size(rem(1)),
                transition(BACKGROUND, s(0.3))
            ),

            add_btn.hover().____( // .add_btn:hover
                P.opacity(0.9)
            ),

            add_btn.disabled().____( // .add_btn:disabled
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
                div(class_(app),
                    h1("My Todos"),
                    div(class_(todo_list),
                        div(class_(todo_item), text("Learn LuvS")),
                        div(class_(todo_item, completed), text("Set up project"))
                    ),
                    luvml.E.button(class_(add_btn), text("Add Todo"))
                )
            )
        );

        System.out.println("<!DOCTYPE html>");
        System.out.println(HtmlRenderer.asFormattedString(page));
    }
}
```

## Complete Example

Full application demonstrating luvs + luvml integration:

```java
import static luvml.E.*; import static luvml.C.*; import static luvml.T.text;
import static luvs.P.*; import static luvs.V.*; import static luvs.HtmlTag.*;
import static luvs.CssRules.*; import static luvs.CssProp.*;
import static TodoApp.Cls.*; import static TodoApp.Theme.*;  // Static import inner enums

public class TodoApp {
    enum Cls implements CssClass { app, todo_list, todo_item, completed, add_btn; }
    enum Theme implements CssVariable { bg_color, text_color, accent; }

    static CssRules styles() {
        return rules(
            $root.____(bg_color.def("#f5f5f5"), text_color.def("#333"), accent.def("#4a90d9")),
            $all.____(margin(ZERO), padding(ZERO), box_sizing(BORDER_BOX)),
            body.____(font_family("system-ui", "sans-serif"), background_color(bg_color.ref()), color(text_color.ref())),
            app.____(max_width(px(600)), margin(rem(2), AUTO), padding(ZERO, rem(1))),
            todo_list.____(display(FLEX), flex_direction(COLUMN), gap(rem(0.5))),
            todo_item.____(
                display(FLEX), align_items(AI_CENTER), padding(rem(1)),
                background(WHITE), border_radius(px(8)),
                box_shadow(ZERO, px(1), px(3), rgba(0, 0, 0, 0.1)),
                transition(TRANSFORM, s(0.2), BOX_SHADOW, s(0.2))
            ),
            todo_item.hover().____(transform(translateY(px(-2))), box_shadow(ZERO, px(4), px(8), rgba(0, 0, 0, 0.15))),
            completed.____(P.opacity(0.6), text_decoration("line-through")),
            add_btn.____(
                background(accent.ref()), color(WHITE), border("none"),
                padding(rem(0.75), rem(1.5)), border_radius(px(8)),
                cursor(POINTER), font_size(rem(1)), transition(BACKGROUND, s(0.3))
            ),
            add_btn.hover().____(P.opacity(0.9)),
            add_btn.disabled().____(P.opacity(0.5), cursor(NOT_ALLOWED))
        );
    }

    public static void main(String[] args) {
        var page = html(
            head(title("Todo App"), style(styles())),
            body(div(class_(app),
                h1("My Todos"),
                div(class_(todo_list),
                    div(class_(todo_item), text("Learn LuvS")),
                    div(class_(todo_item, completed), text("Set up project"))
                ),
                luvml.E.button(class_(add_btn), text("Add Todo"))
            ))
        );
        System.out.println("<!DOCTYPE html>\n" + HtmlRenderer.asFormattedString(page));
    }
}
```

**Key points:** CSS variables in `:root`, universal reset with `$all`, enum-based class names (compile-time safe, IDE navigation), pseudo-classes (`.hover()`), transitions, box-shadow, flexbox layout, static imports for clean syntax.

## Quick Reference

**Type-safe property methods:** `display(FLEX/GRID/BLOCK/NONE)`, `position(ABSOLUTE/RELATIVE/FIXED/STICKY)`, `font_weight(BOLD/NORMAL/W100-W900)`, `text_align(LEFT/RIGHT/CENTER)`, `flex_direction(ROW/COLUMN)`, `justify_content(SPACE_BETWEEN/JC_CENTER)`, `align_items(AI_CENTER/STRETCH)`, `overflow(HIDDEN/SCROLL/OV_AUTO)`, `cursor(POINTER/GRAB/NOT_ALLOWED)`, `border_collapse(BC_COLLAPSE)`, `box_sizing(BORDER_BOX)`, `object_fit(OF_COVER/OF_CONTAIN)`, `color(RED/BLUE/WHITE/BLACK/...)`, `transition(TimingFunction: EASE/LINEAR/EASE_IN_OUT)`. All accept `CharSequence` fallback.

**Selector chaining:** From `HtmlTag` or `CssClass`: `hover()`, `focus()`, `active()`, `disabled()`, `checked()`, `firstChild()`, `nthChild()`, `not()`, `notHover()`, `before()`, `after()`, `placeholder()`, `firstLine()`, `child()`, `descendant()`, `adjacent()`, `sibling()`, `and()`, `withAttr()`, `__()`, `__type()`, `__data()`, `attrStartsWith/Ends/Substring/Contains/DashMatch()`. All return `Selector` supporting further chaining.

**Media queries** (`MQ.*`): `media(condition, rules...)`, `minWidth(val)`, `maxWidth(val)`, `minHeight(val)`, `maxHeight(val)`, `prefersColorScheme(DARK/LIGHT)`, `prefersReducedMotion()`, `orientation(PORTRAIT/LANDSCAPE)`, `screen()`, `print()`, `not(cond)`, `cond.and(other)`, `cond.or(other)`, `condition("raw")`, `feature(name, val)`.

## Real-World Notes

Nowadays there is a trend of css into code like Tailwind and Uno, going along same trends luvs pushes limits in the JAva world.

**Escape hatch usage:** Vendor-prefixed or newer properties not yet in the DSL use `prop("-webkit-line-clamp", "3")`, `prop("-webkit-box-orient", "vertical")`. This is expected - CSS has hundreds of properties. `prop()` means you're never blocked, you just lose type safety for that property. PLEASE USE THIS RARELY expect 99% of what you need is covered.

**Trade-off:** CSS changes need recompile (~few seconds with hot-reload tools like spring-boot-devtools). Main ergonomic cost vs editing `.css` and refreshing browser for projects without hot-reload.
