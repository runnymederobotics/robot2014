package robot.commands.teleop;

import robot.commands.CommandBase;

public class TeleopShooterCommand extends CommandBase {

    public TeleopShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        shooterSubsystem.update(oi.getShooterFireButton());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
