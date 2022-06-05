package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.character.Gegner;
import de.fhkiel.iue.oopming.character.Geschoss;
import de.fhkiel.iue.oopming.character.Player;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends Screen {

    Player player;
    List gegners;
    List geschosse;

    private int geschossIntervall;
    private int imageMoveSpeed;

    private int refreshIndex = 0;
    Position expos = new Position();
    private boolean isExplotion = false;
    private int explosionImageFrame = 12;
    PImage[] explosionImage = new PImage[explosionImageFrame];

    public GameScreen() {
        player = new Player();
        gegners = new ArrayList<Gegner>();
        geschosse = new ArrayList<Geschoss>();
    }

    public void setup(PApplet pApplet) {
        player.setupCharacter(pApplet);
        pApplet.noCursor();
        pApplet.noStroke();
        pApplet.imageMode(pApplet.CENTER);
        //Erstelle 8 Instanzen des Gegners
        for (int i = 0; i < 8; i++) {
            gegners.add(new Gegner());
        }
        this.setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/hintergrundbild.png"));

    }

    @Override
    public void showScreen(PApplet pApplet) {

        hintergrund(pApplet);

        player.drawCharacter(pApplet);
        player.steuen(pApplet);

        gegnerGenerator(pApplet);

        geschossGenerator(pApplet);

        if (isExplotion) {
            for (int k = 0; k < explosionImage.length; k++) {
                explosionImage[k] = pApplet.loadImage("de/fhkiel/iue/oopming/images/explosion1/explosion_" + k + ".png");
            }
            pApplet.image(explosionImage[refreshIndex], expos.getX(), expos.getY());
            refreshIndex++;
            if (refreshIndex == explosionImageFrame - 1) {
                refreshIndex = 0;
                isExplotion = false;
            }
        }
    }

    private void hintergrund(PApplet pApplet) {
        imageMoveSpeed = Main.TIMER / 10;
        for (int i = -imageMoveSpeed; i < Main.HEIGHT; i += getImage().height) {
            pApplet.copy(getImage(), 0, 0, getImage().width, Main.HEIGHT,
                    0, -i, getImage().width, Main.HEIGHT);
        }
    }

    private void geschossGenerator(PApplet pApplet) {
        // lasst Geschoss nach bestimmter Zeit erzeugen
        geschossIntervall++;
        if (geschossIntervall % 4 == 0) {
            Geschoss geschoss = new Geschoss(player.getCenter().getX(),
                    player.getCenter().getY() - player.getImage().height / 2);

            geschosse.add(geschoss);
            geschoss.setupCharacter(pApplet);
        }

        for (int i = 0; i < geschosse.size(); i++) {
            Geschoss tempGeschoss = (Geschoss) geschosse.get(i);
            tempGeschoss.drawCharacter(pApplet);
            tempGeschoss.move();
            if (tempGeschoss.ausserhalbSpielFeld()) {
                geschosse.remove(tempGeschoss);
            }
        }
    }

    private void gegnerGenerator(PApplet pApplet) {
        for (int i = 0; i < gegners.size(); i++) {
            //get Instance von Gegner
            Gegner gegnerTemp = (Gegner) gegners.get(i);
            gegnerTemp.drawCharacter(pApplet);
            gegnerTemp.move();

            if (gegnerTemp.ausserhalbSpielFeld()) {
                gegners.remove(gegnerTemp); // Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                gegners.add(new Gegner()); // ein neuer Gegner herstellen
            }

            for (int j = 0; j < geschosse.size(); j++) {
                Geschoss geschoss = (Geschoss) geschosse.get(j);
                if (geschoss.istErschossen(gegnerTemp, geschoss)) {
                    gegners.remove(gegnerTemp);
                    geschosse.remove(geschoss);
                    gegners.add(new Gegner());

                    isExplotion = true;
                    expos = gegnerTemp.getCenter();

                }

            }

        }
    }

}


