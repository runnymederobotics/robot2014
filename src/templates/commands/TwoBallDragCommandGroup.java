package templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallDragCommandGroup extends CommandGroup {

    public TwoBallDragCommandGroup() {
        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousDragBallCommand()); //Enable dragging
        
        addSequential(new AutonomousDelayCommand(1000)); //Delay for the pickup to engage the drag ball
        addSequential(new AutonomousDriveCommand(120)); //Drive forward
        addSequential(new AutonomousDelayCommand(1000)); //Delay
        
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
        
        addSequential(new AutonomousWaitForReloadCommand()); //Wait until the shooter is loaded again
        addParallel(new AutonomousPickupRollerCommand()); //Bring the second ball in
        addSequential(new AutonomousDelayCommand(1000)); //Delay for second ball to get in
        addParallel(new AutonomousPickupCommand(true));
        
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}