package robot.commands.autonomous;

import robot.commands.CommandBase;

public class AutonomousCatchCommand extends CommandBase{

    boolean state;
    
    public AutonomousCatchCommand(boolean state){
        requires(catchSubsystem);
        this.state = state;
    }
    
    protected void initialize() {
    }

    protected void execute() {
        catchSubsystem.update(state);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}