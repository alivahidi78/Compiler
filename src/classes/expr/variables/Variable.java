package classes.expr.variables;

import classes.DSCPs.Descriptor;
import classes.expr.Expr;

public abstract class Variable extends Expr {
    protected String name;

    public Variable(String name) {
        this.name = name;
    }

    public Descriptor getDSCP() {
        return null;//SymbolTable.getInstance().getDescriptor(name) TODO
    }
}
