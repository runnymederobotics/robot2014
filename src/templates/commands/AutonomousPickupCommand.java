package templates.commands;

public class AutonomousPickupCommand extends CommandBase{
    
    boolean state;
    
    public AutonomousPickupCommand(boolean state){
        requires(pickupSubsystem);
        
        this.state = state;
    }
    
    protected void initialize() {
    }

    protected void execute() {
        //Constantly update the state of the pickup in case we want to shoot (shooting will put us into "armed" state
        pickupSubsystem.updatePistonState(state);
    }

    protected boolean isFinished() {
        //If the pickup is in the desired state then we're finished
        return pickupSubsystem.pickupDown() == state;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}