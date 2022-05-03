package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

public class Geschoss extends FlyingObject {
    private PImage geschossImage;
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
//        pApplet.fill(255);
//        pApplet.ellipse(geCenter.getX(), geCenter.getY(), 10, 10);
        geschossImage = pApplet.loadImage("src/de/fhkiel/iue/oopming/image/Geschoss.png");
        pApplet.image(geschossImage, geCenter.getX(), geCenter.getY());
    }

    public void move() {
        this.geCenter.setY(geCenter.getY() - 8);
    }

    public boolean istErschossen(Gegner g, Geschoss ge) {
        return (g.getGcenter().getX() - g.getGradius() / 2 < ge.getGeCenter().getX() - ge.getGeRadius() / 2 &&
                g.getGcenter().getX() + g.getGradius() / 2  > ge.getGeCenter().getX() + ge.getGeRadius() / 2 &&
                g.getGcenter().getY() + g.getGradius() / 2 > ge.getGeCenter().getY() + ge.getGeRadius() / 2);
    }
    @Override
    public boolean ausserhalbSpielFeld() {
        return geCenter.getY() - geRadius / 2 < 0;
    }
}
