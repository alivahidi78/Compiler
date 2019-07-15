package classes.expr.variables;

import classes.DSCPs.Descriptor;
import classes.expr.Expr;
import classes.help.SymbolTable;

public abstract class Variable extends Expr {
    protected String name;

    public Variable(String name) {
        this.name = name;
    }

    public Descriptor getDSCP() {
        return SymbolTable.getInstance().getDescriptor(name);
    }
}
