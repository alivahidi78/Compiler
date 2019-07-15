package classes.expr.binaryExpr.conditional;

import classes.expr.Expr;
import classes.expr.consts.IntCExpr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class NotEq extends ConditionalExpr {
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }

    public void setBinaryExpr(Expr first, Expr second) {
        //TODO
    }
}
