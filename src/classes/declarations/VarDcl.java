package classes.declarations;

import classes.Operation;
import classes.declarations.Helpers.Declaration;
import classes.declarations.Helpers.SimpleVarDcl;
import classes.help.Functions;
import classes.help.SymbolTable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

public class VarDcl extends Operation {
    boolean isConst;
    public String type;
    ArrayList<VarDclPart> parts;
    ArrayList<Declaration> declarations;

    public VarDcl(boolean isConst, String type, ArrayList<VarDclPart> parts) {
        this.isConst = isConst;
        this.type = type;
        this.parts = parts;
        declarations = new ArrayList<>();
        for (VarDclPart part : parts) {//TODO handle auto
            declarations.add(new SimpleVarDcl(SymbolTable.getInstance().isCurrentScopeGlobal(),
                    isConst, Functions.toType(type), part.getName(), part.getExpr()));
        }
    }

    @Override
    public void compile(MethodVisitor mv, ClassVisitor cv) {
        for (Declaration dcl : declarations) {
            dcl.compile(mv, cv);
        }
    }
}
