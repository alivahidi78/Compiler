package classes.expr.unaryExpr;

import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class UMinus extends UnaryExpr {
    Expr e;
    public UMinus(Expr e) {
        this.e = e;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        e.compile(mv,cv);
        type=e.getType();
        mv.visitInsn(e.getType().getOpcode(Opcodes.INEG));
    }
}
