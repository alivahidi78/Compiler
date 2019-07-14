package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class StringCExpr extends Const {
    private String val;

    public StringCExpr(String val) {
        this.val = val;
        type = Type.getType(String.class);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitLdcInsn(val);
    }

    @Override
    public String getValue() {
        return val;
    }
}
