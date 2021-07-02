package top.dreamcenter.hw.client;

import top.dreamcenter.hw.bean.Person;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread2 extends Thread{
    private Socket socket;
    private Person person;
    public ClientThread2(Socket socket,Person person){
        this.socket = socket;
        this.person = person;
    }

    @Override
    public void run() {
        DataOutputStream dos = null;
        DataInputStream dis = null;

        try{
            while(true){
                dos = new DataOutputStream(socket.getOutputStream());
                synchronized (person){
                    dos.writeInt(person.getDamageC());
                    dos.writeInt(person.getDamageD());
                    person.setDamageC(0);
                    person.setDamageD(0);
                }

                dos.writeInt(person.getColor().getRGB());
                dis = new DataInputStream(socket.getInputStream());
                int dF = dis.readInt();
                if(person.getCurLife()+dF>0){
                    person.setCurLife(person.getCurLife() + dF);
                } else{
                    person.setCurLife(0);
                    JOptionPane.showMessageDialog(null, "YOU DIED!");
                    System.exit(0);
                }
                sleep(50);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close();
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
