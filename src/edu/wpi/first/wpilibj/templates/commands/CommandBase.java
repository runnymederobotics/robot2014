package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.PickupSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.ShooterSubsystem;


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
