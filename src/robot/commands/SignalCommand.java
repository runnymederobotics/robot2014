package robot.commands;

import robot.Toggle;

public class SignalCommand extends CommandBase {

    Toggle possesionToggle = new Toggle(false);

    public SignalCommand() {
        requires(signalSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        possesionToggle.feed(oi.getPossesionToggle());
        signalSubsystem.setPossesion(possesionToggle.get());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
