package top.dreamcenter.hw.frame;

import top.dreamcenter.hw.bean.Person;
import top.dreamcenter.hw.util.MapBuild;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;

public class MainFrame extends JFrame {
    public MainFrame(MapBuild mapBuild, Person person, HashMap<Integer,Person> hashMap){
        setTitle("login");
        setSize(500,835);  //1000, 800
        setResizable(false);
        setLayout(null);
        setLocation(200,0);

        PaintPanel paintPanel = new PaintPanel(mapBuild, person, hashMap);

        /*LoginPanel loginPanel = new LoginPanel();
        loginPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                setSize(1200,1035);
                paintPanel.setVisible(true);
            }
        });

        add(loginPanel);*/

        /**
         * to be delete
         */
        setSize(1200,1035);
        paintPanel.setVisible(true);
        /////////////

        add(paintPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        MapBuild mapBuild = new MapBuild(100,100);
        mapBuild.initMap(0,2,0.5,1);
        HashMap<Integer,Person> hashMap = new HashMap<>();

        Person person = new Person(100,100,mapBuild.getDimensionXY());
        person.setPersonXY(mapBuild.getStartPlace(1));
        person.setCurLife(85);
        person.setCurMoney(56);
        new MainFrame(mapBuild, person, hashMap);
    }
}
