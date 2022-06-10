package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import processing.core.PApplet;
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
    public void drawCharacter(PApplet pApplet) {
        pApplet.image(getImage(), getX(), getY());
    }

    @Override
    public boolean outOfBounds() {
        return getY() - getImage().height / 2 > Main.HEIGHT;
    }
}
