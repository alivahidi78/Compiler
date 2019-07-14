package classes.declarations;

import classes.Operation;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class VarDcl extends Operation {
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        //TODO
    }

    public VarDcl(boolean isConst, String type, ArrayList<VarDclPart> parts){
        //TODO
    }
}
