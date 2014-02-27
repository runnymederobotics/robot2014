package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousParallelCatchCommand extends CommandBase{

    boolean state;
    
    public AutonomousParallelCatchCommand(boolean state){
        requires(catchSubsystem);
        this.state = state;
    }
    
    protected void initialize() {
    }

    protected void execute() {
        catchSubsystem.update(state);
    }

    protected boolean isFinished() {
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
