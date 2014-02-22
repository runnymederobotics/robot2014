/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author 1310
 */
public class AutonomousCommandGroup extends CommandGroup {

    public AutonomousCommandGroup() {
        addParallel(new AutonomousParallelShooterCommand()); //Enable automatic winching
        addParallel(new AutonomousParallelPickupCommand()); //Allow pickup to control itself for "armed" state
        addSequential(new AutonomousDriveCommand(72)); //Drive forward 72 inches
        addSequential(new AutonomousShootCommand()); //Shoot, which stops the automatic winch command
        addParallel(new AutonomousParallelShooterCommand()); //Go back to automatic winching
    }
}
