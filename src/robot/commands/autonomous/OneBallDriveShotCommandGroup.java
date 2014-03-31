package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneBallDriveShotCommandGroup extends CommandGroup {

    public OneBallDriveShotCommandGroup() {
        addParallel(new AutonomousWinchCommand());
        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        
        addSequential(new AutonomousRampUpCommand(false));
        addParallel(new AutonomousDriveCommand(80));
        
        addSequential(new AutonomousEncoderDelayCommand(70));
        //addSequential(new AutonomousDelayCommand(1500));
        addSequential(new AutonomousShootCommand());
    }
}
