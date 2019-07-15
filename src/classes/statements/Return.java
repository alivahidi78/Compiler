package classes.statements;

import classes.Frame;
import classes.expr.Expr;
import classes.function.FuncDcl;
import classes.help.Functions;
import classes.help.SymbolTable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.RETURN;

public class Return extends Statement {
    Expr expr;
    Frame frame;

    public Return(Expr e) {
        this.expr = e;
    }

    public Return() {
        expr = null;
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        frame = SymbolTable.getInstance().getLastFrame();
        FuncDcl f = SymbolTable.getInstance().getLastFunc();
        for (Return r : f.returns) {
            if (r.frame == frame) {
                throw new RuntimeException("Multiple returns in same scope");
            }
        }
        f.returns.add(this);
        if (expr != null) {
            expr.compile(mv, cv);
            if (f.getType().equals(Type.VOID_TYPE)) {
                throw new RuntimeException("Type Mismatch");
            } else if (!f.getType().equals(expr.getType())) {
                throw new RuntimeException("Type Mismatch");
            }
            try {
                Functions.cast(expr.getType(), f.getType(), mv);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mv.visitInsn(expr.getType().getOpcode(IRETURN));
        } else {
            if (!f.getType().equals(Type.VOID_TYPE)) {
                throw new RuntimeException("Return Empty");
            }
            mv.visitInsn(RETURN);
        }
    }
}
