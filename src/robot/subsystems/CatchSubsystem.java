package robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Actuator;
import robot.Constants;
import robot.RobotMap;
import robot.commands.CommandBase;
import robot.commands.teleop.TeleopCatchCommand;

public class CatchSubsystem extends Subsystem {

    Actuator catchPiston = new Actuator(new Solenoid(RobotMap.CATCH_PISTON), false, false);
    long lastTimeDeployed;

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopCatchCommand());
    }

    public void update(boolean requestDeploy) {

        if (CommandBase.shooterSubsystem.shooterArmed() && CommandBase.pickupSubsystem.allowShot()) {
            requestDeploy = true;
        }

        if (!requestDeploy) {
            lastTimeDeployed = System.currentTimeMillis();
        }

        catchPiston.set(requestDeploy);
    }

    public boolean allowShot() {
        return System.currentTimeMillis() - lastTimeDeployed > Constants.CATCH_DEPLOY_TIME;
    }
}
