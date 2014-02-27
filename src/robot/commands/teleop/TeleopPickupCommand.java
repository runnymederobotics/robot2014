package robot.commands.teleop;

import robot.commands.CommandBase;

public class TeleopPickupCommand extends CommandBase {

    public TeleopPickupCommand() {
        requires(pickupSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {

        boolean requestDeploy = oi.getLowerPickupButton() || oi.getDeployCatchButton();
        boolean requestRoll = oi.getRunPickupButton();
        boolean requestReverse = oi.getPickupReverseButton();

        pickupSubsystem.updatePistonState(requestDeploy);
        pickupSubsystem.updateRoller(requestRoll, requestReverse, false);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
