package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

public class Geschoss extends FlyingObject {
    private PImage geschossImage;
    private Position geCenter;

    public Geschoss(int x, int y) {
        geCenter = new Position(x, y);
    }


    public Position getGeCenter() {
        return geCenter;
    }

    public void setGeCenter(Position geCenter) {
        this.geCenter = geCenter;
    }

    public void draw(PApplet pApplet) {
        geschossImage = pApplet.loadImage("src/de/fhkiel/iue/oopming/image/Geschoss.png");
        pApplet.image(geschossImage, geCenter.getX(), geCenter.getY());
    }

    public void move() {
        this.geCenter.setY(geCenter.getY() - 8);
    }

    public boolean istErschossen(Gegner g, Geschoss ge) {
        return (g.getgCenter().getX() - g.getGradius() / 2 < ge.getGeCenter().getX() - ge.geschossImage.width / 2 &&
                g.getgCenter().getX() + g.getGradius() / 2  > ge.getGeCenter().getX() + ge.geschossImage.width / 2 &&
                g.getgCenter().getY() + g.getGradius() / 2 > ge.getGeCenter().getY() + ge.geschossImage.height / 2);
    }
    @Override
    public boolean ausserhalbSpielFeld() {
        return geCenter.getY() - geschossImage.height / 2 < 0;
    }
}
