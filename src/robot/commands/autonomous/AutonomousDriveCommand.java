package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.Constants;
import robot.commands.CommandBase;

public class AutonomousDriveCommand extends CommandBase {

    double encoderCounts;

    public AutonomousDriveCommand(double distanceInInches) {
        requires(chassisSubsystem);
        
        encoderCounts = distanceInInches * Constants.ENCODER_COUNTS_PER_INCH;
    }

    protected void initialize() {
        chassisSubsystem.reset();
        chassisSubsystem.disablePID();
    }

    protected void execute() {
        int direction = 1;
        if (encoderCounts < 0.0) {
            direction = -1;
        }
        
        chassisSubsystem.shift(false);
        
        chassisSubsystem.drive(Constants.AUTONOMOUS_DRIVE_SPEED * direction, 0.0);
    }

    protected boolean isFinished() {
        
        if(!DriverStation.getInstance().isAutonomous()){
            return true;
        }
        
        if (Math.abs(chassisSubsystem.getEncoderCounts()) < Math.abs(encoderCounts)) {
            return false;
        } else {
            return true;
        }
    }

    protected void end() {
        chassisSubsystem.drive(0.0, 0.0);
    }

    protected void interrupted() {
        end();
    }
}
