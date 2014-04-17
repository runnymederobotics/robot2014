package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.Constants;
import robot.commands.CommandBase;

public class AutonomousRampUpCommand extends CommandBase {

    double startTime;
    double speed = 0;
    boolean reverseDirection = false;
    boolean timeToggle = true;

    public AutonomousRampUpCommand(boolean reverseDirection) {
        requires(chassisSubsystem);
        this.reverseDirection = reverseDirection;
    }

    protected void initialize() {
    }

    protected void execute() {

        if (timeToggle) {
            startTime = System.currentTimeMillis();
            timeToggle = false;
        }

        double now = System.currentTimeMillis();
        speed = (now - startTime) / Constants.AUTONOMOUS_RAMPUP_TIME * Constants.AUTONOMOUS_DRIVE_SPEED;

        System.out.println("speed: " + speed);

        if (reverseDirection) {
            speed = -speed;
        }

        chassisSubsystem.shift(false);
        chassisSubsystem.drive(speed, 0.0);
    }

    protected boolean isFinished() {
        return (System.currentTimeMillis() - startTime) >= Constants.AUTONOMOUS_RAMPUP_TIME || !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
        timeToggle = true;
    }

    protected void interrupted() {
        timeToggle = true;
    }

}
