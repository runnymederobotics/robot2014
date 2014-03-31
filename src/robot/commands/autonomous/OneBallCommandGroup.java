package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneBallCommandGroup extends CommandGroup {

    public OneBallCommandGroup() {

        addParallel(new AutonomousWinchCommand());

        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousParallelPickupCommand()); //Allow pickup to control itself for "armed" state
        addSequential(new AutonomousPickupCommand(true));
        addParallel(new AutonomousPickupRollerCommand(false));
        addSequential(new AutonomousRampUpCommand(false));
        addSequential(new AutonomousDriveCommand(98)); //Drive forward
        addSequential(new AutonomousPickupCommand(false));
        addSequential(new AutonomousDelayCommand(750)); //Delay
        addSequential(new AutonomousPickupCommand(true));
        addSequential(new AutonomousDelayCommand(750)); //Delay
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}
