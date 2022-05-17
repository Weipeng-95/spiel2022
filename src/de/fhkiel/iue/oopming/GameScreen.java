package de.fhkiel.iue.oopming;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class GameScreen {

    Gegner gegner;
    int lamsamer;
    Player player;
    List gegners;
    List geschosse;
    public GameScreen(PApplet pApplet){
          player = new Player(pApplet);
          gegner = new Gegner();
        gegners = new ArrayList<Gegner>();
        // Array für Geschosse
       geschosse = new ArrayList<Geschoss>();
    }


    public void gameScreen(PApplet pApplet){

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
    lamsamer++;
        if (lamsamer % 4 == 0) {
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
            if(geschoss.istErschossen(gegner1,geschoss)) {
                gegners.remove(gegner1);
                geschosse.remove(geschoss);
                gegners.add(new Gegner());
            }
        }
    }
        player.draw(pApplet);
        player.steuen(pApplet);
}
public void setup(PApplet pApplet){



    player.setpCenter(new Position(pApplet.width / 2, pApplet.height - 100));
    player.setColor(new Color(100, 100, 200));
    gegner.setGcenter(new Position(0,0));
    pApplet.noCursor();
    pApplet.noStroke();
    pApplet.imageMode(pApplet.CENTER);
    //Erstelle 8 Instanzen des Gegners
    for (int i = 0; i < 8; i++) {
        gegners.add(new Gegner());
    }
    }
}
