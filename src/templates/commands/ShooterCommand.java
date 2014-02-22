package templates.commands;

import templates.Toggle;

public class ShooterCommand extends CommandBase {

    Toggle toggleWinch = new Toggle(false);
    
    public ShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        toggleWinch.feed(oi.getWinchToggle());
        
        shooterSubsystem.update(oi.getShooterFireButton(), toggleWinch.getValue());
        System.out.println(toggleWinch.getValue());
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
