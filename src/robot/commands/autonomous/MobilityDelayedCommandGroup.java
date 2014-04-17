/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.Constants;

/**
 *
 * @author 1310
 */
public class MobilityDelayedCommandGroup extends CommandGroup {

    public MobilityDelayedCommandGroup() {
        addSequential(new AutonomousDelayCommand(6000));
        addSequential(new AutonomousDriveCommand(124, Constants.AUTONOMOUS_DRIVE_SPEED));

    }
}
