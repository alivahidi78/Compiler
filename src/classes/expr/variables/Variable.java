package classes.expr.variables;

import classes.DSCPs.Descriptor;
import classes.expr.Expr;
import classes.help.SymbolTable;
import org.objectweb.asm.Type;

public abstract class Variable extends Expr {
    protected String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Type getType() {
        return getDSCP().getType();
    }

    public Descriptor getDSCP() {
        return SymbolTable.getInstance().getDescriptor(name);
    }
}
