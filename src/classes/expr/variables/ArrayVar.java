package classes.expr.variables;

import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class ArrayVar extends Variable {
    public ArrayVar(String name, ArrayList<Expr> pars) {
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
