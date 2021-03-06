package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Constants;

public class OneBallCommandGroup extends CommandGroup {

    public OneBallCommandGroup() {

        addParallel(new AutonomousWinchCommand());

        addParallel(new AutonomousPickupRollerCommand(false));

        addSequential(new AutonomousDelayCommand(2000));

        addSequential(new AutonomousCheesyCommand());

        addSequential(new AutonomousRampUpCommand(false));
        addParallel(new AutonomousDriveCommand(93, Constants.AUTONOMOUS_DRIVE_SPEED)); //Drive forward 
        addSequential(new AutonomousEncoderDelayCommand(83)); //Delay

        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command

        addSequential(new AutonomousPickupCommand(false));
        addSequential(new AutonomousDriveCommand(-141, Constants.AUTONOMOUS_DRIVE_SPEED));
    }
}
