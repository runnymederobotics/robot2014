package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousPickupRollerCommand extends CommandBase {
    public AutonomousPickupRollerCommand() {
        requires(pickupSubsystem);
    }

    protected void initialize() {
        pickupSubsystem.updatePistonState(false);
    }

    protected void execute() {
        pickupSubsystem.updatePistonState(true);
        pickupSubsystem.updateRoller(true, false, false);
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
