package classes;

import classes.DSCPs.Descriptor;
import org.objectweb.asm.Label;

import java.util.ArrayList;

public class Frame {
    private ArrayList<Descriptor> descriptors = new ArrayList<>();
    private int current_index;
    private Label startLabel;
    private Label endLabel;
    private ScopeType scopeType;

    public Frame(int current_index, ScopeType scopeType) {
        this.current_index = current_index;
        this.startLabel = new Label();
        this.endLabel = new Label();
        this.scopeType = scopeType;
    }


    public int getCurrent_index() {
        return current_index;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setCurrent_index(int current_index) {
        this.current_index = current_index;
    }

    public void addDSCP(Descriptor descriptor){
        descriptors.add(descriptor);
    }

    public ArrayList<Descriptor> getDescriptors() {
        return descriptors;
    }

    public Label getStartLabel() {
        return startLabel;
    }

    public Label getEndLabel() {
        return endLabel;
    }

    public void setStartLabel(Label startLabel) {
        this.startLabel = startLabel;
    }

    public void setEndLabel(Label endLabel) {
        this.endLabel = endLabel;
    }
}
