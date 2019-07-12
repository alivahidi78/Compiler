package classes;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public interface Part {
    void compile(MethodVisitor mv, ClassVisitor cv);
}
