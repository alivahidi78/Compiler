package classes.declarations;

import classes.Part;
import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class VarDclPart {
    private String name;
    private Expr expr;

    public String getName() {
        return name;
    }

    public Expr getExpr() {
        return expr;
    }

    public VarDclPart(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }
}
