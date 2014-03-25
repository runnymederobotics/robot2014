package robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Actuator;
import robot.Constants;
import robot.RobotMap;
import robot.commands.CommandBase;
import robot.commands.teleop.TeleopWinchCommand;

public class WinchSubsystem extends Subsystem {

    Actuator shooterPiston = new Actuator(new DoubleSolenoid(RobotMap.SHOOTER_ONE, RobotMap.SHOOTER_TWO), true, false);
    Victor vicWinch = new Victor(RobotMap.WINCH_MOTOR);
    DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);
    DigitalInput lightSensor = new DigitalInput(RobotMap.LIGHT_SENSOR);
    long lastPrintTime = 0;
    long lastReleaseTime;

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopWinchCommand());
    }

    public boolean getLimit() {
        return limitSwitch.get() || !lightSensor.get();
    }

    public boolean finishedShooting() {
        long now = System.currentTimeMillis();

        return now - lastReleaseTime > Constants.SHOOTER_RELEASE_DELAY;
    }

    public void requestShot() {
        long now = System.currentTimeMillis();
        if (getLimit() && CommandBase.pickupSubsystem.allowShot()) {
            lastReleaseTime = now;
            shooterPiston.set(true);
        }
    }

    public void update(boolean autoReload) {

        if (finishedShooting() && autoReload) {
            shooterPiston.set(false); //Engage the winch
        }

        double winchSpeed = 0.0;
        long now = System.currentTimeMillis();

        //If we want to auto load, only do it if the limit is not pressed, and the winch is engaged
        if (autoReload) {
            if (now - lastPrintTime > Constants.PRINT_DELAY) {
                lastPrintTime = now;
                System.out.println("Winching...");
            }
            if (!getLimit()) {
                //andymark shifter is positive, engaged state of shooter is true
                winchSpeed = Constants.WINCH_SPEED;

            } else {
                winchSpeed = 0.0;
            }
        } else {
            if (now - lastPrintTime > Constants.PRINT_DELAY) {
                lastPrintTime = now;
                System.out.println("not Winching");
            }
            //Manual mode?
            //winchSpeed = 0.0;
        }

        if (getLimit()) {
            winchSpeed = 0.0;
        }

        vicWinch.set(winchSpeed);
    }

}
