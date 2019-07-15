package classes.statements.conditionalSts;

import classes.Block;
import classes.ScopeType;
import classes.expr.Expr;
import classes.expr.binaryExpr.conditional.NotEq;
import classes.expr.consts.IntCExpr;
import classes.help.SymbolTable;
import classes.statements.Statement;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.IFEQ;

public class If extends Statement {
    Expr cond;
    Block block1, block2;

    public If(Expr condition, Block then, Block elseB) {
        this.cond = condition;
        this.block1 = then;
        this.block2 = elseB;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        SymbolTable.getInstance().addFrame(ScopeType.OTHER);
        NotEq notEqual = new NotEq();
        notEqual.setBinaryExpr(cond, new IntCExpr(0));
        notEqual.compile(mv, cv);
        Label startElse = new Label();
        Label endIf = new Label();
        mv.visitJumpInsn(IFEQ, startElse);
        block1.compile(mv, cv);
        mv.visitJumpInsn(GOTO, endIf);
        if (block2 != null) {
            SymbolTable.getInstance().getFrames().remove(SymbolTable.getInstance().getFrames().size()-1);
            SymbolTable.getInstance().addFrame(ScopeType.OTHER);
            SymbolTable.getInstance().setLabelFirst(startElse);
            SymbolTable.getInstance().setLabelLast(endIf);
            mv.visitLabel(startElse);
            block2.compile(mv, cv);
            mv.visitLabel(endIf);
        } else {
            mv.visitLabel(startElse);
            mv.visitLabel(endIf);
        }
    }
}
