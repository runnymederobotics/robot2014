package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousShootCommand extends CommandBase {

    public AutonomousShootCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (DriverStation.getInstance().isAutonomous()) {
            shooterSubsystem.update(true); //Shoot and auto winch
        } else {
            shooterSubsystem.update(false); //Don't shoot and auto winch
        }
    }

    protected boolean isFinished() {
        //We've shot when the shooter is no longer armed
        return !shooterSubsystem.shooterArmed() || !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
