/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.commands.teleop;

import robot.commands.CommandBase;

/**
 *
 * @author 1310
 */
public class TeleopCatchCommand extends CommandBase {

    public TeleopCatchCommand() {
        requires(catchSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        catchSubsystem.update(oi.getDeployCatchButton());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
