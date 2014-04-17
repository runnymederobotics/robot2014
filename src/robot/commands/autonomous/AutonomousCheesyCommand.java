/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.RobotTemplate;
import robot.commands.CommandBase;

/**
 *
 * @author 1310
 */
public class AutonomousCheesyCommand extends CommandBase {

    long startTime;
    boolean finished;

    public AutonomousCheesyCommand() {
        startTime = System.currentTimeMillis();
    }

    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    protected void execute() {
        finished = RobotTemplate.cheesyVisionServer.getLeftStatus() && RobotTemplate.cheesyVisionServer.getRightStatus();
        System.out.println(finished);
    }

    protected boolean isFinished() {
        return finished || !DriverStation.getInstance().isAutonomous() || System.currentTimeMillis() - startTime > 5000;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
