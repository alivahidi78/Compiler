package classes.expr.consts;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class DoubleCExpr extends Const {
    private Double num;

    public DoubleCExpr(double num) {
        this.num = num;
        type = Type.DOUBLE_TYPE;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitLdcInsn(num);
    }

    @Override
    public Double getValue() {
        return num;
    }
}
