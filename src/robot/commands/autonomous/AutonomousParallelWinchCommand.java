package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousParallelWinchCommand extends CommandBase{
    
    public AutonomousParallelWinchCommand(){
        requires(winchSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        winchSubsystem.update(true);
    }

    protected boolean isFinished() {
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
