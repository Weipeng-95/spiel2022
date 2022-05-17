package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Gegner extends FlyingObject {
    private Position gCenter;
    private int gRadius = 30;

    public Gegner() {
        gCenter = new Position((int) (Math.random() * ((Main.WIDTH - gRadius) - gRadius + 1) + gRadius),
                -(int) (Math.random() * (Main.WIDTH - gRadius / 2)));
    }

    public int getGradius() {
        return gRadius;
    }

    public void setGradius(int gradius) {
        this.gRadius = gradius;
    }

    public Position getgCenter() {
        return gCenter;
    }

    public void setgCenter(Position gCenter) {
        this.gCenter = gCenter;
    }

    public void draw(PApplet pApplet) {
        pApplet.fill(120);
        pApplet.circle(gCenter.getX(), gCenter.getY(), gRadius);
    }


    public void move() {
        this.gCenter.setY(gCenter.getY() + 5);
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return gCenter.getY() + gRadius / 2 > Main.HEIGHT;
    }
}
