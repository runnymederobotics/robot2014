package templates.commands;

public class TeleopDriveCommand extends CommandBase {

    public TeleopDriveCommand() {
        requires(chassisSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {

        chassisSubsystem.drive(oi.getDriveAxis(), oi.getRotationAxis());
        chassisSubsystem.shift(oi.getShifterButton());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
