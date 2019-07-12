package classes.statements.loopSts;

import classes.statements.Statement;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class Break extends Statement {
    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
    }
}
