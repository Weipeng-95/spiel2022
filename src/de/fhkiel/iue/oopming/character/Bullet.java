package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import processing.core.PApplet;


public class Bullet extends FlyingObject {

    public Bullet() {
    }

    public Bullet(float x, float y) {
        setSpeed(8);
        setX(x);
        setY(y);
        setImage(Main.bullet);
    }

    public void move() {
        this.setY(getY() - getSpeed());
    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        pApplet.image(Main.bullet, getX(), getY());
    }

    @Override
    public boolean outOfBounds() {
        return getY() - Main.bullet.height / 2 < 0;
    }
}
