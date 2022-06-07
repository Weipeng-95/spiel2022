package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.PlaySound;
import de.fhkiel.iue.oopming.character.Gegner;
import de.fhkiel.iue.oopming.character.Geschoss;
import de.fhkiel.iue.oopming.character.Player;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.data.IntList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends Screen {

    Player player;
    List gegners;
    List geschosse;
    IntList pKeys = new IntList();
    private int geschossIntervall;
    private int imageMoveSpeed;

    private boolean right, left, up, down, schiessen;
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

    @Override
    public void setup(PApplet pApplet) {

        setGameFont(pApplet.createFont("de/fhkiel/iue/oopming/font/thunderstrike3d.ttf", 50));
        pApplet.textAlign(PConstants.CENTER);
        pApplet.imageMode(pApplet.CENTER);
        player.setupCharacter(pApplet);

        //Erstelle 8 Instanzen des Gegners
        for (int i = 0; i < 5; i++) {
            gegners.add(new Gegner());
        }
        this.setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/hintergrundbild.png"));

    }

    @Override
    public void showScreen(PApplet pApplet) {

        hintergrund(pApplet);

        player.drawCharacter(pApplet);

        gegnerGenerator(pApplet);

        geschossGenerator(pApplet);

        if (!player.isInRange()) {
            if (player.getCenter().getX() <= player.getImage().width / 2)
                player.getCenter().setX(player.getImage().width / 2);
            if (player.getCenter().getX() >= Main.WIDTH - player.getImage().width / 2)
                player.getCenter().setX(Main.WIDTH - player.getImage().width / 2);
            if (player.getCenter().getY() <= player.getImage().height / 2)
                player.getCenter().setY(player.getImage().height / 2);
            if (player.getCenter().getY() >= Main.HEIGHT - player.getImage().height / 2)
                player.getCenter().setY(Main.HEIGHT - player.getImage().height / 2);
        }

        if (isExplotion) {
            for (int k = 0; k < explosionImage.length; k++) {
                explosionImage[k] = pApplet.loadImage("de/fhkiel/iue/oopming/images/explosion1/explosion_" + k + ".png");
            }
            pApplet.image(explosionImage[refreshIndex], expos.getX(), expos.getY());
            refreshIndex++;
            if (refreshIndex == explosionImageFrame - 1) {
                refreshIndex = 0;
                isExplotion = false;
                new PlaySound("src/de/fhkiel/iue/oopming/Sound/explosionBgm.wav").start();
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
//        if (geschossIntervall % 4 == 0) {
//            Geschoss geschoss = new Geschoss(player.getCenter().getX(),
//                    player.getCenter().getY() - player.getImage().height / 2);
//
//            geschosse.add(geschoss);
//            geschoss.setupCharacter(pApplet);
//        }

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

    public void playerInput(PApplet pApplet) {

        if (right) {
            player.getCenter().setX(player.getCenter().getX() + player.getSpeed());
        }
        if (left) {
            player.getCenter().setX(player.getCenter().getX() - player.getSpeed());
        }
        if (up) {
            player.getCenter().setY(player.getCenter().getY() - player.getSpeed());
        }
        if (down) {
            player.getCenter().setY(player.getCenter().getY() + player.getSpeed());
        }
        if (schiessen) {
            if (geschossIntervall % 4 == 0) {
                Geschoss geschoss = new Geschoss(player.getCenter().getX(), player.getCenter().getY() - player.getImage().height / 2);
                geschosse.add(geschoss);
                geschoss.setupCharacter(pApplet);
                new PlaySound("src/de/fhkiel/iue/oopming/Sound/shootBgm.wav").start();
            }


        }

    }

    public void playerInputControl(PApplet pApplet, boolean flag) {
        if (pApplet.keyCode == pApplet.RIGHT) right = flag;
        if (pApplet.keyCode == pApplet.LEFT) left = flag;
        if (pApplet.keyCode == pApplet.UP) up = flag;
        if (pApplet.keyCode == pApplet.DOWN) down = flag;
        if (pApplet.key == 'z') schiessen = flag;
    }


}


