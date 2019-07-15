package classes.DSCPs;

import classes.help.Functions;
import classes.help.SymbolTable;
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

    public Descriptor(boolean isConst, Type type, String name) {
        this.type = type;
        this.name = name;
        this.isConst = isConst;
    }

    public Descriptor(boolean isConst,Class<?> clazz,String name) {
        this.type = org.objectweb.asm.Type.getType(clazz);
        this.name = name;
        this.isConst= isConst;

    }

    public Descriptor(boolean isConst,String typeS,String name) {
        this.type = Functions.toType(typeS);
        this.name = name;
        this.isConst= isConst;
    }
}
