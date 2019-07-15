package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LTEq extends ConditionalExpr {
    public LTEq(){

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        compare(Opcodes.IFGT, Opcodes.IF_ICMPGT, mv, cv);
    }
}
