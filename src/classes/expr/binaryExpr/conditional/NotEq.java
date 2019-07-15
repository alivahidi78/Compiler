package classes.expr.binaryExpr.conditional;

import classes.expr.Expr;
import classes.expr.consts.IntCExpr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.IF_ICMPEQ;

public class NotEq extends ConditionalExpr {
    public NotEq(){

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        compare(IFEQ,IF_ICMPEQ, mv, cv);
    }
}
