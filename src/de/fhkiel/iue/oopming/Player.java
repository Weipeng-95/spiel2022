package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends FlyingObject {
    private Position pCenter;
    private PImage playerImage;
    private float playerWidth;
    private float playerHeight;
    private int leben = 3;
    private int speed = 6;
    private int radius = 80;


    public Position getpCenter() {
        return pCenter;
    }

    public void setpCenter(Position pCenter) {
        this.pCenter = pCenter;
    }

    public float getPlayerWidth() {
        return playerWidth;
    }

    public void setPlayerWidth(float playerWidth) {
        this.playerWidth = playerWidth;
    }

    public float getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(float playerHeight) {
        this.playerHeight = playerHeight;
    }

    public PImage getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(PImage playerImage) {
        this.playerImage = playerImage;
    }

    public void setup(PApplet pApplet){
        setpCenter(new Position(pApplet.width / 2, pApplet.height - 100));
        setPlayerImage(pApplet.loadImage("src/de/fhkiel/iue/oopming/image/Flugzeug.png"));
        setPlayerHeight(getPlayerImage().height);
        setPlayerWidth(getPlayerImage().width);
    }
    public void draw(PApplet pApplet) {
        pApplet.image(playerImage, pCenter.getX(), pCenter.getY());
    }


    public void steuen(PApplet pApplet) {
        if (pApplet.keyPressed) {
            if (pCenter.getX() >= getPlayerWidth() / 2
                    && pCenter.getX() <= Main.width - getPlayerWidth() / 2
                    && pCenter.getY() >= getPlayerHeight() / 2
                    && pCenter.getY() <= Main.height - getPlayerHeight() / 2) {
                if (pApplet.keyCode == pApplet.RIGHT) {
                    pCenter.setX(pCenter.getX() + speed);
                } else if (pApplet.keyCode == pApplet.LEFT) {
                    pCenter.setX(pCenter.getX() - speed);
                } else if (pApplet.keyCode == pApplet.UP) {
                    pCenter.setY(pCenter.getY() - speed);
                } else if (pApplet.keyCode == pApplet.DOWN) {
                    pCenter.setY(pCenter.getY() + speed);
                }
            } else if (pCenter.getX() < getPlayerWidth() / 2) pCenter.setX(getPlayerWidth() / 2);
            else if (pCenter.getX() > Main.width - getPlayerWidth() / 2) pCenter.setX(Main.width - getPlayerWidth() / 2);
            else if (pCenter.getY() < getPlayerHeight() / 2) pCenter.setY(getPlayerHeight() / 2);
            else if (pCenter.getY() > Main.height - getPlayerHeight() / 2) pCenter.setY(Main.height - getPlayerHeight() / 2);

        }
    }


    @Override
    public boolean ausserhalbSpielFeld() {
        return false;
    }
}
