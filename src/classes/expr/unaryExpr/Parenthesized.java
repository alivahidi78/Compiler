package classes.expr.unaryExpr;

import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Parenthesized extends UnaryExpr {
    private Expr e;

    public Parenthesized(Expr e) {
        this.e = e;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        e.compile(mv, cv);
        type = e.getType();
    }
}
