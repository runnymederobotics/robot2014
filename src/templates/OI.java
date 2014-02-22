package templates;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public static class Driver {

        public final static Joystick joystick = new Joystick(1);
        public final static int DRIVE_AXIS = 2;
        public final static int ROTATION_AXIS = 3;
        public final static int SHIFTER_BUTTON = 8;
        public final static int PICKUP_BUTTON = 7;
    }

    public static class Operator {

        public final static Joystick joystick = new Joystick(2);
        public final static int RUN_PICKUP_BUTTON = 7;
        public final static int WINCH_TOGGLE = 2;
        public final static int LOWER_PICKUP_BUTTON = 5;
        public final static int SHOOTER_BUTTON = 6;
        public final static int PICKUP_REVERSE_BUTTON = 3;
    }

    public boolean getRunPickupButton() {
        return Operator.joystick.getRawButton(Operator.RUN_PICKUP_BUTTON);
    }

    public boolean getWinchToggle() {
        return Operator.joystick.getRawButton(Operator.WINCH_TOGGLE);
    }

    public boolean getLowerPickupButton() {
        return Operator.joystick.getRawButton(Operator.LOWER_PICKUP_BUTTON);
    }

    public boolean getPickupReverseButton() {
        return Operator.joystick.getRawButton(Operator.PICKUP_REVERSE_BUTTON);
    }

    public boolean getPickupButton() {
        return Driver.joystick.getRawButton(Driver.PICKUP_BUTTON);
    }

    public boolean getShooterFireButton() {
        return Operator.joystick.getRawButton(Operator.SHOOTER_BUTTON);
    }

    public boolean getShifterButton() {
        return Driver.joystick.getRawButton(Driver.SHIFTER_BUTTON);
    }

    public double getDriveAxis() {
        return Driver.joystick.getRawAxis(Driver.DRIVE_AXIS);
    }

    public double getRotationAxis() {
        return Driver.joystick.getRawAxis(Driver.ROTATION_AXIS);
    }
}
