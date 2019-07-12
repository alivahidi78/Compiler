package classes.function;

import classes.Block;
import classes.Part;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class FuncDcl implements Part {
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }

    public FuncDcl(String type, String name, ArrayList<Argument> args, Block block){
        //TODO
    }
}
