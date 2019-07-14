package classes;

import classes.help.Constants;
import classes.help.SymbolTable;
import classes.declarations.Struct;
import classes.declarations.VarDcl;
import classes.function.FuncDcl;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.objectweb.asm.Opcodes.*;

public class Program {
    private ArrayList<Part> parts = new ArrayList<>();

    public void compile(String to) throws FileNotFoundException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(Paths.
                get(Constants.COMPILE_PATH, Constants.DEFAULT_MAIN_CLASS_NAME + ".txt").toFile()));
        MethodVisitor mv;

        //Class Header
        cv.visit(V1_8, ACC_PUBLIC + ACC_SUPER, Constants.DEFAULT_MAIN_CLASS_NAME, null,
                Type.getInternalName(Object.class), null);

        //Constructor
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object",
                "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        //Struct Declarations
        for (Part p : parts) {
            if (p instanceof Struct) {
                p.compile(mv, cv);
            }
        }

        //Variable Declarations
        for (Part p : parts) {
            if (p instanceof VarDcl) {
                p.compile(mv, cv);
            }
        }

        //Function Declarations
        for (Part p : parts) {
            if (p instanceof FuncDcl) {
                p.compile(mv, cv);
            }
        }

        //main function
        mv = cv.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        FuncDcl f = SymbolTable.getInstance().getFunction("main", new Type[0]);
        if(f == null)
            throw new RuntimeException("missing main function");
        if (!f.getType().equals(Type.INT_TYPE))
            throw new RuntimeException("main function should return integer");
        mv.visitMethodInsn(INVOKESTATIC, Constants.DEFAULT_MAIN_CLASS_NAME,
                f.getName(), f.getSignature(), false);
        //TODO process return value

        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        cv.visitEnd();
        byte[] bytes = cw.toByteArray();

        try {
            Files.write(Paths.get(to, Constants.DEFAULT_MAIN_CLASS_NAME + ".class"), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Program add(Part part) {
        parts.add(part);
        return this;
    }
}
