package classes.expr.consts;

import classes.help.Functions;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.BIPUSH;

public class SizeOfExpr extends Const {
    private Integer value;

    public SizeOfExpr(String type) {
        this.type = Type.INT_TYPE;
        this.value = Functions.getSize(type);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        mv.visitIntInsn(BIPUSH, value);
    }

    @Override
    public Object getValue() {
        return value;
    }
}
