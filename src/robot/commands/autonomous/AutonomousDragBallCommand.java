package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousDragBallCommand extends CommandBase {

    public AutonomousDragBallCommand(){
        requires(pickupSubsystem);
    }
    
    protected void initialize() {
        pickupSubsystem.updatePistonState(false);
    }

    protected void execute() {
        pickupSubsystem.updatePistonState(true);
        pickupSubsystem.updateRoller(false, false, true);
    }

    protected boolean isFinished() {
        //We're finished if we're not in autonomous
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
