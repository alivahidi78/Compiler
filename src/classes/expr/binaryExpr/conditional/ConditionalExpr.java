package classes.expr.binaryExpr.conditional;

import classes.expr.binaryExpr.BinaryExpr;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ICONST_1;

public abstract class ConditionalExpr extends BinaryExpr {
    public void notIntCompare(MethodVisitor mv, Type type) {
        if (type == Type.DOUBLE_TYPE) {
            mv.visitInsn(Opcodes.DCMPG);
        } else if (type == Type.LONG_TYPE) {
            mv.visitInsn(Opcodes.LCMP);
        }
    }

    public void compare(int notIntOpcode, int intOpcode, MethodVisitor mv, ClassVisitor cv) {
        exp1.compile(mv, cv);
        exp2.compile(mv, cv);
        Type type = getType();
        int opcode;
        if (type != Type.INT_TYPE) {
            notIntCompare(mv, type);
            opcode = notIntOpcode;
        } else {
            opcode = intOpcode;
        }
        Label l1 = new Label();
        Label l2 = new Label();
        mv.visitJumpInsn(opcode, l1);
        mv.visitInsn(ICONST_1);
        mv.visitJumpInsn(GOTO, l2);
        mv.visitLabel(l1);
        mv.visitInsn(ICONST_0);
        mv.visitLabel(l2);
    }

    public void AndOr(boolean isAdd,MethodVisitor mv, ClassVisitor cv) throws Exception {
        Label label = new Label();
        Label EndLabel = new Label();
        exp1.compile(mv, cv);
        mv.visitJumpInsn(isAdd? IFEQ : IFNE, label);
        exp2.compile(mv, cv);
        mv.visitJumpInsn(isAdd? IFEQ : IFNE, label);
        mv.visitInsn(isAdd? ICONST_1 : ICONST_0);
        mv.visitJumpInsn(GOTO, EndLabel);
        mv.visitLabel(label);
        mv.visitInsn(isAdd? ICONST_0 : ICONST_1);
        mv.visitLabel(EndLabel);
        Type type = getType();
        if (type != Type.INT_TYPE)
            throw new Exception("Only Int Types Can Be Operands Of Conditional And");
    }
}
