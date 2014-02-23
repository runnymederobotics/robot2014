package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.OI;
import robot.subsystems.ChassisSubsystem;
import robot.subsystems.PickupSubsystem;
import robot.subsystems.ShooterSubsystem;
import robot.subsystems.SignalSubsystem;


public abstract class CommandBase extends Command {

    public static OI oi;
    public static ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
    public static PickupSubsystem pickupSubsystem = new PickupSubsystem();
    public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public static SignalSubsystem signalSubsystem = new SignalSubsystem();

    public static void init() {
        oi = new OI();
    }

    public static void enable() {
        chassisSubsystem.enable();
    }

    public static void disable() {
        chassisSubsystem.disable();
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
