package templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Scheduler;
import templates.commands.OneBallCommandGroup;
import templates.commands.CommandBase;

public class RobotTemplate extends IterativeRobot {

    Compressor compressor = new Compressor(RobotMap.PRESSURE_SWITCH, RobotMap.COMPRESSOR_RELAY);
    Solenoid testMakeSureToRemove = new Solenoid(6);
    RobotServer robotServer = new RobotServer(8000);
    long lastPrintTime = 0;

    public void robotInit() {
        compressor.start();
        CommandBase.init();
        
        robotServer.start();
    }

    public void disabledInit() {
        compressor.stop();
        CommandBase.disable();
    }

    public void autonomousInit() {
        Scheduler.getInstance().add(new OneBallCommandGroup());
        compressor.start();
        CommandBase.enable();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        print("Autonomous");
    }

    public void teleopInit() {
        compressor.start();
        CommandBase.enable();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        print("Teleop");
        testMakeSureToRemove.set(CommandBase.oi.getShifterButton());
    }

    public void testPeriodic() {
        print("Test");
    }

    void print(String mode) {

        long now = System.currentTimeMillis();

        if (now - lastPrintTime > 500) {
            lastPrintTime = now;
            
            System.out.println("[" + mode + "]");
            
            CommandBase.chassisSubsystem.print();
            CommandBase.pickupSubsystem.print();
            CommandBase.shooterSubsystem.print();
        }
    }
}
