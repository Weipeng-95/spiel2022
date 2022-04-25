package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Player extends FlyingObject {
    private Position pCenter;
    private int leben = 3;
    private int speed = 6;
    private int radius = 80;

    public Player() {
//        pCenter = new Position (Main.width / 2,Main.height - 100);
    }

    public Position getpCenter() {
        return pCenter;
    }

    public void setpCenter(Position pCenter) {
        this.pCenter = pCenter;
    }

    public void draw(PApplet pApplet) {
        pApplet.fill(color.getR(), color.getG(), color.getB());
        pApplet.circle(pCenter.getX(), pCenter.getY(), radius);
    }

    public void steuen(PApplet pApplet) {
        if (pApplet.keyPressed) {
            if (pCenter.getX() >= radius / 2
                    && pCenter.getX() <= Main.width - radius / 2
                    && pCenter.getY() >= radius / 2
                    && pCenter.getY() <= Main.height - radius / 2) {
                if (pApplet.keyCode == pApplet.RIGHT) {
                    pCenter.setX(pCenter.getX() + speed);
                } else if (pApplet.keyCode == pApplet.LEFT) {
                    pCenter.setX(pCenter.getX() - speed);
                } else if (pApplet.keyCode == pApplet.UP) {
                    pCenter.setY(pCenter.getY() - speed);
                } else if (pApplet.keyCode == pApplet.DOWN) {
                    pCenter.setY(pCenter.getY() + speed);
                }
            } else if (pCenter.getX() < radius / 2) pCenter.setX(radius / 2);
            else if (pCenter.getX() > Main.width - radius / 2) pCenter.setX(Main.width - radius / 2);
            else if (pCenter.getY() < radius / 2) pCenter.setY(radius / 2);
            else if (pCenter.getY() > Main.height - radius / 2) pCenter.setY(Main.height - radius / 2);

        }
    }


    @Override
    public boolean ausserhalbSpielFeld() {
        return false;
    }
}
