package classes.statements.conditionalSts;

import classes.Block;
import classes.expr.Expr;
import classes.statements.Statement;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class If extends Statement {
    public If(Expr condition, Block then, Block elseB) {
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
