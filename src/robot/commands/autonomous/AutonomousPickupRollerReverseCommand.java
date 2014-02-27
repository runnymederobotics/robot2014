package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousPickupRollerReverseCommand extends CommandBase {
    public AutonomousPickupRollerReverseCommand() {
        requires(pickupSubsystem);
    }

    protected void initialize() {
        pickupSubsystem.updatePistonState(false);
    }

    protected void execute() {
        pickupSubsystem.updateRoller(false, true, false);
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
