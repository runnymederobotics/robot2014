package templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import templates.OI;
import templates.subsystems.ChassisSubsystem;
import templates.subsystems.PickupSubsystem;
import templates.subsystems.ShooterSubsystem;


public abstract class CommandBase extends Command {

    public static OI oi;
    public static ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
    public static PickupSubsystem pickupSubsystem = new PickupSubsystem();
    public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

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
