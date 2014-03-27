package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousWinchCommand extends CommandBase{
    
    public AutonomousWinchCommand(){
        requires(winchSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        winchSubsystem.update(DriverStation.getInstance().getDigitalIn(1));
    }

    protected boolean isFinished() {
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    
}
