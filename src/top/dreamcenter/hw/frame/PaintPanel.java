package top.dreamcenter.hw.frame;

import top.dreamcenter.hw.audio.AudioCenter;
import top.dreamcenter.hw.bean.Person;
import top.dreamcenter.hw.util.DimensionXY;
import top.dreamcenter.hw.util.MapBuild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class PaintPanel extends JPanel implements KeyListener{
    private int[][] map;
    private int mapW;
    private int mapH;
    private Person person;
    private AudioCenter audioCenter;
    private HashMap<Integer,Person> hashMap;    //player
    private List<DimensionXY> light;
    private Random random;

    RadarPanel radarPanel;

    private int dx; // page dx
    private int dy;

    public PaintPanel(MapBuild mapBuild, Person person, HashMap<Integer,Person> hashMap){
        setSize(1200,1000);
        setLayout(null);
        this.hashMap = hashMap;
        light = new ArrayList<>();
        random = new Random();

        // init map
        map = mapBuild.getMap();
        mapW = mapBuild.getMapW();
        mapH = mapBuild.getMapH();

        this.person = person;

        dx = -person.getPersonX()+5;
        dy = -person.getPersonY()+5;

        addKeyListener(this);
        radarPanel = new RadarPanel(1020,160,100);

        setVisible(false);
        setFocusable(true);

        // bgm start
        audioCenter = new AudioCenter();
        new Thread(audioCenter).start();
    }

    @Override
    public void paint(Graphics g) {
        if(random.nextDouble()<0.01){
            person.setCurMoney((person.getCurMoney()>99)?100:person.getCurMoney()+1);
        }

        g.setColor(Color.GRAY);
        g.fillRect(0,0,1000,1000);

        mapDraw(g);

        personDraw(g);
        rightSquare(g);

        try {
            Thread.sleep(50);  //50
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void mapDraw(Graphics g){
        int[][] pM = person.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // basic view
                if(map[i][j] == 0 || pM[i][j] == 0) g.setColor(Color.BLACK);
                else g.setColor(Color.WHITE);
                // person view
                if (i>=person.getPersonY()-1 && i<=person.getPersonY()+1
                    && j>= person.getPersonX()-1 && j<= person.getPersonX()+1) {
                    g.setColor((map[i][j]==0? Color.BLACK:Color.WHITE));
                }
                g.fillRect(j*100 + dx*100,i*100 + dy*100,100,100);
            }
        }

        // light view
        for (int i = 0; i < light.size(); i++) {
            DimensionXY d = light.get(i);
            g.setColor(Color.yellow);
            g.fillRect(d.getX()*100 + dx*100 +30,d.getY()*100 + dy*100 +30,
                    100 -60,100 -60);
        }

    }

    private void personDraw(Graphics g){
        g.setColor(person.getColor());
        g.fillOval(person.getPersonX()*100+dx*100,person.getPersonY()*100+dy*100,100,100);
        Iterator<Map.Entry<Integer,Person>> iterator = hashMap.entrySet().iterator();
//        System.out.println(hashMap.size());
        int[][] mapTemp = person.getMap();
        while(iterator.hasNext()){
            Map.Entry<Integer,Person> entry = iterator.next();
            Person p = entry.getValue();
            if((mapTemp[p.getPersonY()][p.getPersonX()]==1
                    || (Math.abs(person.getPersonX()-p.getPersonX())<2
                        && Math.abs(person.getPersonY()-p.getPersonY())<2))){
                if(entry.getKey() != person.getColor().getRGB()){
                    g.setColor(entry.getValue().getColor());
                    g.fillOval(p.getPersonX()*100+dx*100,p.getPersonY()*100+dy*100,100,100);
                }
            }
        }
    }

    private void rightSquare(Graphics g){
        //split left and right
        g.setColor(new Color(200,180,180));
        g.fillRect(1000,0,10,1000);
        g.setColor(new Color(200,200,200));
        g.fillRect(1010,0,190,1000);

        // border
        g.setColor(Color.BLACK);
        g.drawRect(1020,100,160,15);
        g.drawRect(1020,140,160,15);
        //content
        g.setColor(Color.RED);
        g.fillRect(1021,101,159*person.getCurLife()/person.getLife(),14);
        g.setColor(Color.orange);
        g.fillRect(1021,141,159*person.getCurMoney()/person.getMoney(),14);

        g.setColor(Color.BLACK);
        g.setFont(new Font("正楷",Font.BOLD,45));
        g.drawString("NAME",1021,35);
        g.setFont(new Font("正楷",Font.BOLD,20));
        g.drawString("HP:" +person.getCurLife() + "/" + person.getLife(),1021,90);
        g.drawString("ME:" +person.getCurMoney() + "/" + person.getMoney(),1021,135);

        radarPanel.paint(g);

        g.setColor(Color.BLUE);
        g.drawString("在线人数 : " + hashMap.size(),1021,350);

        alertFound(g);
        faceShow(g);
    }

    private void alertFound(Graphics g){
        g.setColor(Color.RED);
        g.drawRect(1018,360,165,30);

        if (person.isAlertView()){
            Iterator<Map.Entry<Integer,Person>> iterator = hashMap.entrySet().iterator();
            int[][] mapTemp = person.getMap();
            boolean has = false;
            Person p = null;
            while(iterator.hasNext()){
                Map.Entry<Integer,Person> entry = iterator.next();
                p = entry.getValue();
                if(entry.getKey()!=person.getColor().getRGB()){
                    if(mapTemp[p.getPersonY()][p.getPersonX()]==1|| (Math.abs(person.getPersonX()-p.getPersonX())<2
                            && Math.abs(person.getPersonY()-p.getPersonY())<2)){
                        has = true;
                        break;
                    }
                }
            }
            if (has){
                g.drawString("玩家["+p.getCurLife()+"]闯入!" ,1021,382);
            } else{
                g.setColor(Color.BLUE);
                g.drawString("监听中..." ,1021,382);
            }
        }else {
            g.drawString("购买警报P(20ME)" ,1021,382);
        }
    }

    private void faceShow(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("正楷",Font.BOLD,40));
        switch (person.getFace()){
            case 'W':
                g.drawString("⇧" ,1021,420);
                break;
            case 'A':
                g.drawString("⇦" ,1021,420);
                break;
            case 'S':
                g.drawString("⇩" ,1021,420);
                break;
            case 'D':
                g.drawString("⇨" ,1021,420);
                break;
            default:;
        }
//        g.drawString(Character.toString() ,1021,410);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        /**
         * 37   <
         * 38   ^
         * 39   >
         * 40   _
         */
        /**
         * 0    wall
         * 1    road
         * ?    light(10)   =>  personal
         * ?    light_cover =>  public
         */
//        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()){
            case 37:    //left
                if ((person.getPersonX()-1>=0)&&(map[person.getPersonY()][person.getPersonX()-1]==1)){
                    dx++;
                    person.setPersonXY(person.getPersonX()-1,person.getPersonY());
                    audioCenter.footStep();
                    person.setFace('A');
                }
                break;
            case 38:    //up
               if ((person.getPersonY()-1>=0)&&(map[person.getPersonY()-1][person.getPersonX()]==1)){
                   dy++;
                   person.setPersonXY(person.getPersonX(),person.getPersonY()-1);
                   audioCenter.footStep();
                   person.setFace('W');
               }
                break;
            case 39:    //right
                if ((person.getPersonX()+1<mapW)&&(map[person.getPersonY()][person.getPersonX()+1]==1)){
                    dx--;
                    person.setPersonXY(person.getPersonX()+1,person.getPersonY());
                    audioCenter.footStep();
                    person.setFace('D');
                }
                break;
            case 40:    //down
                if ((person.getPersonY()+1<mapH)&&(map[person.getPersonY()+1][person.getPersonX()]==1)){
                    dy--;
                    person.setPersonXY(person.getPersonX(),person.getPersonY()+1);
                    audioCenter.footStep();
                    person.setFace('S');
                }
                break;
            case 76:    //L =>  light
                if (person.getCurMoney()-5>=0){
                    light.add(person.getPersonXY());
                    person.setCurMoney(person.getCurMoney()-5);
                    for (int j = -1; j < 2; j++) {
                        for (int k = -1; k < 2; k++) {
                            if(person.getPersonX()+j>=0 && person.getPersonX()+j<mapH
                                    && person.getPersonY()+k>=0 && person.getPersonY()+k<mapW){
                                person.setMap(person.getPersonX()+j,person.getPersonY() + k,1);
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"NO MONEY TO BUY THE LIGHT!");
                }
                break;
            case 80:    //P => buy alert sys
                if(person.getCurMoney()-20>=0){
                    person.setCurMoney(person.getCurMoney()-20);
                    person.setAlertView(true);
                }
                break;
            case 87:    //W
                person.setFace('W');
                break;
            case 65:    //A
                person.setFace('A');
                break;
            case 83:    //S
                person.setFace('S');
                break;
            case 68:    //D
                person.setFace('D');
                break;
            case 74:    //J attack
                if (person.getCurMoney()-5>=0){
                    person.setCurMoney(person.getCurMoney()-5);
                    attackCal();
                }else{
                    JOptionPane.showMessageDialog(null,"NO MONEY TO BUY ONE SHOOT!");
                }
                break;
            case 27:    //exit
                System.exit(1);
            default:;
        }

        repaint();
    }

    private synchronized void attackCal(){
        List<DimensionXY> range = new ArrayList<>();
        int ddx = 0;
        int ddy = 0;
        switch (person.getFace()){
            case 'W': ddy--; break;
            case 'A': ddx--; break;
            case 'S': ddy++; break;
            case 'D': ddx++; break;
            default:break;
        }
        for (int i = 0; i < 4; i++) {
            int tempX = person.getPersonX() + i*ddx;
            int tempY = person.getPersonY() + i*ddy;
            if (tempX<0 || tempX>=mapW || tempY<0 || tempY>=mapH
                    || map[tempY][tempX] == 0){
                break;
            }
            Iterator<Map.Entry<Integer,Person>> iterator = hashMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<Integer,Person> entry = iterator.next();
                Person p = entry.getValue();
                if (p.getPersonX() == tempX && p.getPersonY() == tempY
                        && p.getColor().getRGB()!=person.getColor().getRGB()){
                    person.setDamageC(p.getColor().getRGB());
                    person.setDamageD(20 + person.getDamageD());
                    if(p.getCurLife()<=person.getDamageD()){
                        person.setCurMoney((person.getCurMoney()>50)?100:person.getCurMoney()+50);
                        JOptionPane.showMessageDialog(null,"Killed a player! 50ME to you!");
                    }
                    i = 5;
                    break;
                }
            }
        }
    }
}
