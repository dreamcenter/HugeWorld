package top.dreamcenter.hw.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

public class HwServer3 {
    private static volatile HashMap<Integer,Integer> damage;

    public static void main(String[] args) throws IOException {
        System.out.println("[server3 start]");

        Properties properties = new Properties();
        properties.load(new FileInputStream("sources\\server.properties"));
        int port = Integer.parseInt(properties.getProperty("port3"));

        ServerSocket ss = null;
        Socket socket = null;
        damage = new HashMap<>();
        try {
            ss = new ServerSocket(port);
            while(true){
                socket = ss.accept();
                new ServerThread3(socket,damage).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
