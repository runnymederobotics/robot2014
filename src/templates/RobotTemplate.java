package templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import templates.commands.AutonomousCommandGroup;
import templates.commands.CommandBase;

public class RobotTemplate extends IterativeRobot {

    Compressor compressor = new Compressor(RobotMap.PRESSURE_SWITCH, RobotMap.COMPRESSOR_RELAY);
    long lastPrintTime = 0;

    public void robotInit() {
        compressor.start();
        CommandBase.init();
    }

    public void disabledInit() {
        compressor.stop();
        CommandBase.disable();
    }

    public void autonomousInit() {
        Scheduler.getInstance().add(new AutonomousCommandGroup());
        compressor.start();
        CommandBase.enable();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        print();
    }

    public void teleopInit() {
        compressor.start();
        CommandBase.enable();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        print();
    }

    public void testPeriodic() {
        print();
    }

    void print() {

        long now = System.currentTimeMillis();

        if (now - lastPrintTime > 500) {
            lastPrintTime = now;
            CommandBase.chassisSubsystem.print();
            CommandBase.pickupSubsystem.print();
            CommandBase.shooterSubsystem.print();
        }
    }
}
