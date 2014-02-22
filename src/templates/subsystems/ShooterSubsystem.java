package templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import templates.Constants;
import templates.RobotMap;
import templates.commands.CommandBase;
import templates.commands.ShooterCommand;

public class ShooterSubsystem extends Subsystem {

    DoubleSolenoid shooterPiston = new DoubleSolenoid(RobotMap.SHOOTER_ONE, RobotMap.SHOOTER_TWO);
    Victor vicWinch = new Victor(RobotMap.WINCH_MOTOR);
    DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);
    boolean shooterArmed;
    long lastReleaseTime; //Records the time of the last shot

    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterCommand());
    }

    //The shooter is pulled back if the limit is true
    public boolean getLimit() {
        return limitSwitch.get();
    }

    public void setPistonState(boolean state) {
        if (state) {
            shooterPiston.set(DoubleSolenoid.Value.kForward);
        } else {
            shooterPiston.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public boolean getWinchEngaged() {
        return shooterPiston.get() == DoubleSolenoid.Value.kForward;
    }

    public void update(boolean fireShot, boolean autoReload) {
        //Release the piston for the winch if:
        //The pickup subsystem says its okay
        //The limit switch is pressed
        //requestShot is true

        long now = System.currentTimeMillis();

        boolean shooterReleaseDelay = finishedShooting();

        if (getLimit() && CommandBase.pickupSubsystem.allowShot() && fireShot) {
            lastReleaseTime = now;
            setPistonState(true); //Disengage the winch
        } else if (shooterReleaseDelay && autoReload) {
            //lastReleaseTime = now;
            setPistonState(false); //Engage the winch
        }
        
        //If the limit switch is pressed, allow shooter to be armed
        if (getLimit() && fireShot) {
            shooterArmed = true;
        } else if (shooterReleaseDelay) { //Don't put shooter into disarmed state until the shooterReleaseDelay has happened
            shooterArmed = false;
        }
        
        double winchSpeed = 0.0;

        //If we want to auto load, only do it if the limit is not pressed, and the winch is engaged
        if (autoReload) {
            if (!getLimit()) {
                //andymark shifter is positive, engaged state of shooter is true
                winchSpeed = Constants.WINCH_SPEED;
            } else {
                winchSpeed = 0.0;
            }
        } else {
            //Manual mode?
            //winchSpeed = 0.0;
        }

        if (getLimit()) {
            winchSpeed = 0.0;
        }
        
        vicWinch.set(winchSpeed);
    }
    
    public boolean finishedShooting() {
        long now = System.currentTimeMillis();
        
        return now - lastReleaseTime > Constants.SHOOTER_RELEASE_DELAY;
    }

    public boolean shooterArmed() {
        return shooterArmed;
    }

    public void print() {
        //System.out.println("[ShooterSubsystem]");
        //System.out.println("Acutal limit switch: " + limitSwitch.get());
        //System.out.println("Winch: " + vicWinch.get());
        //System.out.println("Piston: " + getWinchEngaged());
    }
}
