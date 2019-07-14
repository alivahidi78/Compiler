package classes;

import classes.DSCPs.Descriptor;
import org.objectweb.asm.Label;

import java.util.ArrayList;

public class Frame {
    private ArrayList<Descriptor> descriptors = new ArrayList<>();
    private int current_index = 0;
    private Label start;
    private Label end;
    private ScopeType scopeType;


    public int getCurrent_index() {
        return current_index;
    }

    public Label getStart() {
        return start;
    }

    public Label getEnd() {
        return end;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setCurrent_index(int current_index) {
        this.current_index = current_index;
    }

    public void setStart(Label start) {
        this.start = start;
    }

    public void setEnd(Label end) {
        this.end = end;
    }

    public void addDSCP(Descriptor descriptor){
        descriptors.add(descriptor);
    }
}
