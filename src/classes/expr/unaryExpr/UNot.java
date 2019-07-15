package classes.expr.unaryExpr;

import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.ICONST_M1;
import static org.objectweb.asm.Opcodes.IXOR;

public class UNot extends UnaryExpr {
    private Expr e;

    public UNot(Expr e) {
        this.e = e;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if (type != Type.INT_TYPE)
            throw new IllegalArgumentException();

        e.compile(mv, cv);
        type = getType();
        mv.visitInsn(ICONST_M1);
        mv.visitInsn(type.getOpcode(IXOR));
    }
}
