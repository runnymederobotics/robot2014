package templates.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class AutonomousShootCommand extends CommandBase {

    public AutonomousShootCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (DriverStation.getInstance().isAutonomous()) {
            shooterSubsystem.update(true, true); //Shoot and auto winch
        } else {
            shooterSubsystem.update(false, true); //Don't shoot and auto winch
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
