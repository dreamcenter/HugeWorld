package top.dreamcenter.hw.server;

import top.dreamcenter.hw.bean.Person;
import top.dreamcenter.hw.util.MapBuild;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private MapBuild mapBuild;
    public ServerThread(Socket socket, MapBuild mapBuild){
        this.socket = socket;
        this.mapBuild = mapBuild;
    }

    @Override
    public void run() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(bufferedReader.readLine());

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(mapBuild);

            oos.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
