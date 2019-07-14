package classes.statements.loopSts;

import classes.Block;
import classes.expr.Assignment;
import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class For extends Loop {
    public For(Assignment assignment, Expr e1, Expr e2, Block block) {

    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
