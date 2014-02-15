package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public static class Driver {
        public final static Joystick joystick = new Joystick(1);
        public final static int DRIVE_AXIS = 2;
        public final static int ROTATION_AXIS = 3;
    }
    
    public final int PICKUP_REVERSE_BUTTON = 2;
    public final int PICKUP_BUTTON = 7;
    public final int SHOOTER_BUTTON = 6;
    public final int SHIFTER_BUTTON = 8;
    public final int TOGGLE_RELOAD_BUTTON = 9;
    public final int SHOOTER_ARM_BUTTON = 5;
    
    public boolean getPickupReverseButton() {
        return Driver.joystick.getRawButton(PICKUP_REVERSE_BUTTON);
    }
    
    public boolean getPickupButton() {
        return Driver.joystick.getRawButton(PICKUP_BUTTON);
    }
    
    public boolean getShooterFireButton() {
        return Driver.joystick.getRawButton(SHOOTER_BUTTON);
    }
    
    public boolean getShifterButton() {
        return Driver.joystick.getRawButton(SHIFTER_BUTTON);
    }
    
    public boolean getToggleAutoReloadButton() {
        return Driver.joystick.getRawButton(TOGGLE_RELOAD_BUTTON);
    }
    
    public boolean getShooterArmButton() {
        return Driver.joystick.getRawButton(SHOOTER_ARM_BUTTON);
    }

    public double getDriveAxis() {
        return Driver.joystick.getRawAxis(Driver.DRIVE_AXIS);
    }

    public double getRotationAxis() {
        return Driver.joystick.getRawAxis(Driver.ROTATION_AXIS);
    }
}
