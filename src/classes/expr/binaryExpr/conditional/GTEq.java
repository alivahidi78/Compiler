package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class GTEq extends ConditionalExpr {
    public GTEq(){

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        compare(Opcodes.IFLT, Opcodes.IF_ICMPLT, mv, cv);
    }
}
