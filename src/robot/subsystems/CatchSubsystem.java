package robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Actuator;
import robot.RobotMap;
import robot.commands.teleop.TeleopDriveCommand;

public class CatchSubsystem extends Subsystem {

    Actuator catchPiston = new Actuator(new DoubleSolenoid(RobotMap.CATCH_PISTON_ONE, RobotMap.CATCH_PISTON_TWO), false, false);

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopDriveCommand());
    }

    public void update(boolean requestDeploy) {
        catchPiston.set(requestDeploy);
    }
}
