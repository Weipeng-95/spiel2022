package de.fhkiel.iue.oopming;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Main extends PApplet {
    static int width = 500;
    static int height = 800;
    int lamsamer;
    public Player player = new Player();
    public Gegner gegner = new Gegner();
    ArrayList bullets = new ArrayList();
    List<Gegner> gegners = new ArrayList<Gegner>();
    List<Geschoss> geschosse = new ArrayList<Geschoss>();

    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");

    }

    public void setup() {
        noCursor();
        noStroke();
        //创建10个敌机
        for (int i = 0; i < 8; i++) {
            gegners.add(new Gegner());
        }

    }

    @Override
    public void settings() {
        // Spielfeld

        size(width, height);
        player.setpCenter(new Position(width / 2, height - 100));
        player.setColor(new Color(100, 100, 200));
    }

    @Override
    public void draw() {
        background(0);
        //让敌机往下移动
        for (int i = 0; i < gegners.size(); i++) {
            //获取敌机实例
            Gegner gegner = gegners.get(i);
            gegner.draw(this);
            gegner.move();
            if (gegner.ausserhalbSpielFeld()) {
                //删除敌机
                gegners.remove(gegner);
                //创建新敌机
                gegners.add(new Gegner());
            }
        }

        lamsamer++;
        if (lamsamer % 6 == 0) {
            Geschoss geschoss = new Geschoss((int) player.getpCenter().getX(), (int) player.getpCenter().getY());
            geschosse.add(geschoss);
        }
        for (int i = 0; i < geschosse.size(); i++) {
            Geschoss tempGeschoss = geschosse.get(i);
            tempGeschoss.draw(this);
            tempGeschoss.move();
            if (tempGeschoss.ausserhalbSpielFeld()) {
                geschosse.remove(tempGeschoss);
            }
        }
        player.draw(this);
        player.steuen(this);
    }
}

