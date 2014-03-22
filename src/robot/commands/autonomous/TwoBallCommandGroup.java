package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallCommandGroup extends CommandGroup {
    public TwoBallCommandGroup(){
        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousParallelPickupCommand()); //Allow pickup to control itself for "armed" state
        addSequential(new AutonomousDriveCommand(120)); //Drive forward
        addSequential(new AutonomousDelayCommand(1000)); //Delay
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
        
        addSequential(new AutonomousRampUpCommand(false));
        addSequential(new AutonomousDriveCommand(-120));
        addParallel(new AutonomousPickupRollerCommand(true));
        addSequential(new AutonomousPickupCommand(true));
        addSequential(new AutonomousShootCommand());
        addParallel(new AutonomousParallelShooterCommand());
    }
}
