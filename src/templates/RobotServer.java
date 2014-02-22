package templates;

import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class RobotServer extends Thread {

    int port;

    public RobotServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            StreamConnectionNotifier server = (StreamConnectionNotifier) Connector.open("socket://:" + port);

            Client currentClient = null;

            while (true) {
                StreamConnection connection = server.acceptAndOpen();
                System.out.println("Client Connected");

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
                        break;
                    }

                    String str = new String(buffer, 0, len);

                    System.out.println(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
