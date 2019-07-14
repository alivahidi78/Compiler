package classes.Help;

import org.objectweb.asm.Type;

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
}
