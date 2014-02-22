package templates.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import templates.ArcadeDrive;
import templates.Constants;
import templates.RobotMap;
import templates.Storage;
import templates.commands.TeleopDriveCommand;

public class ChassisSubsystem extends Subsystem {

    Solenoid shifter = new Solenoid(RobotMap.SHIFTER);
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
        //pidLeft.enable();
        //pidRight.enable();
    }

    public void disable() {
        pidLeft.disable();
        pidRight.disable();

        pidLeft.setSetpoint(0);
        pidRight.setSetpoint(0);

        vicLeft.set(0.0);
        vicRight.set(0.0);
    }

    public void drive(double speed, double rotation) {

        if (Math.abs(speed) < 0.075) {
            speed = 0.0;
        }
        if (Math.abs(rotation) < 0.075) {
            rotation = 0.0;
        }

        double leftSetpoint;
        double rightSetpoint;

        arcadeDrive.drive(speed, -rotation);

        leftSetpoint = leftStorage.get();// * Constants.MAX_ENCODER_COUNTS;
        rightSetpoint = -rightStorage.get();// * Constants.MAX_ENCODER_COUNTS;

        vicLeft.set(leftSetpoint);
        vicRight.set(rightSetpoint);

        //pidLeft.setSetpoint(leftSetpoint);
        //pidRight.setSetpoint(rightSetpoint);
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

        System.out.println("[ChassisSubsystem]");
        //System.out.println("Left Speed: " + leftStorage.get());
        //System.out.println("Right Speed: " + rightStorage.get());
        //System.out.println("Left Distance: " + encLeft.getDistance());
        //System.out.println("Right Distance: " + encRight.getDistance());
        System.out.println("Left Encoder Rate " + encLeft.getRate());
        System.out.println("Right Encoder Rate " + encRight.getRate());

    }
}
