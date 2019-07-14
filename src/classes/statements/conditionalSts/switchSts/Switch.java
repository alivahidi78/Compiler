package classes.statements.conditionalSts.switchSts;

import classes.Block;
import classes.expr.Expr;
import classes.statements.Statement;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Switch extends Statement {
    public Switch(Expr expr, ArrayList<Case> cases, Block defaultCase) {
        //TODO
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {

    }
}
