package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Constants;

public class OneBallCheesyShotCommandGroup extends CommandGroup {

    public OneBallCheesyShotCommandGroup() {

        addParallel(new AutonomousWinchCommand());

        addParallel(new AutonomousPickupRollerCommand(false));

        addSequential(new AutonomousDelayCommand(2000));

        addSequential(new AutonomousCheesyCommand());

        addSequential(new AutonomousRampUpCommand(false));
        addParallel(new AutonomousDriveCommand(93, Constants.AUTONOMOUS_DRIVE_SPEED)); //Drive forward 
        addSequential(new AutonomousEncoderDelayCommand(83)); //Delay

        addSequential(new AutonomousCheesyShootCommand()); //Shoot, which stops the automatic winch command
    }
}
