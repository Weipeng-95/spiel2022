package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends FlyingObject {
    private Position pCenter;
    private PImage playerImage;
    private int leben = 3;
    private int speed = 6;
    private int radius = 80;

    public Player() {
        setpCenter(new Position(Main.WIDTH / 2, Main.HEIGHT - 100));

    }

    public Position getpCenter() {
        return pCenter;
    }

    public void setpCenter(Position pCenter) {
        this.pCenter = pCenter;
    }

    public void draw(PApplet pApplet) {
        playerImage = pApplet.loadImage("src/de/fhkiel/iue/oopming/image/Flugzeug.png");
        pApplet.image(playerImage, pCenter.getX(), pCenter.getY());
    }

    public void steuen(PApplet pApplet) {
        if (pApplet.keyPressed) {
            if (pCenter.getX() >= playerImage.width / 2
                    && pCenter.getX() <= Main.WIDTH - playerImage.width / 2
                    && pCenter.getY() >= playerImage.height / 2
                    && pCenter.getY() <= Main.HEIGHT - playerImage.height / 2) {
                if (pApplet.keyCode == pApplet.RIGHT) {
                    pCenter.setX(pCenter.getX() + speed);
                } else if (pApplet.keyCode == pApplet.LEFT) {
                    pCenter.setX(pCenter.getX() - speed);
                } else if (pApplet.keyCode == pApplet.UP) {
                    pCenter.setY(pCenter.getY() - speed);
                } else if (pApplet.keyCode == pApplet.DOWN) {
                    pCenter.setY(pCenter.getY() + speed);
                }
            } else if (pCenter.getX() < playerImage.width / 2) pCenter.setX(playerImage.width / 2);
            else if (pCenter.getX() > Main.WIDTH - playerImage.width / 2)
                pCenter.setX(Main.WIDTH - playerImage.width / 2);
            else if (pCenter.getY() < playerImage.height / 2) pCenter.setY(playerImage.height / 2);
            else if (pCenter.getY() > Main.HEIGHT - playerImage.height / 2)
                pCenter.setY(Main.HEIGHT - playerImage.height / 2);
        }
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return false;
    }
}
