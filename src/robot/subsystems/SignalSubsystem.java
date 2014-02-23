package robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Actuator;
import robot.RobotMap;
import robot.commands.SignalCommand;

public class SignalSubsystem extends Subsystem {

    Actuator possesionLED = new Actuator(new Relay(RobotMap.POSSESION_LED_RELAY), false, false);

    public SignalSubsystem() {
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new SignalCommand());
    }

    public void setPossesion(boolean value) {
        possesionLED.set(value);
    }
}
