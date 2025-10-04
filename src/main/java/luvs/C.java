package luvs;

import java.util.Set;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import luvml.HtmlAttribute;

public class C {
    public static HtmlAttribute classAttr(CharSequence ... cs) {
        return class_(cs);
    }
    
    public static HtmlAttribute class_(CharSequence ... cs) {
        if(cs==null)return strAttr("class", "");
        return spaceSeparatedAttrVals("class", 
            Stream.of(cs).map(__->__.toString()).toArray(String[]::new)
        );
    }
    
    public static HtmlAttribute spaceSeparatedAttrVals(String attrName, String ... vals){
        var bidsStr = vals==null?"":Stream.of(vals)
                .filter(__->!__.isBlank())
                .map(String::trim)
                .collect(joining(" "));
        // depulicate bids
        bidsStr = Set.of(bidsStr.split(" ")).stream().collect(joining(" "));
        return strAttr(attrName,bidsStr);
    }
    
    public static HtmlAttribute strAttr(String attr, String val){
        return new HtmlAttribute(attr, val);
    }
    
}
