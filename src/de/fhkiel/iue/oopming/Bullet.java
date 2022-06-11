package de.fhkiel.iue.oopming;

public class Bullet extends FlyingObject {

    public Bullet(float x, float y) {
        setSpeed(8);
        setX(x);
        setY(y);
        setImage(Main.bullet);
    }

    @Override
    public void move() {
        this.setY(getY() - getSpeed());
    }

    @Override
    public boolean outOfBounds() {
        return getY() - Main.bullet.height / 2 < 0;
    }
}
