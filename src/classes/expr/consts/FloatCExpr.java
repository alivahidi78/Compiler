package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class FloatCExpr extends Const {
    private Float num;

    public FloatCExpr(float num) {
        this.num = num;
        type = Type.FLOAT_TYPE;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitLdcInsn(num);
    }

    @Override
    public Object getValue() {
        return num;
    }
}
