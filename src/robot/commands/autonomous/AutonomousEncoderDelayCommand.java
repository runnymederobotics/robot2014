package robot.commands.autonomous;

import robot.Constants;
import robot.commands.CommandBase;

public class AutonomousEncoderDelayCommand extends CommandBase {

    double encoderCounts;

    public AutonomousEncoderDelayCommand(double distanceInInches) {
        encoderCounts = distanceInInches * Constants.ENCODER_COUNTS_PER_INCH;
    }

    protected void initialize() {
        chassisSubsystem.reset();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(chassisSubsystem.getEncoderCounts()) >= Math.abs(encoderCounts);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
