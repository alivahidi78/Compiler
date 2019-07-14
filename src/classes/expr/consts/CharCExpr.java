package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.SIPUSH;

public class CharCExpr extends Const {
    private Character val;

    public CharCExpr(char val) {
        this.val = val;
        type = Type.CHAR_TYPE;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if (val <= Byte.MAX_VALUE) {
            mv.visitIntInsn(BIPUSH, (int) val);
        } else if (val <= Short.MAX_VALUE) {
            mv.visitIntInsn(SIPUSH, (int) val);
        } else {
            mv.visitLdcInsn((int) val);
        }
    }

    @Override
    public Object getValue() {
        return val;
    }
}
