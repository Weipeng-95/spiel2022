package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class GameScreen {


    Gegner gegner;
    int langsamer;

    Position expos = new Position();
    boolean explotion = false;
    int explosionImageFrame = 8;
    PImage[] explosionImage = new PImage[explosionImageFrame];
    Player player;
    List gegners;
    List geschosse;
    int u = 0;

    public GameScreen(PApplet pApplet) {

        player = new Player(pApplet);
        gegner = new Gegner();
        gegners = new ArrayList<Gegner>();
        // Array für Geschosse
        geschosse = new ArrayList<Geschoss>();
    }


    public void gameScreen(PApplet pApplet) {

        for (int i = 0; i < explosionImage.length; i++) {
            explosionImage[i] = pApplet.loadImage("src/de/fhkiel/iue/oopming/image/explosion/explosion_" + i + ".png");

        }

        pApplet.background(0);
        //
        for (int i = 0; i < gegners.size(); i++) {
            //get Instance von Gegner
            gegner = (Gegner) gegners.get(i);
            gegner.draw(pApplet);
            gegner.move();
            if (gegner.ausserhalbSpielFeld()) {
                //Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                gegners.remove(gegner);
                //ein neuer Gegner herstellen
                gegners.add(new Gegner());
            }
        }
// lasst Geschoss nach bestimmter Zeit erzeugen
        langsamer++;
        if (langsamer % 4 == 0) {
            Geschoss geschoss = new Geschoss((int) player.getpCenter().getX(), (int) player.getpCenter().getY());
            geschosse.add(geschoss);
        }

        for (int i = 0; i < geschosse.size(); i++) {
            Geschoss tempGeschoss = (Geschoss) geschosse.get(i);
            tempGeschoss.draw(pApplet);
            tempGeschoss.move();
            if (tempGeschoss.ausserhalbSpielFeld()) {
                geschosse.remove(tempGeschoss);
            }
        }

        for (int i = 0; i < gegners.size(); i++) {
            Gegner gegner1 = (Gegner) gegners.get(i);
            for (int j = 0; j < geschosse.size(); j++) {
                Geschoss geschoss = (Geschoss) geschosse.get(j);
                if (geschoss.istErschossen(gegner1, geschoss)) {
                    explotion = true;
                    expos.setY(gegner1.getGcenter().getY());
                    expos.setX(gegner1.getGcenter().getX());
                    gegners.remove(gegner1);
                    geschosse.remove(geschoss);
                    gegners.add(new Gegner());


                }
            }
        }
        player.draw(pApplet);
        player.steuen(pApplet);



        if (explotion) {

            pApplet.image(explosionImage[u],expos.getX(),expos.getY());
            u++;
            if(u==7){
                u=0;
                explotion = false;
            }
        }



    }

    public void setup(PApplet pApplet) {


        player.setpCenter(new Position(pApplet.width / 2, pApplet.height - 100));
        player.setColor(new Color(100, 100, 200));
        gegner.setGcenter(new Position(0, 0));
        pApplet.noCursor();
        pApplet.noStroke();
        pApplet.imageMode(pApplet.CENTER);
        //Erstelle 8 Instanzen des Gegners
        for (int i = 0; i < 8; i++) {
            gegners.add(new Gegner());
        }

    }


}


