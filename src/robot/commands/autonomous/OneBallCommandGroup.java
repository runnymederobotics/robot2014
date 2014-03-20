package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneBallCommandGroup extends CommandGroup {

    public OneBallCommandGroup() {
        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousParallelPickupCommand()); //Allow pickup to control itself for "armed" state
        addSequential(new AutonomousPickupCommand(true));
        addSequential(new AutonomousDriveCommand(93)); //Drive forward
        addSequential(new AutonomousDelayCommand(200)); //Delay
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}
