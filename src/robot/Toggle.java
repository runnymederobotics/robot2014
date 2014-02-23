/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

/**
 *
 * @author 1310
 */
public class Toggle {

    boolean state;
    boolean lastInput;
    long toggleTime;

    public Toggle(boolean state) {
        this.state = state;
    }

    public void feed(boolean input) {

        if (!lastInput && input) {
            state = !state;
        }
        lastInput = input;
        toggleTime = System.currentTimeMillis();

    }
    
    public long getToggleTime(){
        return toggleTime;
    }
    
    public boolean get(){
        return state;
    }
    
}

