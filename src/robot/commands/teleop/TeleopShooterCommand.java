package robot.commands.teleop;

import edu.wpi.first.wpilibj.DriverStation;
import robot.Toggle;
import robot.commands.CommandBase;

public class TeleopShooterCommand extends CommandBase {

    Toggle toggleWinch = new Toggle(true);

    public TeleopShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        toggleWinch.feed(oi.getWinchToggle());

        shooterSubsystem.update(oi.getShooterFireButton(), toggleWinch.get() && DriverStation.getInstance().getDigitalIn(1));   
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
