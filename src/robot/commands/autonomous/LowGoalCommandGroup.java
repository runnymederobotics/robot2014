package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Constants;

public class LowGoalCommandGroup extends CommandGroup {

    public LowGoalCommandGroup() {

        //addParallel(new AutonomousWinchCommand());

        //addSequential(new AutonomousRampUpCommand(false));
        addSequential(new AutonomousDriveCommand(100, Constants.AUTONOMOUS_DRIVE_SPEED));
        //addParallel(new AutonomousPickupRollerReverseCommand());
        //addSequential(new AutonomousDelayCommand(1000));
        //addParallel(new AutonomousParallelPickupCommand());
    }
}
