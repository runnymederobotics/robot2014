package templates.commands;

public class PickupCommand extends CommandBase {

    public PickupCommand() {
        requires(pickupSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {

        boolean requestDeploy = oi.getPickupButton();
        boolean requestReverse = oi.getPickupReverseButton();

        pickupSubsystem.updatePistonState(requestDeploy);
        pickupSubsystem.updateRoller(requestDeploy, requestReverse);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
