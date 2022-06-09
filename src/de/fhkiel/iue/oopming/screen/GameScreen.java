package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.ExploAnimation;
import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.PlaySound;
import de.fhkiel.iue.oopming.character.Gegner;
import de.fhkiel.iue.oopming.character.Geschoss;
import de.fhkiel.iue.oopming.character.Player;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends Screen {
    Player player;
    List gegners;
    List geschosse;
    private int geschossIntervall;
    private int imageMoveSpeed;

    private ExploAnimation explosion;
    public static boolean isExploded;

    private boolean right, left, up, down, schiessen;


    public GameScreen() {
    }

    @Override
    public void setup(PApplet pApplet) {
        pApplet.textAlign(PConstants.CENTER);
        pApplet.imageMode(pApplet.CENTER);
        gegners = new ArrayList<Gegner>();
        geschosse = new ArrayList<Geschoss>();
        player = new Player();
        setGameFont(pApplet.createFont("de/fhkiel/iue/oopming/font/thunderstrike3d.ttf", 50));

        setImage(Main.background);

        //Erstelle 8 Instanzen des Gegners
        for (int i = 0; i < 5; i++) {
            gegners.add(new Gegner());
        }
    }

    @Override
    public void showScreen(PApplet pApplet) {

        hintergrund(pApplet);

        player.drawCharacter(pApplet);

        gegnerGenerator(pApplet);

        geschossGenerator(pApplet);

        if (!player.outOfBounds()) {
            if (player.getX() <= Main.player0.width / 2)
                player.setX(Main.player0.width / 2);
            if (player.getX() >= Main.WIDTH - Main.player0.width / 2)
                player.setX(Main.WIDTH - Main.player0.width / 2);
            if (player.getY() <= Main.player0.height / 2)
                player.setY(Main.player0.height / 2);
            if (player.getY() >= Main.HEIGHT - Main.player0.height / 2)
                player.setY(Main.HEIGHT - Main.player0.height / 2);
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

        for (int i = 0; i < geschosse.size(); i++) {
            Geschoss tempGeschoss = (Geschoss) geschosse.get(i);
            tempGeschoss.drawCharacter(pApplet);
            tempGeschoss.move();
            if (tempGeschoss.outOfBounds()) {
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

            if (gegnerTemp.outOfBounds()) {
                gegners.remove(gegnerTemp); // Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                gegners.add(new Gegner()); // ein neuer Gegner herstellen
            }


            for (int j = 0; j < geschosse.size(); j++) {
                Geschoss geschoss = (Geschoss) geschosse.get(j);
                if (gegnerTemp.shootBy(geschoss)) {
                    isExploded = true;
                    explosion = new ExploAnimation(gegnerTemp);
                    gegners.remove(gegnerTemp);
                    geschosse.remove(geschoss);
                    gegners.add(new Gegner());
                }
            }
            if (isExploded) {
                explosion.drawExplosion(pApplet);
            }
        }


    }

    public void playerInput(PApplet pApplet) {

        if (right) {
            player.setX(player.getX() + player.getSpeed());
        }
        if (left) {
            player.setX(player.getX() - player.getSpeed());
        }
        if (up) {
            player.setY(player.getY() - player.getSpeed());
        }
        if (down) {
            player.setY(player.getY() + player.getSpeed());
        }
        if (schiessen) {
            if (geschossIntervall % 4 == 0) {
                Geschoss geschoss = new Geschoss(player.getX(), player.getY() - Main.player0.height / 2);
                geschosse.add(geschoss);
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


