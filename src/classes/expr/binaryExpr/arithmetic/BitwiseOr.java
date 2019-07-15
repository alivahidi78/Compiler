package classes.expr.binaryExpr.arithmetic;

import classes.expr.binaryExpr.BinaryExpr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.IOR;

public class BitwiseOr extends BinaryExpr {
    public BitwiseOr(){

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        exp1.compile(mv,cv);
        exp2.compile(mv,cv);

        Type type = getType();


        mv.visitInsn(type.getOpcode(IOR));

    }
}
