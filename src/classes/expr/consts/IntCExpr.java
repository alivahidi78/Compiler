package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.SIPUSH;

public class IntCExpr extends Const {
    private Integer num;

    public IntCExpr(int num) {
        type = Type.INT_TYPE;
        this.num = num;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if (num >= Byte.MIN_VALUE && num <= Byte.MAX_VALUE) {
            mv.visitIntInsn(BIPUSH, num);
        } else if (num >= Short.MIN_VALUE && num <= Short.MAX_VALUE) {
            mv.visitIntInsn(SIPUSH, num);
        } else {
            mv.visitLdcInsn(num);
        }
    }

    @Override
    public Integer getValue() {
        return num;
    }
}
