package robot.commands.teleop;

import edu.wpi.first.wpilibj.DriverStation;
import robot.Toggle;
import robot.commands.CommandBase;

public class TeleopWinchCommand extends CommandBase {

    Toggle toggleWinch = new Toggle(true);

    public TeleopWinchCommand() {
        requires(winchSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        toggleWinch.feed(oi.getWinchToggle());

        winchSubsystem.update(toggleWinch.get() && DriverStation.getInstance().getDigitalIn(1));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
