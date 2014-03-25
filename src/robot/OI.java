package robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public static final class Driver {

        public static final Joystick joystick = new Joystick(1);
        public static final int DRIVE_AXIS = 2;
        public static final int ROTATION_AXIS = 3;
        public static final int SHIFTER_BUTTON = 8;
        public static final int PID_TOGGLE = 9;
        public static final int PICKUP_REVERSE_BUTTON = 3;
        public static final int LOWER_PICKUP_BUTTON = 5;
        public static final int RUN_PICKUP_BUTTON = 7;
        public static final int SHOOTER_BUTTON = 6;
    }

    public static final class Operator {

        public static final Joystick joystick = new Joystick(2);
        public static final int WINCH_TOGGLE = 2;
        public static final int POSSESION_TOGGLE_BUTTON = 4;
    }

    public boolean getRunPickupButton() {
        return Driver.joystick.getRawButton(Driver.RUN_PICKUP_BUTTON);
    }

    public boolean getWinchToggle() {
        return Operator.joystick.getRawButton(Operator.WINCH_TOGGLE);
    }

    public boolean getLowerPickupButton() {
        return Driver.joystick.getRawButton(Driver.LOWER_PICKUP_BUTTON);
    }

    public boolean getPickupReverseButton() {
        return Driver.joystick.getRawButton(Driver.PICKUP_REVERSE_BUTTON);
    }

    public boolean getShooterFireButton() {
        return Driver.joystick.getRawButton(Driver.SHOOTER_BUTTON);
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

    public boolean getPossesionToggle() {
        return Operator.joystick.getRawButton(Operator.POSSESION_TOGGLE_BUTTON);
    }

    public boolean getTogglePID() {
        return Driver.joystick.getRawButton(Driver.PID_TOGGLE);
    }
}
