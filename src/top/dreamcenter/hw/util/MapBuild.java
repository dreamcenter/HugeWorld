package top.dreamcenter.hw.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapBuild implements Serializable {
    private final int mapW;
    private final int mapH;
    private int[][] map;

    public MapBuild(int mapW, int mapH){
        map = new int[mapH][mapW];
        this.mapW = mapW;
        this.mapH = mapH;
    }

    /**
     *
     * @param min   min possible num
     * @param max   max possible num
     * @param p     possiblility to change the result
     * @param mark  the target result which want to replace the change data
     */
    public void initMap(int min, int max, double p, int mark){
        Random random = new Random();
        for (int i = 0; i < mapH; i++) {
            for (int j = 0; j < mapW; j++) {
                map[i][j] = Math.abs(random.nextInt())%(max - min) + min;
                if (Math.abs(random.nextDouble())<=p) map[i][j] = mark;
            }
        }
    }

    public DimensionXY getStartPlace(int save) {
        List<DimensionXY> list = new ArrayList<>();
        for (int i = 0; i < mapH; i++) {
            for (int j = 0; j < mapW; j++) {
                if(map[i][j] == save){
                    list.add(new DimensionXY(j,i));
                }
            }
        }
        return list.get(Math.abs(new Random().nextInt())%list.size());
    }

    public int getMapW() {
        return mapW;
    }

    public int getMapH() {
        return mapH;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int x, int y, int value) {
        map[y][x] = value;
    }

    public DimensionXY getDimensionXY(){
        return new DimensionXY(mapW, mapH);
    }

    private String getMapDataAsString(){
        String temp = "[\n";
        for (int i = 0; i < mapH; i++) {
            temp += "[";
            for (int j = 0; j < mapW; j++) {
                temp += map[i][j] + (j == mapW-1 ? "" : ",");
            }
            temp += "]" + (i == mapH-1 ? "" : ",") + "\n";
        }
        temp += "]";
        return temp;
    }

    @Override
    public String toString() {
        return "MapBuild{" +
                "mapW=" + mapW +
                ", mapH=" + mapH +
                ", map=" + getMapDataAsString() +
                '}';
    }
}
