# luvs

**Type-safe Java CSS generation, with compile-time checking, IDE navigation (ctrl+click, find-all-references, rename-symbol), and full composability.**

```xml
<dependency>
    <groupId>io.github.luvml</groupId>
    <artifactId>luvs</artifactId>
    <version>2.0</version>
</dependency>
```

**Requires:** JDK 21+ · depends on [luvx-char_sequence](https://github.com/luvml/luvx-char_sequence)

## Quick Start

```java
import static luvs.P.*;
import static luvs.V.*;
import static luvs.HtmlTag.*;
import static luvs.CssRules.*;

enum Styles implements CssClass { container, card, btn; }

CssRules styles = rules(
    container.____(max_width(px(600)), margin(rem(2), AUTO)),
    card.____(
        background(WHITE),
        border_radius(px(8)),
        box_shadow(ZERO, px(2), px(8), rgba(0, 0, 0, 0.1))
    ),
    card.hover().____(transform(translateY(px(-2))))
);
```

## Why luvs?

CSS class names are enum constants, not strings:

```java
enum Styles implements CssClass { container, card, btn; }

// Rename with the IDE -> every usage updates
// Misspell -> compiler error
// Find all references -> works
div(class_(container, card), text("Type-safe"))
```

## Coverage

**394 CSS properties** — all standard properties, modern features (2020-2024), logical properties (inline/block), SVG (20+ properties), masking, print (`@page`), vendor-specific extensions. Verified against W3Schools, CSS-Tricks and MDN property lists.

**Modern at-rules** — `@font-face`, `@supports`, `@container`, `@layer`, `@page`, `@media` with full nesting and logical operators (`and`/`or`/`not`).

**Full feature set** — transforms, filters, gradients, calc expressions, color functions (`rgb`/`hsl`/`color-mix`/relative color syntax), transitions, animations, keyframes, CSS variables, container queries, cascade layers, pseudo-classes/-elements, attribute selectors, all combinators.

## Core Concepts

Three main entry points: **`P`** (properties — `color()`, `margin()`, ...), **`V`** (values — `px(10)`, `rem(1.5)`, enum constants `FLEX`, `BOLD`, ...), **`Selector`** (a fluent builder — `.hover()`, `.and(...)`, `.child(...)`, `.descendant(...)`). Supporting types: `CssClass`, `HtmlTag`, `CssRule`, `CssRules`, `CssVariable`, `Keyframes`, `MQ`, `FontFace`, `Supports`, `ContainerQuery`, `Layer`.

## Documentation

The full tutorial — every property group, selector composition, media/container queries, and how to look up an exact signature without guessing — lives in [`luvs_tutorial.md`](luvs_tutorial.md).

## Related Projects

- **[luvml](https://github.com/luvml/luvml)** — the companion type-safe HTML DSL luvs is designed to sit alongside
- **[luvue](https://github.com/luvml/luvue)** — type-safe Vue.js template directives

## License

Apache License 2.0 — see [LICENSE](LICENSE).
