package classes.statements.conditionalSts.switchSts;

import classes.Block;
import classes.expr.consts.IntCExpr;
import classes.statements.Statement;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Case extends Statement {
    public Case(IntCExpr num, Block block) {
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
