package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.ArcadeDrive;
import edu.wpi.first.wpilibj.templates.Constants;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.Storage;
import edu.wpi.first.wpilibj.templates.commands.TeleopDriveCommand;

public class ChassisSubsystem extends Subsystem {

    Solenoid shifter = new Solenoid(RobotMap.SHIFTER);
    Encoder encLeft = new Encoder(RobotMap.ENC_LEFT_ONE, RobotMap.ENC_LEFT_TWO, true);
    Encoder encRight = new Encoder(RobotMap.ENC_RIGHT_ONE, RobotMap.ENC_RIGHT_TWO, true);
    Victor vicLeft = new Victor(RobotMap.LEFT_MOTOR);
    Victor vicRight = new Victor(RobotMap.RIGHT_MOTOR);
    Storage leftStorage = new Storage();
    Storage rightStorage = new Storage();
    PIDController leftPID = new PIDController(0.0, 0.0, 0.0, encLeft, vicLeft);
    PIDController rightPID = new PIDController(0.0, 0.0, 0.0, encRight, vicRight);
    ArcadeDrive arcadeDrive = new ArcadeDrive(leftStorage, rightStorage);

    public ChassisSubsystem() {
        encLeft.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        encRight.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);

        encLeft.start();
        encRight.start();

        leftPID.setInputRange(-Constants.MAX_ENCODER_COUNTS, Constants.MAX_ENCODER_COUNTS);
        rightPID.setInputRange(-Constants.MAX_ENCODER_COUNTS, Constants.MAX_ENCODER_COUNTS);

        leftPID.setOutputRange(-1.0, 1.0);
        rightPID.setOutputRange(-1.0, 1.0);
    }

    public void enable() {
        //leftPID.enable();
        //rightPID.enable();
    }

    public void disable() {
        leftPID.disable();
        rightPID.disable();

        leftPID.setSetpoint(0);
        rightPID.setSetpoint(0);

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

        leftSetpoint = leftStorage.get();
        rightSetpoint = rightStorage.get();

        vicLeft.set(leftSetpoint);
        vicRight.set(-rightSetpoint);

        //leftPID.setSetpoint(leftSetpoint * RobotMap.MAX_ENCODER_COUNTS);
        //rightPID.setSetpoint(rightSetpoint * RobotMap.MAX_ENCODER_COUNTS);
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

        return (encLeftDistance - encRightDistance) / 2;

    }

    public void reset() {
        encLeft.reset();
        encRight.reset();
    }

    public void print() {

        System.out.println("[ChassisSubsystem]");
        System.out.println("Left Speed: " + leftStorage.get());
        System.out.println("Right Speed: " + rightStorage.get());
        System.out.println("Left Distance: " + encLeft.getDistance());
        System.out.println("Right Distance: " + encRight.getDistance());

    }
}
