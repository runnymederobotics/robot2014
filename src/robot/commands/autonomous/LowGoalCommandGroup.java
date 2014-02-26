package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowGoalCommandGroup extends CommandGroup{
    
    public LowGoalCommandGroup(){
        addSequential(new AutonomousDriveCommand(120));
        addSequential(new AutonomousPickupRollerReverseCommand());
    }
    
}
