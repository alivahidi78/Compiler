package classes.function;

import classes.help.Functions;
import org.objectweb.asm.Type;

public class Argument {
    private Type type;
    private String name;
    private Integer dimensions;

    public Argument(String type, String name, int dims) {
        this.type = Functions.toType(type);
        this.name = name;
        this.dimensions = dims;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getDimensions() {
        return dimensions;
    }
}
