/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author 1310
 */
public class AutonomousCommandGroup extends CommandGroup {
    public AutonomousCommandGroup() {
        addSequential(new AutonomousDriveCommand(72));
    }
}
