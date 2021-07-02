package top.dreamcenter.hw.bean;

import top.dreamcenter.hw.util.DimensionXY;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Person implements Serializable {
    private int life;   // life of one person
    private int money;  // monet of one person
    private int[][] mapCover;// map of one person
    private int mapCoverW;
    private int mapCoverH;
    private int personX;
    private int personY;
    private int curLife;
    private int curMoney;
    private Color color;
    private boolean alertView;
    private char face;  //wasd

    // damage judge
    private int damageC;
    private int damageD;

    public Person(int life, int money, int mapW, int mapH) {
        this.life = life;
        this.money = money;
        mapCoverH = mapH;
        mapCoverW = mapW;
        mapCover = new int[mapH][mapW];
        curLife = life;
        curMoney = money;
        Random random = new Random();
        color = new Color(Math.abs(random.nextInt()%256),Math.abs(random.nextInt()%256),Math.abs(random.nextInt()%256));
        face = 'W';
    }

    public Person(int life, int money, DimensionXY dimensionXY){
        this.life = life;   // total
        this.money = money; // total
        mapCoverH = dimensionXY.getY();
        mapCoverW = dimensionXY.getX();
        mapCover = new int[mapCoverH][mapCoverW];
        curLife = life;
        curMoney = money;
        Random random = new Random();
        color = new Color(Math.abs(random.nextInt()%256),Math.abs(random.nextInt()%256),Math.abs(random.nextInt()%256));
        face = 'W';
    }

    public int getDamageD() {
        return damageD;
    }

    public void setDamageD(int damageD) {
        this.damageD = damageD;
    }

    public int getDamageC() {
        return damageC;
    }

    public void setDamageC(int damageC) {
        this.damageC = damageC;
    }

    public char getFace() {
        return face;
    }

    public void setFace(char face) {
        this.face = face;
    }

    public boolean isAlertView() {
        return alertView;
    }

    public void setAlertView(boolean alertView) {
        this.alertView = alertView;
    }

    public void setPersonXY(int x, int y){
        this.personX = x;
        this.personY = y;
    }

    public void setPersonXY(DimensionXY dimensionXY){
        this.personX = dimensionXY.getX();
        this.personY = dimensionXY.getY();
    }

    public Color getColor() {
        return color;
    }

    public int getCurLife() {
        return curLife;
    }

    public void setCurLife(int curLife) {
        this.curLife = curLife;
    }

    public int getCurMoney() {
        return curMoney;
    }

    public void setCurMoney(int curMoney) {
        this.curMoney = curMoney;
    }

    public int getPersonX() {
        return personX;
    }

    public int getPersonY() {
        return personY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int[][] getMap() {
        return mapCover;
    }

    public DimensionXY getPersonXY(){
        return new DimensionXY(personX,personY);
    }

    public void setMap(int x, int y, int value) {
        mapCover[y][x] = value;
    }

    private String getMapCoverDataAsString(){
        String temp = "[\n";
        /*for (int i = 0; i < mapCoverH; i++) {
            temp += "[";
            for (int j = 0; j < mapCoverW; j++) {
                temp += mapCover[i][j] + (j == mapCoverW-1 ? "" : ",");
            }
            temp += "]" + (i == mapCoverH-1 ? "" : ",") + "\n";
        }*/
        temp += "]";
        return temp;
    }

    @Override
    public String toString() {
        return "Person{" +
                "life=" + life +
                ", money=" + money +
                ", mapCover=" + getMapCoverDataAsString() +
                ", mapCoverW=" + mapCoverW +
                ", mapCoverH=" + mapCoverH +
                ", personX=" + personX +
                ", personY=" + personY +
                '}';
    }
}
