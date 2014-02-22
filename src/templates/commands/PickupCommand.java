package templates.commands;

public class PickupCommand extends CommandBase {

    public PickupCommand() {
        requires(pickupSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {

        boolean requestDeploy = oi.getLowerPickupButton();
        boolean requestPickup = oi.getPickupButton();
        boolean requestRoll = oi.getRunPickupButton();
        boolean requestReverse = oi.getPickupReverseButton();

        pickupSubsystem.updatePistonState(requestPickup || requestDeploy);
        pickupSubsystem.updateRoller(requestRoll || requestPickup, requestReverse, false);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
