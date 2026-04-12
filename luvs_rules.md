
remove examples like this: 
public final class Colors {
    public static final String PRIMARY = "#667eea";
    public static final String PRIMARY_DARK = "#5568d3";
    public static final String TEXT_DARK = "#333";
    // ...
}

change to 
public final class Colors {
    public static final String 
		PRIMARY = "#667eea",
		PRIMARY_DARK = "#5568d3",
		TEXT_DARK = "#333"
	;
    // ...
}

and tell clearly this is not just shown as such in tutorial , but as a pro-tip to make things less verbose, and not abuse the library and make the library look bad with verbose coding style.

format this a bit neatly, more readably
```
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

as
```
public enum AppStyles implements CssClass {
    container, card, header, btn;

    public static CssRules appRules() {
        return rules(
            
			container.____(
				width(percent(80)), 
				margin(ZERO, AUTO)
			),
			
            card.____(
				background(WHITE), 
				border_radius(px(8)), 
				padding(rem(1.5))
			),
            
			header.____(
				background(PRIMARY), 
				color(WHITE)
			),
            
			btn.____(
				display(INLINE_BLOCK), 
				padding(rem(0.5), rem(1))
			)
        );
    }
}
```

also u need to mention, with rainbow braces plugin and other ide sensibility of coloring each `(` `)` with different color, the code actually looks fine ... and even without, it is quiet readable. 


please avoid these:
```
AppVars.primary_color.def("#90caf9"),
AppVars.bg_color.def("#121212")
```

show clear static import and do '
```
import static ...AppVars.*;

primary_color.def("#90caf9"),
bg_color.def("#121212")

```

also throughout the tutorial after a piece of code luvs Dsl is written immediately show it's css equivalent.