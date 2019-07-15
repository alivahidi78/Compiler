package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.IFNE;
import static org.objectweb.asm.Opcodes.IF_ICMPNE;

public class EqEq extends ConditionalExpr {
    public EqEq() {

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        compare(IFNE, IF_ICMPNE, mv, cv);
    }
}
