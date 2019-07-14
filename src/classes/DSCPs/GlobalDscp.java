package classes.DSCPs;

import org.objectweb.asm.Type;

public class GlobalDscp extends Descriptor {

    public GlobalDscp(boolean isConst,Type type, String name) {
        super(isConst,type, name);
    }

}
