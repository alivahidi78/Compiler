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

public abstract class Assignment extends Expr {
    protected Variable var;
    protected Expr expr;

    public void setDetail(Variable v, Expr e) {
        this.var = v;
        this.expr = e;
    }
}
