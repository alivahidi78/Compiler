package classes.Help;

import classes.function.FuncDcl;
import org.objectweb.asm.Type;

public class SymbolTable {
    private static SymbolTable instance = new SymbolTable();

    public static SymbolTable getInstance() {
        return instance;
    }

    private SymbolTable() {
    }

    public FuncDcl getFunction(String name, Type[] args) {
        //TODO
        return null;
    }
}
