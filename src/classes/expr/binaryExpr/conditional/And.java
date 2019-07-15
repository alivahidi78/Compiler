package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class And extends ConditionalExpr {
    public And() {

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        try {
            AndOr(true, mv, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
