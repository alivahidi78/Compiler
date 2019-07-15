package classes.help;

import classes.DSCPs.Descriptor;
import classes.DSCPs.DynamicDscp;
import classes.Frame;
import classes.ScopeType;
import classes.declarations.Struct;
import classes.function.FuncDcl;
import org.objectweb.asm.Label;
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
    private ArrayList<Frame> frames = new ArrayList<>();
    private FuncDcl lastFunc;


    public static SymbolTable getInstance() {
        return instance;
    }

    private SymbolTable() {
        Frame baseFrame = new Frame(1,null);//There is Always a (String... args) in main Function.
        frames.add(baseFrame);
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

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public FuncDcl getLastFunc() {
        return lastFunc;
    }

    public void setLastFunc(FuncDcl lastFunc) {
        this.lastFunc = lastFunc;
    }

    public void addVariable(Descriptor descriptor) {
        frames.get(frames.size() - 1).getDescriptors().add(descriptor);
        if (descriptor instanceof DynamicDscp)
            frames.get(frames.size() - 1).setCurrent_index(frames.get(frames.size() - 1).getCurrent_index() + descriptor.getType().getSize() - 1);
    }
    public void addFrame(ScopeType scopeType) {
        Frame frame = null;
        if (scopeType != ScopeType.FUNCTION){
            frame = new Frame(frames.get(frames.size() - 1).getCurrent_index(), scopeType);
        }else {
            frame = new Frame(0, scopeType);
        }
        frames.add(frame);
    }

    public Descriptor getDescriptor(String name) {
        int from = frames.size();
        while (from != 0) {
            from--;
            for (Descriptor descriptor : frames.get(from).getDescriptors()){
                if (descriptor.getName().equals(name)) return descriptor;
            }
        }
        throw new RuntimeException("DSCP not found");
    }

    public boolean canHaveBreak(){
        //fixme
        return frames.get(frames.size() - 1).getScopeType() == ScopeType.LOOP || frames.get(frames.size() - 1).getScopeType() == ScopeType.SWITCH;
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
        return frames.get(frames.size() - 1);
    }
}
