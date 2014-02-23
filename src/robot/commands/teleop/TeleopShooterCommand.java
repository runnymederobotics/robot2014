package robot.commands.teleop;

import robot.Toggle;
import robot.commands.CommandBase;

public class TeleopShooterCommand extends CommandBase {

    Toggle toggleWinch = new Toggle(false);
    
    public TeleopShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        toggleWinch.feed(oi.getWinchToggle());
        
        shooterSubsystem.update(oi.getShooterFireButton(), toggleWinch.get());
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
