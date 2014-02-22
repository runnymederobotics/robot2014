package templates.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import templates.Constants;
import templates.Pneumatic;
import templates.RobotMap;
import templates.commands.CommandBase;
import templates.commands.PickupCommand;

public class PickupSubsystem extends Subsystem {

    Victor vicPickup = new Victor(RobotMap.PICKUP_MOTOR);
    Pneumatic pickupPiston = new Pneumatic(new DoubleSolenoid(RobotMap.PICKUP_ONE, RobotMap.PICKUP_TWO), false, false);
    long lastRetractedTime = 0;

    public boolean pickupDown() {
        return pickupPiston.get();
    }

    public void updatePistonState(boolean requestDeploy) {
        long now = System.currentTimeMillis();

        //Loading or shooting or winching or ready?
        //Determine whether or not the shooter is "armed"
        boolean shooterArmed = CommandBase.shooterSubsystem.shooterArmed();

        //If we're not deployed and we want to be
        //We want to deploy only if we request it or if the shooter is in the loaded state
        if (shooterArmed || requestDeploy) {
            pickupPiston.set(true); //Deploy the pickup
        } else {
            //Record the time we were last in the retracted state
            lastRetractedTime = now;
            pickupPiston.set(false); //Retract the pickup
        }
    }

    public void updateRoller(boolean runForward, boolean runReverse) {

        long now = System.currentTimeMillis();

        double pickupSpeed = 0.0;

        //If the pickup has been out for less than Constants.PICKUP_DEPLOY_ROLLER_TIME
        if (pickupDown() && now - lastRetractedTime < Constants.PICKUP_DEPLOY_ROLLER_TIME) {
            pickupSpeed = Constants.PICKUP_DEPLOY_SPEED;
        } else if (runReverse) {
            pickupSpeed = -Constants.PICKUP_SPEED;
        } else if (runForward) {// && CommandBase.shooterSubsystem.getLimit()) {
            //You can't try to intake unless the limit switch is pressed
            pickupSpeed = Constants.PICKUP_SPEED;
        }

        vicPickup.set(pickupSpeed);
    }

    public boolean allowShot() {
        long now = System.currentTimeMillis();

        //Allow a shot if it's deployed and we've delayed long enough
        return pickupDown() && now - lastRetractedTime >= Constants.PICKUP_MOTION_TIME;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new PickupCommand());
    }

    public void print() {
        System.out.println("[PickupSubsystem] **********************************");
        //System.out.println("Pickup speed: " + vicPickup.get());
    }
}