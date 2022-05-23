package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

public class Geschoss extends FlyingObject {
    private PImage geschossImage;
    private Position geCenter;
    private float geschossWidth;
    private float geschossHeight;

    public Geschoss(int x, int y) {
        geCenter = new Position(x, y);
    }

    public Position getGeCenter() {
        return geCenter;
    }

    public void setGeCenter(Position geCenter) {
        this.geCenter = geCenter;
    }

    public float getGeschossWidth() {
        return geschossWidth;
    }

    public void setGeschossWidth(float geschossWidth) {
        this.geschossWidth = geschossWidth;
    }

    public float getGeschossHeight() {
        return geschossHeight;
    }

    public void setGeschossHeight(float geschossHeight) {
        this.geschossHeight = geschossHeight;
    }

    public PImage getGeschossImage() {
        return geschossImage;
    }

    public void setGeschossImage(PImage geschossImage) {
        this.geschossImage = geschossImage;
    }

    public void setup(PApplet pApplet) {
        setGeschossImage(pApplet.loadImage("src/de/fhkiel/iue/oopming/image/Geschoss.png"));
        setGeschossHeight(getGeschossImage().height);
        setGeschossWidth(getGeschossImage().width);
    }

    public void draw(PApplet pApplet) {
        pApplet.image(geschossImage, geCenter.getX(), geCenter.getY());
    }


    public void move() {
        this.geCenter.setY(geCenter.getY() - 8);
    }

    public boolean istErschossen(Gegner g, Geschoss ge) {
        return (g.getGcenter().getX() - g.getGradius() / 2 < ge.getGeCenter().getX() + ge.getGeschossWidth() / 2 &&
                g.getGcenter().getX() + g.getGradius() / 2 > ge.getGeCenter().getX() - ge.getGeschossWidth() / 2 &&
                g.getGcenter().getY() + g.getGradius() / 2 > ge.getGeCenter().getY() - ge.getGeschossHeight() / 2 &&
                g.getGcenter().getY() - g.getGradius() / 2 < ge.getGeCenter().getY() + ge.getGeschossHeight() / 2);
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return geCenter.getY() - getGeschossHeight() / 2 < 0;
    }
}
