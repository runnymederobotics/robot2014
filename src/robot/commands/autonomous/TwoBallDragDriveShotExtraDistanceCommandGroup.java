package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallDragDriveShotExtraDistanceCommandGroup extends CommandGroup {

    public TwoBallDragDriveShotExtraDistanceCommandGroup() {

        addParallel(new AutonomousWinchCommand());

        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousDragBallCommand()); //Enable dragging

        addSequential(new AutonomousDelayCommand(750)); //Delay for the pickup to engage the drag ball
        addSequential(new AutonomousRampUpCommand(false));
        addParallel(new AutonomousDriveCommand(93)); //Drive forward 
        
        addSequential(new AutonomousEncoderDelayCommand(83));
        //addSequential(new AutonomousDelayCommand(1500));
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addSequential(new AutonomousDelayCommand(500)); //Delay
        
        addParallel(new AutonomousPickupRollerCommand(false));
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching

        addSequential(new AutonomousWaitForReloadCommand()); //Wait until the shooter is loaded again
        addParallel(new AutonomousPickupRollerCommand(true)); //Bring the second ball in
        addSequential(new AutonomousDelayCommand(350));
        addParallel(new AutonomousDriveCommand(48));
        addSequential(new AutonomousDelayCommand(3500)); //Delay for second ball to get in
        addParallel(new AutonomousPickupCommand(true));

        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}
