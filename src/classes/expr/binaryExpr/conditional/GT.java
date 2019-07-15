package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GT extends ConditionalExpr {
    public GT() {

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        compare(Opcodes.IFLE, Opcodes.IF_ICMPLE, mv, cv);
    }
}
