package de.fhkiel.iue.oopming;

import processing.core.PImage;

public class Enemy extends FlyingObject {
    private int award;
    private int resistance;

    public Enemy(int speed, int award, int resistance, PImage image) {
        // Initialisierung der zufälligen Position des Gegners
        // Zufallszahlen in einem bestimmten Bereich: int num = min + (int) (Math.random() * (max - min + 1));
        setSpeed(speed);
        setAward(award);
        setResistance(resistance);
        setX(50 / 2 + (float) (Math.random() * ((Main.WIDTH - 50 / 2) - 50 / 2 + 1)));
        setY(-(float) (Math.random() * Main.HEIGHT));
        setImage(image);
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public boolean shootBy(Bullet bullet) {
        // Erkannt, wenn Gegner mit Geschoss kollidierte
        return (this.getX() - getImage().width / 2 < bullet.getX() + bullet.getImage().width / 2 &&
                this.getX() + getImage().width / 2 > bullet.getX() - bullet.getImage().width / 2 &&
                this.getY() + getImage().height / 2 > bullet.getY() + bullet.getImage().height / 2 &&
                this.getY() - getImage().height / 2 < bullet.getY() - bullet.getImage().height / 2);
    }

    @Override
    public void move() {
        setY(getY() + getSpeed());
    }

    @Override
    public boolean outOfBounds() {
        return getY() - getImage().height / 2 > Main.HEIGHT;
    }
}
