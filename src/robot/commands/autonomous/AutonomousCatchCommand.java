package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousCatchCommand extends CommandBase{

    protected void initialize() {
    }

    protected void execute() {
        catchSubsystem.update(false);
    }

    protected boolean isFinished() {
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
