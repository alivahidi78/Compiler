package classes.statements;

import classes.Operation;
import classes.expr.Assignment;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class AssignmentSt extends Operation {
    Assignment a;
    public AssignmentSt(Assignment assignment) {
        this.a = assignment;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        a.compile(mv,cv);
    }
}
