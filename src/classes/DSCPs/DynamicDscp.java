package classes.DSCPs;

import org.objectweb.asm.Type;

public class DynamicDscp extends Descriptor {
    private int index;

    public DynamicDscp(boolean isConst, Type type, String name, int index) {
        super(isConst, type, name);
        this.index = index;
    }


    public int getIndex() {
        return index;
    }
}
