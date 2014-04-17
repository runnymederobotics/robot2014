package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.RobotTemplate;
import robot.commands.CommandBase;

public class AutonomousCheesyShootCommand extends CommandBase {

    public AutonomousCheesyShootCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (RobotTemplate.cheesyVisionServer.getLeftStatus() && RobotTemplate.cheesyVisionServer.getRightStatus()) {
            if (DriverStation.getInstance().isAutonomous()) {
                shooterSubsystem.update(true); //Shoot and auto winch
            } else {
                shooterSubsystem.update(false); //Don't shoot and auto winch
            }
        }
    }

    protected boolean isFinished() {
        //We've shot when the shooter is no longer armed
        return !winchSubsystem.getLimit() || !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
