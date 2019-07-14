package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;

public class BooleanCExpr extends Const {
    private Boolean val;

    public Boolean getValue() {
        return val;
    }

    public BooleanCExpr(boolean val) {
        this.val = val;
        type = Type.BOOLEAN_TYPE;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitInsn(val ? ICONST_1 : ICONST_0);
    }
}
