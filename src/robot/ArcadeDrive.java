package robot;

import edu.wpi.first.wpilibj.SpeedController;

public class ArcadeDrive {

    SpeedController leftMotor;
    SpeedController rightMotor;
    double leftSpeed;
    double rightSpeed;

    public ArcadeDrive(SpeedController leftMotor, SpeedController rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    public void drive(double driveAxis, double rotationAxis) {

        leftSpeed = driveAxis + rotationAxis;
        rightSpeed = driveAxis - rotationAxis;

        int leftSign = 1, rightSign = 1;

        if (leftSpeed < 0.0) {
            leftSign = -1;
        }

        if (rightSpeed < 0.0) {
            rightSign = -1;
        }

        leftSpeed = Math.abs(leftSpeed);
        rightSpeed = Math.abs(rightSpeed);

        if (leftSpeed > 1.0 && rightSpeed != 0.0) {
            double ratio = leftSpeed / rightSpeed;
            leftSpeed = 1.0;
            rightSpeed = 1.0 / ratio;
        } else if (rightSpeed > 1.0 && leftSpeed != 0.0) {
            double ratio = rightSpeed / leftSpeed;
            leftSpeed = 1.0 / ratio;
            rightSpeed = 1.0;
        }

        leftSpeed *= leftSign;
        rightSpeed *= rightSign;

        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);

    }
}
