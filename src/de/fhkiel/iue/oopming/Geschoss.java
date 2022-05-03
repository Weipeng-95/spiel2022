package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Geschoss extends FlyingObject {
    private Position geCenter;
    private int geRadius = 4;

    public Geschoss(int x, int y) {
        geCenter = new Position(x, y);
    }

    public int getGeRadius() {
        return geRadius;
    }

    public void setGeRadius(int geRadius) {
        this.geRadius = geRadius;
    }

    public Position getGeCenter() {
        return geCenter;
    }

    public void setGeCenter(Position geCenter) {
        this.geCenter = geCenter;
    }

    public void draw(PApplet pApplet) {
        pApplet.fill(255);
        pApplet.ellipse(geCenter.getX(), geCenter.getY(), 10, 10);
    }

    public void move() {
        this.geCenter.setY(geCenter.getY() - 8);
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return geCenter.getY() - geRadius / 2 < 0;
    }
}
