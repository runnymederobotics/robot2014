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
    Victor winch = new Victor(RobotMap.WINCH_MOTOR);
    DigitalInput limit = new DigitalInput(RobotMap.LIMIT_SWITCH);
    boolean shooterArmed;
    long lastReleaseTime;
    boolean autoLoad = true;
    long winchEngagedTime;

    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterCommand());
    }

    //The shooter is pulled back if the limit is true
    public boolean getLimit() {
        return limit.get();
    }

    public void setPistonState(boolean state) {

        if (!state) {
            shooterPiston.set(DoubleSolenoid.Value.kForward);
        } else {
            shooterPiston.set(DoubleSolenoid.Value.kReverse);
        }

    }

    public boolean getWinchEngaged() {
        return shooterPiston.get() == DoubleSolenoid.Value.kForward;
    }

    public void update(boolean armShooter, boolean fireShot) {
        //Release the piston for the winch if:
        //The pickup subsystem says its okay
        //The limit switch is pressed
        //requestShot is true

        long now = System.currentTimeMillis();

        boolean shooterReleaseDelay = now - lastReleaseTime > Constants.SHOOTER_RELEASE_DELAY;

        if (getLimit() && CommandBase.pickupSubsystem.allowShot() && armShooter && fireShot) {
            lastReleaseTime = now;
            setPistonState(false); //Disengage the winch
        } else if (shooterReleaseDelay) {
            setPistonState(true); //Engage the winch
        }
        
        //If the limit switch is pressed, allow shooter to be armed
        if (getLimit() && armShooter) {
            shooterArmed = true;
        } else if (shooterReleaseDelay) { //Don't put shooter into disarmed state until the shooterReleaseDelay has happened
            shooterArmed = false;
        }
        
        double winchSpeed = 0.0;

        //If we want to auto load, only do it if the limit is not pressed, and the winch is engaged
        if (autoLoad) {
            if (!getLimit()) {
                winchSpeed = -Constants.WINCH_SPEED;
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
        
        winch.set(winchSpeed);
    }

    public boolean getShooterArmed() {
        return shooterArmed;
    }

    public void print() {
        System.out.println("[ShooterSubsystem]");
        System.out.println("Acutal limit switch: " + limit.get());
        System.out.println("Winch: " + winch.get());
        System.out.println("Piston: " + getWinchEngaged());
    }
}
