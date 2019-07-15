package classes.expr.binaryExpr.conditional;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Or extends ConditionalExpr {
    public Or() {

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        try {
            AndOr(false,mv, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
