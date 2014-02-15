package templates.commands;

public class ShooterCommand extends CommandBase {

    public ShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        shooterSubsystem.update(oi.getShooterArmButton(), oi.getShooterFireButton());
    }

    public static boolean getLimit() {
        return shooterSubsystem.getLimit();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
