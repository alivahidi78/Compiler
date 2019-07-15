package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LT extends ConditionalExpr {
    public LT(){

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        compare(Opcodes.IFGE, Opcodes.IF_ICMPGE, mv, cv);
    }
}
