package robot;

import com.team254.lib.CheesyVisionServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import robot.commands.CommandBase;

public class RobotTemplate extends IterativeRobot {

    Compressor compressor = new Compressor(RobotMap.PRESSURE_SWITCH, RobotMap.COMPRESSOR_RELAY);
    RobotServer robotServer = new RobotServer(8000);
    long lastPrintTime = 0;
        
    public static CheesyVisionServer cheesyVisionServer = CheesyVisionServer.getInstance();
    public final int listenPort = 1180;

    public void robotInit() {
        compressor.start();
        CommandBase.init();
        
        robotServer.start();
        
        cheesyVisionServer.setPort(listenPort);
        cheesyVisionServer.start();
        
    }

    public void disabledInit() {
        compressor.stop();
        CommandBase.disable();
        
        cheesyVisionServer.stopSamplingCounts();
        
    }

    public void disabledPeriodic() {
        
    }
    
    public void autonomousInit() {
        
        cheesyVisionServer.reset();
        cheesyVisionServer.startSamplingCounts();
        
        Scheduler.getInstance().add(robotServer.getAutonomousCommand());
        compressor.start();
        CommandBase.enable();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        print("Autonomous");
        //System.out.println("Current left: " + cheesyVisionServer.getLeftStatus() + ", current right: " + cheesyVisionServer.getRightStatus());
    }

    public void teleopInit() {
        compressor.start();
        CommandBase.enable();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        print("Teleop");
    }

    public void testPeriodic() {
        print("Test");
    }

    void print(String mode) {

        long now = System.currentTimeMillis();

        if (now - lastPrintTime > 500) {
            lastPrintTime = now;
            /*
            System.out.println("[" + mode + "]");
            
            CommandBase.chassisSubsystem.print();
            CommandBase.pickupSubsystem.print();
            CommandBase.shooterSubsystem.print();
            */
        }
    }
}
