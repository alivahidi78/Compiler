package classes.help;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

public class Functions {
    public static Type toType(String type) {
        switch (type) {
            case "int":
                return Type.INT_TYPE;
            case "long":
                return Type.LONG_TYPE;
            case "char":
                return Type.CHAR_TYPE;
            case "bool":
                return Type.BOOLEAN_TYPE;
            case "double":
                return Type.DOUBLE_TYPE;
            case "float":
                return Type.FLOAT_TYPE;
            case "string":
                return Type.getType(String.class);
            case "void":
                return Type.VOID_TYPE;
            case "short":
                return Type.SHORT_TYPE;
            default:
                return Type.getType("L" + type + ";");
        }
    }

    private static boolean isStruct(Type type) {
        return type != Type.BOOLEAN_TYPE && type != Type.INT_TYPE &&
                type != Type.CHAR_TYPE && type != Type.LONG_TYPE &&
                type != Type.FLOAT_TYPE && type != Type.DOUBLE_TYPE &&
                type != Type.SHORT_TYPE;
    }

    private static boolean isInt(Type type) {
        return type == Type.BOOLEAN_TYPE || type == Type.INT_TYPE ||
                type == Type.CHAR_TYPE || type == Type.SHORT_TYPE;
    }

    public static void cast(Type type1, Type type2, MethodVisitor mv) throws Exception {
        if (type1.equals(type2))
            return;
        if (isStruct(type1) || isStruct(type2)) {
            throw new Exception("Cannot cast between different structs");
        } else {
            if (type2.equals(Type.DOUBLE_TYPE)) {
                if (type1.equals(Type.FLOAT_TYPE)) {
                    mv.visitInsn(F2D);
                } else if (isInt(type1)) {
                    mv.visitInsn(I2D);
                } else if (type1.equals(Type.LONG_TYPE)) {
                    mv.visitInsn(L2D);
                }
                return;
            }
            if (type2.equals(Type.FLOAT_TYPE)) {
                if (isInt(type1)) {
                    mv.visitInsn(I2F);
                } else if (type1.equals(Type.LONG_TYPE)) {
                    mv.visitInsn(L2F);
                }
                return;
            }
            if (type2.equals(Type.LONG_TYPE)) {
                if (isInt(type1)) {
                    mv.visitInsn(I2L);
                }
                return;
            }
            throw new Exception("Cannot cast " + type1 + " to " + type2);
        }
    }

    public static int getSize(String name){
        int size;
        switch (name) {
            case "int":
                size = Integer.SIZE;
                break;
            case "long":
                size = Long.SIZE;
                break;
            case "char":
                size = Character.SIZE;
                break;
            case "bool":
                size = 1;
                break;
            case "double":
                size = Double.SIZE;
                break;
            case "float":
                size = Float.SIZE;
                break;
            default:
                throw new RuntimeException("type is not valid");

        }
        return size;
    }
}
