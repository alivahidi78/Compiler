package classes;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public abstract class Part {
    public abstract void compile(MethodVisitor mv, ClassVisitor cv);
}
