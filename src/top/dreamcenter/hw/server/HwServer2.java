package top.dreamcenter.hw.server;

import top.dreamcenter.hw.bean.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

public class HwServer2 {

    public static void main(String[] args) throws IOException {
        System.out.println("[server2 start]");

        Properties properties = new Properties();
        properties.load(new FileInputStream("sources\\server.properties"));
        int port = Integer.parseInt(properties.getProperty("port2"));

        HashMap<Integer,Person> hashMap = null;

        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            hashMap = new HashMap<>();

            while(true){
                socket = serverSocket.accept();
                ServerThread2 serverThread = new ServerThread2(socket, hashMap);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
