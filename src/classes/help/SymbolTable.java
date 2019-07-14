package classes.help;

import classes.function.FuncDcl;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {

    private boolean isCurrentScopeGlobal = true;//TODO handle
    private static SymbolTable instance = new SymbolTable();
    private HashMap<String, List<FuncDcl>> functions = new HashMap<>();

    public static SymbolTable getInstance() {
        return instance;
    }

    private SymbolTable() {
    }

    public boolean isCurrentScopeGlobal() {
        return isCurrentScopeGlobal;
    }

    public FuncDcl getFunction(String name, Type[] args) {
        if (!functions.containsKey(name))
            return null;
        List<FuncDcl> overloads = functions.get(name);
        for (FuncDcl f : overloads) {
            if (f.checkArgs(args))
                return f;
        }
        return null;
    }

    public void addFunction(FuncDcl funcDcl) {
        List<FuncDcl> overloads;
        if (!functions.containsKey(funcDcl.getName())) {
            overloads = new ArrayList<>();
            overloads.add(funcDcl);
            functions.put(funcDcl.getName(), overloads);
        } else {
            overloads = functions.get(funcDcl.getName());
            overloads.add(funcDcl);
        }

    }
}
