package robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.ArcadeDrive;
import robot.Constants;
import robot.Actuator;
import robot.RobotMap;
import robot.Storage;
import robot.commands.teleop.TeleopDriveCommand;

public class ChassisSubsystem extends Subsystem {

    Actuator shifter = new Actuator(new Solenoid(RobotMap.SHIFTER), false, false);
    Encoder encLeft = new Encoder(RobotMap.ENC_LEFT_ONE, RobotMap.ENC_LEFT_TWO, true);
    Encoder encRight = new Encoder(RobotMap.ENC_RIGHT_ONE, RobotMap.ENC_RIGHT_TWO, false);
    Victor vicLeft = new Victor(RobotMap.LEFT_MOTOR);
    Victor vicRight = new Victor(RobotMap.RIGHT_MOTOR);
    Storage leftStorage = new Storage();
    Storage rightStorage = new Storage();
    PIDController pidLeft = new PIDController(0.0005, 0.0, 0.0, encLeft, vicLeft);
    PIDController pidRight = new PIDController(0.0005, 0.0, 0.0, encRight, vicRight);
    ArcadeDrive arcadeDrive = new ArcadeDrive(leftStorage, rightStorage);

    public ChassisSubsystem() {
        encLeft.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        encRight.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);

        encLeft.start();
        encRight.start();

        pidLeft.setInputRange(-Constants.MAX_ENCODER_COUNTS, Constants.MAX_ENCODER_COUNTS);
        pidRight.setInputRange(-Constants.MAX_ENCODER_COUNTS, Constants.MAX_ENCODER_COUNTS);

        pidLeft.setOutputRange(-1.0, 1.0);
        pidRight.setOutputRange(-1.0, 1.0);
    }

    public void enable() {
        //enablePID();
    }

    public void disable() {
        disablePID();

        pidLeft.setSetpoint(0);
        pidRight.setSetpoint(0);

        vicLeft.set(0.0);
        vicRight.set(0.0);
    }

    public void disablePID() {
        if (pidLeft.isEnable()) {
            pidLeft.disable();
        }
        if (pidRight.isEnable()) {
            pidRight.disable();
        }
    }

    public void enablePID() {
        if (!pidLeft.isEnable()) {
            pidLeft.enable();
        }
        if (!pidRight.isEnable()) {
            pidRight.enable();
        }
    }

    public void drive(double speed, double rotation) {

        if (Math.abs(speed) < 0.075) {
            speed = 0.0;
        }
        if (Math.abs(rotation) < 0.075) {
            rotation = 0.0;
        }

        arcadeDrive.drive(speed, -rotation);

        double leftSpeed = leftStorage.get();
        double rightSpeed = -rightStorage.get();

        if (!pidLeft.isEnable() && !pidRight.isEnable()) {
            vicLeft.set(leftSpeed);
            vicRight.set(rightSpeed);
        } else if (pidLeft.isEnable() && pidRight.isEnable()) {
            pidLeft.setSetpoint(leftSpeed * Constants.MAX_ENCODER_COUNTS);
            pidRight.setSetpoint(rightSpeed * Constants.MAX_ENCODER_COUNTS);
        } else {
            disablePID();
        }
    }

    public void shift(boolean state) {
        shifter.set(state);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopDriveCommand());
    }

    public double getEncoderCounts() {

        double encLeftDistance = encLeft.getDistance();
        double encRightDistance = encRight.getDistance();

        return (encLeftDistance + encRightDistance) / 2;
    }

    public void reset() {
        encLeft.reset();
        encRight.reset();
    }

    public void print() {

        System.out.println("[ChassisSubsystem] **********************************");
        //System.out.println("Left Speed: " + leftStorage.get());
        //System.out.println("Right Speed: " + rightStorage.get());
        //System.out.println("Left Distance: " + encLeft.getDistance());
        //System.out.println("Right Distance: " + encRight.getDistance());
        System.out.println("Left Encoder Rate " + encLeft.getRate());
        System.out.println("Right Encoder Rate " + encRight.getRate());

    }
}
