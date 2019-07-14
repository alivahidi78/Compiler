package classes.function;

import classes.Part;
import classes.expr.Expr;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class Parameters{
    private ArrayList <Expr> expressions = new ArrayList<Expr>();

    public Parameters(Expr exp, ArrayList<Expr> expressions){
        this.expressions = expressions;
        this.expressions.add(exp);
    }

    public Parameters(Expr ... exps){
        for (Expr e : exps){
            expressions.add(e);
        }
    }

    public ArrayList<Expr> getExpressions() {
        return expressions;
    }
}
