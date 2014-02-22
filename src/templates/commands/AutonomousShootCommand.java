package templates.commands;

public class AutonomousShootCommand extends CommandBase {
    public AutonomousShootCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        shooterSubsystem.update(true, true);
    }

    protected boolean isFinished() {
        //We've shot when the shooter is no longer armed
        return !shooterSubsystem.shooterArmed();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
