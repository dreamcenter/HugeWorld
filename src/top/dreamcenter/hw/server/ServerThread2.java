package top.dreamcenter.hw.server;

import top.dreamcenter.hw.bean.Person;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class ServerThread2 extends Thread{
    private Socket socket;
    private HashMap<Integer,Person> hashMap;
    public ServerThread2(Socket socket, HashMap<Integer,Person> hashMap){
        this.socket = socket;
        this.hashMap = hashMap;
    }
    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Person person = null;
        try{
            while(true){
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                person = (Person) ois.readObject();

                hashMap.put(person.getColor().getRGB(),person);
                System.out.println(hashMap + "##" + hashMap.size() + "##");

                oos.writeObject(hashMap);
            }
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("user " +person.getColor().getRGB()+" exit ...");
        } finally {
            hashMap.remove(person.getColor().getRGB());
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
