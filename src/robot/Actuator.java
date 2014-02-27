package robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

public class Actuator {

    private DoubleSolenoid doubleSolenoid;
    private Solenoid solenoid;
    private Relay relay;
    private boolean currentState;
    private boolean inverted;

    public Actuator(DoubleSolenoid doubleSolenoid, boolean defaultState, boolean inverted) {
        this.doubleSolenoid = doubleSolenoid;
        this.currentState = defaultState;
        this.inverted = inverted;

    }

    public Actuator(Solenoid solenoid, boolean defaultState, boolean inverted) {
        this.solenoid = solenoid;
        this.currentState = defaultState;
        this.inverted = inverted;
    }

    public Actuator(Relay relay, boolean defaultState, boolean inverted) {
        this.relay = relay;
        this.currentState = defaultState;
        this.inverted = inverted;
    }

    public void set(boolean state) {
        currentState = state;

        if (inverted) {
            state = !state;
        }

        if (doubleSolenoid != null) {
            DoubleSolenoid.Value value;
            if (state) {
                value = DoubleSolenoid.Value.kForward;
            } else {
                value = DoubleSolenoid.Value.kReverse;
            }

            doubleSolenoid.set(value);
        } else if (solenoid != null) {
            solenoid.set(state);
        } else if (relay != null) {
            Relay.Value value;
            if (state) {
                value = Relay.Value.kForward;
            } else {
                value = Relay.Value.kOff;
            }

            relay.set(value);
        }
    }

    public boolean get() {
        return currentState;
    }
}
