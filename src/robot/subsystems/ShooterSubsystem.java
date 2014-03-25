package robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.commands.CommandBase;
import robot.commands.teleop.TeleopShooterCommand;

public class ShooterSubsystem extends Subsystem {

    boolean shooterArmed;

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopShooterCommand());
    }

    public void update(boolean fireShot) {

        if (fireShot/* && CommandBase.catchSubsystem.allowShot()*/) {
            CommandBase.winchSubsystem.requestShot(); //Disengage the winch
        }

        //If the limit switch is pressed, allow shooter to be armed
        if (CommandBase.winchSubsystem.getLimit() && fireShot) {
            shooterArmed = true;
        } else if (CommandBase.winchSubsystem.finishedShooting()) { //Don't put shooter into disarmed state until the shooterReleaseDelay has happened
            shooterArmed = false;
        }

    }

    public boolean shooterArmed() {
        return shooterArmed;
    }

    public void print() {
        System.out.println("[ShooterSubsystem] **********************************");
        //System.out.println("Acutal limit switch: " + limitSwitch.get());
        //System.out.println("Winch: " + vicWinch.get());
        //System.out.println("Piston: " + getWinchEngaged());
    }
}
