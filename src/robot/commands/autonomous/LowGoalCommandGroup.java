package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowGoalCommandGroup extends CommandGroup{
    
    public LowGoalCommandGroup(){
        addSequential(new AutonomousDriveCommand(-180));
        addParallel(new AutonomousPickupRollerReverseCommand());
        addSequential(new AutonomousDelayCommand(1000));
        addParallel(new AutonomousParallelPickupCommand());
    }
    
}
