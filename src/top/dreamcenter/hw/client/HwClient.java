package top.dreamcenter.hw.client;

import top.dreamcenter.hw.bean.Person;
import top.dreamcenter.hw.frame.MainFrame;
import top.dreamcenter.hw.util.MapBuild;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

//  java top.dreamcenter.hw.client.HwClient

public class HwClient {
    public static void main(String[] args) {

        System.out.println("[client start]");
        Properties properties = new Properties();
        Socket socket = null;
        HashMap<Integer,Person> hashMap = new HashMap<>();
        try {
            properties.load(new FileInputStream("sources\\basic.properties"));
//            String ip = properties.getProperty("ip_address");

            String ip1 = properties.getProperty("server1");
            String ip2 = properties.getProperty("server2");
            String ip3 = properties.getProperty("server3");
            int port1 = Integer.parseInt(properties.getProperty("port1"));
            int port2 = Integer.parseInt(properties.getProperty("port2"));
            int port3 = Integer.parseInt(properties.getProperty("port3"));

            socket = new Socket(ip1, port1);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("request map ...\n");
            bufferedWriter.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            MapBuild mapBuild = (MapBuild) ois.readObject();
            System.out.println("data received mapHeight:" + mapBuild.getMapH());
            ois.close();
            bufferedWriter.close();

            Person person = new Person(100,100,mapBuild.getDimensionXY());
            person.setPersonXY(mapBuild.getStartPlace(1));
            person.setCurLife(100);
            person.setCurMoney(100);
            new MainFrame(mapBuild, person, hashMap);

            socket = new Socket(ip2, port2);
            ClientThread clientThread = new ClientThread(socket, person, hashMap);
            clientThread.start();

            Socket socket2 = new Socket(ip3, port3);
            ClientThread2 clientThread2 = new ClientThread2(socket2,person);
            clientThread2.start();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
