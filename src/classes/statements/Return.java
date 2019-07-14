package classes.statements;

import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Return extends Statement {
    public Return(Expr e) {
        //TODO
    }

    public Return(){
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
