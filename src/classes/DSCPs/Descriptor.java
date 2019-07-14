package classes.DSCPs;

import org.objectweb.asm.Type;

public abstract class Descriptor {
    private Type type;
    private String name;
    private boolean isConst;

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isConst() {
        return isConst;
    }

    public Descriptor(Type type, String name, boolean isConst) {
        this.type = type;
        this.name = name;
        this.isConst = isConst;
    }
}
