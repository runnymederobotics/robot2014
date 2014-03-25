package robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import robot.commands.autonomous.LowGoalCommandGroup;
import robot.commands.autonomous.OneBallCommandGroup;
import robot.commands.autonomous.TwoBallCommandGroup;
import robot.commands.autonomous.TwoBallDragCommandGroup;
import robot.commands.autonomous.TwoBallDragDriveShotCommandGroup;

public class RobotServer extends Thread {

    int port;
    CommandGroup currentAutonomousCommand;

    public RobotServer(int port) {
        this.port = port;
        this.currentAutonomousCommand = new OneBallCommandGroup();
    }

    public void run() {
        try {
            StreamConnectionNotifier server = (StreamConnectionNotifier) Connector.open("socket://:" + port);

            Client currentClient = null;

            while (true) {
                StreamConnection connection = server.acceptAndOpen();
                System.out.println("Client Connected.");

                if (currentClient != null) {
                    currentClient.kill();
                }

                currentClient = new Client(connection);
                currentClient.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommandGroup getAutonomousCommand() {
        return currentAutonomousCommand;
    }

    private class Client extends Thread {

        private StreamConnection connection;
        private boolean running;

        public Client(StreamConnection connection) {
            this.connection = connection;
            this.running = true;
        }

        public void kill() {
            try {
                connection.close();
                running = false;
            } catch (Exception e) {
            }
        }

        public void run() {
            try {
                InputStream is = connection.openInputStream();

                while (running) {
                    byte[] buffer = new byte[1024];

                    int len = is.read(buffer);

                    if (len < 0) {
                        kill();
                        running = false;
                        break;
                    }

                    String input = new String(buffer, 0, len);

                    handleInput(input);
                }
            } catch (Exception e) {
                //The client disconnected
                System.out.println("Client Disconnected.");
            }
        }

        public void handleInput(String input) {
            if (input.equals("OneBall")) {
                System.out.println("Selecting: " + input);
                currentAutonomousCommand = new OneBallCommandGroup();
            } else if (input.equals("LowGoal")) {
                System.out.println("Selecting: " + input);
                currentAutonomousCommand = new LowGoalCommandGroup();
            } else if (input.equals("TwoBall")) {
                System.out.println("Selecting: " + input);
                currentAutonomousCommand = new TwoBallCommandGroup();
            } else if (input.equals("TwoBallDrag")) {
                System.out.println("Selecting: " + input);
                currentAutonomousCommand = new TwoBallDragCommandGroup();
            } else if (input.equals("TwoBallDragDriveShot")) {
                System.out.println("Selecting: " + input);
                currentAutonomousCommand = new TwoBallDragDriveShotCommandGroup();
            } else {
                System.out.println("Unknown input: " + input);
            }
        }
    }
}
