package classes;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Block extends Part {
    ArrayList <Operation> operationCodes = new ArrayList<>();
    public Block(ArrayList <Operation> operationCodes){
        this.operationCodes = operationCodes;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        for(Operation oc : operationCodes){
            oc.compile(mv,cv);
        }
    }
}
