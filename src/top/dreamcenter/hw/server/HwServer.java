package top.dreamcenter.hw.server;

import top.dreamcenter.hw.util.MapBuild;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class HwServer {

    public static void main(String[] args) throws IOException {
        System.out.println("[server start]");

        Properties properties = new Properties();
        properties.load(new FileInputStream("sources\\server.properties"));
        int width = Integer.parseInt(properties.getProperty("width"));
        double p = Double.parseDouble(properties.getProperty("p"));
        int port = Integer.parseInt(properties.getProperty("port1"));

        MapBuild mapBuild = new MapBuild(width,width);
        mapBuild.initMap(0,2,p,1);

        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);

            while(true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket,mapBuild);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
