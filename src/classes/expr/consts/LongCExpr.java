package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class LongCExpr extends Const {
    private Long num;

    public LongCExpr(long num) {
        this.num = num;
        type = Type.LONG_TYPE;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitLdcInsn(num);
    }

    @Override
    public Long getValue() {
        return num;
    }
}
