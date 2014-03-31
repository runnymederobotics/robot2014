package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallDragDriveShotCommandGroup extends CommandGroup {

    public TwoBallDragDriveShotCommandGroup() {

        addParallel(new AutonomousWinchCommand());

        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousDragBallCommand()); //Enable dragging

        addSequential(new AutonomousDelayCommand(750)); //Delay for the pickup to engage the drag ball
        addSequential(new AutonomousRampUpCommand(false));
        addParallel(new AutonomousDriveCommand(80)); //Drive forward 
        
        addSequential(new AutonomousEncoderDelayCommand(75));
        //addSequential(new AutonomousDelayCommand(1500));
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addSequential(new AutonomousDelayCommand(500)); //Delay
        
        addParallel(new AutonomousPickupRollerCommand(false));
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching

        addSequential(new AutonomousWaitForReloadCommand()); //Wait until the shooter is loaded again
        addParallel(new AutonomousPickupRollerCommand(true)); //Bring the second ball in
        addSequential(new AutonomousDelayCommand(350));
        addSequential(new AutonomousDriveCommand(2));
        addSequential(new AutonomousDelayCommand(2000)); //Delay for second ball to get in
        addParallel(new AutonomousPickupCommand(true));

        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}
