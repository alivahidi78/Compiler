package classes.expr;

import classes.expr.variables.Variable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Assignment extends Expr {
    public void setDetail(Variable v, Expr e) {
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }
}
