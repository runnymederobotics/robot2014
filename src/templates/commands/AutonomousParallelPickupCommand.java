package templates.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class AutonomousParallelPickupCommand extends CommandBase {

    public AutonomousParallelPickupCommand() {
        requires(pickupSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        //Constantly update the state of the pickup in case we want to shoot (shooting will put us into "armed" state)
        pickupSubsystem.updatePistonState(false);
        pickupSubsystem.updateRoller(false, false, false); //Make sure the rollers run if they want to
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
