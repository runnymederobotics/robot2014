package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallDragCommandGroup extends CommandGroup {

    public TwoBallDragCommandGroup() {
        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousDragBallCommand()); //Enable dragging

        addSequential(new AutonomousDelayCommand(750)); //Delay for the pickup to engage the drag ball
        addSequential(new AutonomousRampUpCommand(false));
        addSequential(new AutonomousDriveCommand(93)); //Drive forward 

        addParallel(new AutonomousPickupRollerCommand(false));
        addSequential(new AutonomousDelayCommand(200)); //Delay

        //addSequential(new AutonomousCatchCommand(true));
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching

        addSequential(new AutonomousWaitForReloadCommand()); //Wait until the shooter is loaded again
        addParallel(new AutonomousPickupRollerCommand(true)); //Bring the second ball in
        addSequential(new AutonomousDelayCommand(350));
        addSequential(new AutonomousDriveCommand(2));
        addSequential(new AutonomousDelayCommand(1500)); //Delay for second ball to get in
        addParallel(new AutonomousPickupCommand(true));

        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}
