package classes.declarations.Helpers;

import classes.DSCPs.Descriptor;
import classes.DSCPs.DynamicDscp;
import classes.DSCPs.GlobalDscp;
import classes.Frame;
import classes.expr.Expr;
import classes.help.SymbolTable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;


public class SimpleVarDcl extends Declaration {
    private boolean isGlobal;

    public SimpleVarDcl(boolean isGlobal, boolean isConst, Type type, String name, Expr expr) {
        super(isConst, type, name, expr);
        this.isGlobal = isGlobal;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        Descriptor descriptor;
        if (isGlobal) {
            descriptor = new GlobalDscp(isConst, type, name);
            if (expr != null)
                throw new RuntimeException("Global variable cannot be initialized");
            if (isConst)
                throw new RuntimeException("Global variable cannot be constant");

            FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_STATIC, getName(),
                    getType().getDescriptor(), null, null);
            fv.visitEnd();
        } else {
            Frame frame = SymbolTable.getInstance().getLastFrame();
            frame.setCurrent_index(frame.getCurrent_index() + 1);
            descriptor = new DynamicDscp(isConst, type, name, frame.getCurrent_index());
            DynamicDscp dd = (DynamicDscp) descriptor;
            if (expr != null && expr.getType().equals(getType())) {
                expr.compile(mv, cv);
                mv.visitVarInsn(getType().getOpcode(ISTORE), dd.getIndex());
            }
        }
        SymbolTable.getInstance().addVariable(descriptor);
    }
}
