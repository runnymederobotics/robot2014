package robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import robot.commands.CommandBase;

public class AutonomousDelayCommand extends CommandBase {

    long startTime, delay;

    public AutonomousDelayCommand(long delay) {
        this.delay = delay;
    }

    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return System.currentTimeMillis() - startTime > delay || !DriverStation.getInstance().isAutonomous();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
