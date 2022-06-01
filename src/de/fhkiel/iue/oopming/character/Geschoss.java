package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;


public class Geschoss extends FlyingObject {

    public Geschoss() {
    }

    public Geschoss(int x, int y) {
        this.setCenter(new Position(x, y));
    }

    public void setup(PApplet pApplet) {
        setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Geschoss.png"));
    }

    public void draw(PApplet pApplet) {
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
    }


    public void move() {
        this.getCenter().setY(getCenter().getY() - 8);
    }

    public boolean istErschossen(Gegner g, Geschoss ge) {
        return (g.getCenter().getX() - g.getGradius() / 2 < ge.getCenter().getX() + ge.getImage().width / 2 &&
                g.getCenter().getX() + g.getGradius() / 2 > ge.getCenter().getX() - ge.getImage().width / 2 &&
                g.getCenter().getY() + g.getGradius() / 2 > ge.getCenter().getY() - ge.getImage().height / 2 &&
                g.getCenter().getY() - g.getGradius() / 2 < ge.getCenter().getY() + ge.getImage().height / 2);
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return getCenter().getY() - getImage().height / 2 < 0;
    }
}
