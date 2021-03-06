package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousPickupRollerCommand extends CommandBase {
    
    boolean requestRoll;
    
    public AutonomousPickupRollerCommand(boolean requestRoll) {
        requires(pickupSubsystem);
        this.requestRoll = requestRoll;
    }

    protected void initialize() {
        pickupSubsystem.updatePistonState(false);
    }

    protected void execute() {
        pickupSubsystem.updatePistonState(true);
        pickupSubsystem.updateRoller(requestRoll, false, false);
    }

    protected boolean isFinished() {
        //We've shot when the shooter is no longer armed
        return !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
