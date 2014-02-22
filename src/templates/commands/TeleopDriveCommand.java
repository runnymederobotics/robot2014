package templates.commands;

import templates.Toggle;

public class TeleopDriveCommand extends CommandBase {

    Toggle pidToggle = new Toggle(false);

    public TeleopDriveCommand() {
        requires(chassisSubsystem);
    }

    protected void initialize() {
        chassisSubsystem.disablePID();
    }

    protected void execute() {
        pidToggle.feed(oi.getTogglePID());

        if (pidToggle.get()) {
            chassisSubsystem.enablePID();
        } else {
            chassisSubsystem.disablePID();
        }

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
