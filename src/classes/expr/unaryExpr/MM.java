package classes.expr.unaryExpr;

import classes.DSCPs.Descriptor;
import classes.DSCPs.DynamicDscp;
import classes.expr.Expr;
import classes.expr.variables.SimpleVar;
import classes.expr.variables.Variable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

public class MM extends UnaryExpr {
    private Expr e;

    public MM(Expr e) {
        this.e = e;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        if (!(e instanceof Variable) ||
                (type != Type.INT_TYPE && type != Type.DOUBLE_TYPE
                        && type != Type.LONG_TYPE && type != Type.CHAR_TYPE))
            throw new IllegalArgumentException();
        Variable var = (Variable) e;
        type = var.getType();
        if (e instanceof SimpleVar) {
            Descriptor dscp = var.getDSCP();

            if (dscp instanceof DynamicDscp) {
                int index = ((DynamicDscp) dscp).getIndex();
                if (type == Type.INT_TYPE || type == Type.CHAR_TYPE) {
                    var.compile(mv, cv);
                    mv.visitIincInsn(index, -1);
                } else {
                    var.compile(mv, cv);//Postfix

                    var.compile(mv, cv);

                    if (type == Type.DOUBLE_TYPE) {
                        mv.visitInsn(Opcodes.DCONST_1);
                    } else {
                        mv.visitInsn(LCONST_1);
                    }
                    mv.visitInsn(type.getOpcode(ISUB));
                    mv.visitVarInsn(type.getOpcode(ISTORE), index);
                }

            } else {
                throw new RuntimeException();
            }
        }
    }
}
