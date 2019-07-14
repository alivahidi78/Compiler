package classes.declarations;

import classes.expr.Expr;
import classes.Part;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class VarDclPart extends Part {

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }

    public VarDclPart(String name, Expr expr) {
        //TODO
    }
}
