package classes.expr;

import classes.DSCPs.Descriptor;
import classes.DSCPs.DynamicDscp;
import classes.expr.variables.SimpleVar;
import classes.expr.variables.Variable;
import classes.help.Constants;
import classes.help.Functions;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.PUTSTATIC;

public class Assignment extends Expr {
    private Variable var;
    private Expr expr;

    public void setDetail(Variable v, Expr e) {
        this.var = v;
        this.expr = e;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        this.type = var.getType();
        //TODO arrays
        if (!(var instanceof SimpleVar))
            throw new RuntimeException();
        expr.compile(mv, cv);
        if (this.type != expr.getType()) {
            try {
                Functions.cast(expr.getType(), var.getType(), mv);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        Descriptor dscp = var.getDSCP();
        if (dscp instanceof DynamicDscp) {
            int index = ((DynamicDscp) dscp).getIndex();
            mv.visitVarInsn(var.getType().getOpcode(ISTORE), index);
            var.compile(mv, cv);
        } else {
            mv.visitFieldInsn(PUTSTATIC, Constants.DEFAULT_MAIN_CLASS_NAME,
                    dscp.getName(), dscp.getType().toString());
        }
    }
}
