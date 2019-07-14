package classes.function;

import classes.Block;
import classes.Part;
import classes.help.Functions;
import classes.help.SymbolTable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class FuncDcl extends Part {
    private Type type;
    private String name;
    private String signature;
    private Type[] args;

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }

    public FuncDcl(String type, String name, ArrayList<Argument> args, Block block) {
        this.type = Functions.toType(type);
        this.name = name;
        this.args = new Type[args.size()];
        for (int i = 0; i < args.size(); i++)
            this.args[i] = args.get(i).getType();
        StringBuilder sig = new StringBuilder();
        sig.append("(");
        for (Type arg : this.args)
            sig.append(arg.toString());
        sig.append(")");
        sig.append(this.type.toString());
        signature = sig.toString();
        FuncDcl f = SymbolTable.getInstance().getFunction(name, this.args);
        if (f != null)
            throw new RuntimeException("Repetitive Function");
        SymbolTable.getInstance().addFunction(this);
    }

    public boolean checkArgs(Type[] args) {
        return Arrays.equals(this.args, args);
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }
}
