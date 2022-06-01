package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;


public class Geschoss extends FlyingObject {
    PApplet pApplet;

    public Geschoss() {
    }

    public Geschoss(float x, float y) {
        setCenter(new Position(x, y));
    }

    public void move() {
        this.getCenter().setY(getCenter().getY() - 8);
    }

    public boolean istErschossen(Gegner g, Geschoss ge) {
        return (g.getCenter().getX() - g.getImage().width / 2 < ge.getCenter().getX() + ge.getImage().width / 2 &&
                g.getCenter().getX() + g.getImage().width / 2 > ge.getCenter().getX() - ge.getImage().width / 2 &&
                g.getCenter().getY() + g.getImage().height / 2 > ge.getCenter().getY() - ge.getImage().height / 2 &&
                g.getCenter().getY() - g.getImage().height / 2 < ge.getCenter().getY() + ge.getImage().height / 2);
    }

    @Override
    public void setupCharacter(PApplet pApplet) {
        setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Geschoss.png"));
    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return getCenter().getY() - getImage().height / 2 < 0;
    }
}
