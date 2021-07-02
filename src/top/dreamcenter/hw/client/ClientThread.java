package top.dreamcenter.hw.client;

import top.dreamcenter.hw.bean.Person;
import top.dreamcenter.hw.util.MapBuild;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientThread extends Thread{
    private Socket socket;
    private MapBuild mapBuild;
    private Person person;
    private HashMap<Integer,Person> hashMap;

    public ClientThread(Socket socket, Person person, HashMap<Integer,Person> hashMap){
        this.socket = socket;
        this.person = person;
        this.hashMap = hashMap;
    }
    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try{
            while(true){
                if (socket.isClosed()) break;
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(person);

                ois = new ObjectInputStream(socket.getInputStream());

                HashMap<Integer,Person> tempHashMap = (HashMap<Integer, Person>)ois.readObject();
                Iterator<Integer> keys = hashMap.keySet().iterator();
                List<Integer> list = new ArrayList<>();
                while(keys.hasNext()){
                    Integer temp = keys.next();
                    if(!tempHashMap.keySet().contains(temp)){
                        list.add(temp);
                    }
                }
                for (Integer i:list) {
                    hashMap.remove(i);
                }

                hashMap.putAll(tempHashMap);

                sleep(10);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
//            e.printStackTrace();
            System.out.println("server2 exit");
            JOptionPane.showMessageDialog(null,"SERVER2 EXIT");
            System.exit(2);
        } finally {
            System.out.println("exit");
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
