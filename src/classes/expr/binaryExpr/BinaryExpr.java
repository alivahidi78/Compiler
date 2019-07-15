package classes.expr.binaryExpr;

import classes.expr.Expr;
import org.objectweb.asm.Type;

public abstract class BinaryExpr extends Expr {
    protected Expr exp1, exp2 ;
    @Override
    public Type getType() {
        if (type != null) return type;
        if (exp1.getType() != exp2.getType())
            throw new IllegalArgumentException("Two Operands must be of the same type");
        return type = exp1.getType();
    }

    public BinaryExpr setBinaryExpr(Expr exp1, Expr exp2){
        this.exp1=exp1;
        this.exp2=exp2;
        return this;
    }
}
