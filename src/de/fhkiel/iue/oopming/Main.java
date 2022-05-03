package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Main extends PApplet {
    static int width = 500;
    static int height = 800;
    int lamsamer;
    public Player player = new Player(this);
    public Gegner gegner = new Gegner();

//    public Geschoss geschoss = new Geschoss(0, 0);

    // Array für Gegner
    List<Gegner> gegners = new ArrayList<Gegner>();
    // Array für Geschosse
    List<Geschoss> geschosse = new ArrayList<Geschoss>();

    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");
    }

    public void setup() {
        noCursor();
        noStroke();
        imageMode(CENTER);
        //Erstelle 8 Instanzen des Gegners
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
        gegner.setGcenter(new Position(0,0));
//        geschoss.setGeCenter(new Position(0,0));
    }

    @Override
    public void draw() {
        background(0);
        //
        for (int i = 0; i < gegners.size(); i++) {
            //get Instance von Gegner
            gegner = gegners.get(i);
            gegner.draw(this);
            gegner.move();
            if (gegner.ausserhalbSpielFeld()) {
                //Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                gegners.remove(gegner);
                //ein neuer Gegner herstellen
                gegners.add(new Gegner());
            }
        }
// lasst Geschoss nach bestimmter Zeit erzeugen
        lamsamer++;
        if (lamsamer % 4 == 0) {
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

        for (int i = 0; i < gegners.size(); i++) {
            Gegner gegner1 = gegners.get(i);
            for (int j = 0; j < geschosse.size(); j++) {
                Geschoss geschoss = geschosse.get(j);
                if(geschoss.istErschossen(gegner1,geschoss)) {
                    gegners.remove(gegner1);
                    geschosse.remove(geschoss);
                    gegners.add(new Gegner());
                }
            }
        }
        player.draw(this);
        player.steuen(this);
    }
}

