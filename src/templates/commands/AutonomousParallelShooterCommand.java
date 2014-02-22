package templates.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class AutonomousParallelShooterCommand extends CommandBase {

    public AutonomousParallelShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        //Don't fire, auto winch
        shooterSubsystem.update(false, true);
    }

    protected boolean isFinished() {
        //If we're in autonomous, then we're not finished
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
