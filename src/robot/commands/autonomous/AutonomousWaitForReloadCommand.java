package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousWaitForReloadCommand extends CommandBase {

    public AutonomousWaitForReloadCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        shooterSubsystem.update(false);
    }

    protected boolean isFinished() {
        //When we've waited long enough and the limit is now pressed. This means it has shot and fully retracted again
        return (winchSubsystem.finishedShooting() && winchSubsystem.getLimit()) || !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
