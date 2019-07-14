package classes.expr;

import classes.Part;
import org.objectweb.asm.Type;

public abstract class Expr extends Part {
    protected Type type;


    public Type getType() {
        if (type == null)
            throw new RuntimeException("Type is null");
        return type;
    }
}
