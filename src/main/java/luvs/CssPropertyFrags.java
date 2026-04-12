package luvs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public non-sealed class CssPropertyFrags  implements CssPropertyFrag {

    private final ArrayList<CssPropertyFrag> frgs = new ArrayList<>();

    public CssPropertyFrags() {
    }
    
    public CssPropertyFrags ____(CssPropertyFrag ... frags){
        for (CssPropertyFrag frag : frags) {
            frgs.add(frag);
        }
        return this;
    }
    
    public CssPropertyFrags ____(Iterable<CssPropertyFrag> frags){
        for (CssPropertyFrag frag : frags) {
            frgs.add(frag);
        }
        return this;
    }
    
    public List<CssProperty> flattened(){
        var l = new ArrayList<CssProperty>();
        fillList(l, this);
        return Collections.unmodifiableList(l);
    }
    
    private static void fillList(List<CssProperty> toFill, CssPropertyFrag item){
        switch (item) {
            case CssProperty c -> toFill.add(c);
            case CssPropertyFrags f -> {
                for (CssPropertyFrag frg : f.frgs) {
                    fillList(toFill, frg);
                }
            }
        }
    }
    
    @Override
    public String delegatedCharSeqVal() {
        return flattened().stream()
            .map(CssProperty::delegatedCharSeqVal)
            .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return delegatedCharSeqVal();
    }

}
