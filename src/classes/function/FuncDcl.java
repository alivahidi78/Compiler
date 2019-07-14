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

import static org.objectweb.asm.Opcodes.*;

public class FuncDcl extends Part {
    private Type type;
    private String name;
    private String signature;
    private Type[] argTypes;
    private ArrayList<Argument> args;
    private Block block;

    public FuncDcl(String type, String name, ArrayList<Argument> args, Block block) {
        this.type = Functions.toType(type);
        this.name = name;
        this.argTypes = new Type[args.size()];
        for (int i = 0; i < args.size(); i++)
            this.argTypes[i] = args.get(i).getType();
        StringBuilder sig = new StringBuilder();
        sig.append("(");
        for (Type arg : this.argTypes)
            sig.append(arg.toString());
        sig.append(")");
        sig.append(this.type.toString());
        signature = sig.toString();
        FuncDcl f = SymbolTable.getInstance().getFunction(name, this.argTypes);
        if (f != null)
            throw new RuntimeException("Repetitive Function");
        SymbolTable.getInstance().addFunction(this);
        this.args = args;
        this.block = block;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //FIXME
        MethodVisitor newMv = cv.visitMethod(ACC_PUBLIC + ACC_STATIC,
                name, this.signature, null, null);
        newMv.visitCode();
        newMv.visitVarInsn(ILOAD, 1);
        newMv.visitInsn(IRETURN);
        newMv.visitMaxs(1, 1);
        newMv.visitEnd();

    }

    public boolean checkArgs(Type[] args) {
        return Arrays.equals(this.argTypes, args);
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
