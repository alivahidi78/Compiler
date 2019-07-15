package classes.help;

import classes.DSCPs.Descriptor;
import classes.DSCPs.DynamicDscp;
import classes.Frame;
import classes.ScopeType;
import classes.declarations.Struct;
import classes.function.FuncDcl;
import jdk.internal.org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class SymbolTable {
    private static SymbolTable instance = new SymbolTable();
    private boolean isCurrentScopeGlobal = true;
    private HashMap<String, List<FuncDcl>> functions = new HashMap<>();
    private HashMap<String, List<Struct>> structs = new HashMap<>();
    private Stack<Frame> frameStack = new Stack<>();
    private FuncDcl lastFunc;


    public static SymbolTable getInstance() {
        return instance;
    }

    private SymbolTable() {
        Frame baseFrame = new Frame(1,null);//There is Always a (String... args) in main Function.
        frameStack.add(baseFrame);
    }

    public boolean isCurrentScopeGlobal() {
        return isCurrentScopeGlobal;
    }

    public FuncDcl getFunction(String name, Type[] args) {
        if (!functions.containsKey(name))
            return null;
        List<FuncDcl> overloads = functions.get(name);
        for (FuncDcl f : overloads) {
            if (f.checkArgs(args))
                return f;
        }
        return null;
    }

    public void addFunction(FuncDcl funcDcl) {
        List<FuncDcl> overloads;
        if (!functions.containsKey(funcDcl.getName())) {
            overloads = new ArrayList<>();
            overloads.add(funcDcl);
            functions.put(funcDcl.getName(), overloads);
        } else {
            overloads = functions.get(funcDcl.getName());
            overloads.add(funcDcl);
        }

    }

    public Stack<Frame> getFrameStack() {
        return frameStack;
    }

    public FuncDcl getLastFunc() {
        return lastFunc;
    }

    public void setLastFunc(FuncDcl lastFunc) {
        this.lastFunc = lastFunc;
    }

    public void addVariable(Descriptor descriptor) {
        frameStack.peek().getDescriptors().add(descriptor);
        if (descriptor instanceof DynamicDscp)
            frameStack.peek().setCurrent_index(frameStack.peek().getCurrent_index() + descriptor.getType().getSize() - 1);
    }
    public void addFrame(ScopeType scopeType) {
        Frame frame = null;
        if (scopeType != ScopeType.FUNCTION){
            frame = new Frame(frameStack.peek().getCurrent_index(), scopeType);
        }else {
            frame = new Frame(0, scopeType);
        }
        frameStack.add(frame);
    }

    public Descriptor getDescriptor(String name) {
//        int from = frameStack.size();
//        while (from != 0) {
//            from--;
//            if (frameStack.get(from).containsKey(name)) {
//                return frameStack.get(from).get(name);
//            }
//        }
//        throw new RuntimeException();
        return null; //TODO
    }

    public boolean canHaveBreak(){
        //fixme
        return frameStack.peek().getScopeType() == ScopeType.LOOP || frameStack.peek().getScopeType() == ScopeType.SWITCH;
    }

    public int returnNewIndex(){
        return 0;
    }

    public void setLabelFirst(Label label) {
    }

    public Label getLabelLast(){
        return null;}

    public void setLabelLast(Label label) {
    }

    public Label getLabelStart() {
        return null;
    }

    public Frame getLastFrame() {
        return frameStack.peek();
    }
}
