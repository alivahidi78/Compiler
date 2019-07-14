package classes.declarations;

import classes.Part;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Struct extends Part {
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }

    public Struct(String name, ArrayList<VarDcl> dcls){
        //TODO
    }
}
