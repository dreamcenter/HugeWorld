package top.dreamcenter.hw.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread3 extends Thread{
    private Socket socket;
    private HashMap<Integer,Integer> damage;
    public ServerThread3(Socket socket,HashMap<Integer,Integer> damage){
        this.socket = socket;
        this.damage = damage;
    }
    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try{
            while (true){
                dis = new DataInputStream(socket.getInputStream());
                int c = dis.readInt();
                int d = dis.readInt();
                int cF = dis.readInt();
                dos = new DataOutputStream(socket.getOutputStream());
                if(damage.keySet().contains(c)){
                    damage.replace(c,damage.get(c)-d);
                }else{
                    damage.put(c,-d);
                }
                if(damage.keySet().contains(cF)){
                    dos.writeInt(damage.get(cF));
                    damage.remove(cF);
                }else{
                    dos.writeInt(0);
                }
//                System.out.println(damage);
            }
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("one client exit");
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
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
