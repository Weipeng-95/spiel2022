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

    public Player(PApplet pApplet) {
//        pCenter = new Position (Main.width / 2,Main.height - 100);

    }

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

    public void draw(PApplet pApplet) {
        pApplet.fill(this.getColor().getR(), this.getColor().getG(), this.getColor().getB());
//        pApplet.circle(pCenter.getX(), pCenter.getY(), radius);
        playerImage = pApplet.loadImage("src/de/fhkiel/iue/oopming/image/Flugzeug.png");
        pApplet.image(playerImage, pCenter.getX(), pCenter.getY());
    }

    public void steuen(PApplet pApplet) {
        if (pApplet.keyPressed) {
            if (pCenter.getX() >= playerImage.width / 2
                    && pCenter.getX() <= Main.width - playerImage.width / 2
                    && pCenter.getY() >= playerImage.height / 2
                    && pCenter.getY() <= Main.height - playerImage.height / 2) {
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
            else if (pCenter.getX() > Main.width - playerImage.width / 2) pCenter.setX(Main.width - playerImage.width / 2);
            else if (pCenter.getY() < playerImage.height / 2) pCenter.setY(playerImage.height / 2);
            else if (pCenter.getY() > Main.height - playerImage.height / 2) pCenter.setY(Main.height - playerImage.height / 2);

        }
    }


    @Override
    public boolean ausserhalbSpielFeld() {
        return false;
    }
}
