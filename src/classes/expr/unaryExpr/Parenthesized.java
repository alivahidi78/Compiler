package classes.expr.unaryExpr;

import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Parenthesized extends UnaryExpr {
    public Parenthesized(Expr e) {
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
