package classes.expr.variables;

import classes.DSCPs.Descriptor;
import classes.DSCPs.DynamicDscp;
import classes.help.Constants;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.ILOAD;

public class SimpleVar extends Variable {

    public SimpleVar(String name) {
        super(name);
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        type = this.getDSCP().getType();
        Descriptor d = getDSCP();
        if (d instanceof DynamicDscp) {
            int index = ((DynamicDscp) d).getIndex();
            mv.visitVarInsn(getType().getOpcode(ILOAD), index);
        } else {
            mv.visitFieldInsn(GETSTATIC, Constants.DEFAULT_MAIN_CLASS_NAME, name,
                    getType().getDescriptor());
        }
    }
}
