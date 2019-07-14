package classes.function;

import classes.Block;
import classes.Help.Functions;
import classes.Part;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.util.ArrayList;

public class FuncDcl implements Part {
    private Type type;
    private String name;
    private String signature;
    private Type [] args;
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }

    public FuncDcl(String type, String name, ArrayList<Argument> args, Block block){
        this.type = Functions.toType(type);
        this.name = name;
        this.args = new Type[args.size()];
        //TODO
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
