package classes.function;

import classes.expr.Expr;
import classes.help.Constants;
import classes.help.SymbolTable;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class FuncCall extends Expr{
    String id;
    Parameters parameters ;
    FuncDcl funcDcl;

    public FuncCall(String id, Parameters parameters) {
        this.id = id;
        this.parameters = parameters;
    }


    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        Label start = new Label();
        Label middle = new Label();
        Label end = new Label();
        mv.visitJumpInsn(Opcodes.GOTO,middle);
        mv.visitLabel(start);
        for (Expr exp : parameters.getExpressions()) {
            exp.compile(mv, cv);
        }
        mv.visitJumpInsn(Opcodes.GOTO,end);
        Type[] typesInComped = new Type[parameters.getExpressions().size()];
        for (int i = 0; i < parameters.getExpressions().size(); i++) {
            typesInComped[i] = parameters.getExpressions().get(i).getType();
        }
        this.funcDcl = SymbolTable.getInstance().getFunction(id, typesInComped);
        this.type = funcDcl.getType();
        if(parameters.getExpressions().size() != funcDcl.getArgTypes().length){
            throw new RuntimeException("the static array doesn't have any signature like this");
        }
        mv.visitLabel(middle);
        mv.visitJumpInsn(org.objectweb.asm.Opcodes.GOTO,start);
        mv.visitLabel(end);
        mv.visitMethodInsn(org.objectweb.asm.Opcodes.INVOKESTATIC, Constants.DEFAULT_MAIN_CLASS_NAME, funcDcl.getName(), funcDcl.getSignature(), false);
    }
}
