import classes.Program;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Parser p = new Parser(new Scanner(new FileReader("input.txt")));
        Object result = p.parse().value;
        Program program = (Program) result;
        program.compile();
    }
}
