package classes.DSCPs;

import org.objectweb.asm.Type;

public class GlobalDscp extends Descriptor {

    public GlobalDscp(Type type, String name, boolean isConst) {
        super(type, name, isConst);
    }
}
