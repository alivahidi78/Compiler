package classes.DSCPs;

import org.objectweb.asm.Type;

public class DynamicDscp extends Descriptor {
    private int index;

    public DynamicDscp(Type type, String name, boolean isConst) {
        super(type, name, isConst);
    }

    public int getIndex() {
        return index;
    }
}
