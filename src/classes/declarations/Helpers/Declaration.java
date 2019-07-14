package classes.declarations.Helpers;

import classes.Operation;
import classes.expr.Expr;
import org.objectweb.asm.Type;

public abstract class Declaration extends Operation {
    protected boolean isConst;
    protected Type type;
    protected String name;
    protected Expr expr;

    public Declaration(boolean isConst, Type type, String name, Expr expr) {
        this.isConst = isConst;
        this.type = type;
        this.name = name;
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}
