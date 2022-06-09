package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import processing.core.PApplet;

public class Gegner extends FlyingObject {

    private int award;
    private int resistance;


    public Gegner() {
        // Initialisierung der zufälligen Position des Gegners
        // Zufallszahlen in einem bestimmten Bereich: int num = min + (int) (Math.random() * (max - min + 1));
        setSpeed(5);
        setX(50 / 2 + (float) (Math.random() * ((Main.WIDTH - 50 / 2) - 50 / 2 + 1)));
        setY(-(float) (Math.random() * (Main.WIDTH - 50 / 2)));
        setImage(Main.enemy);
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


    public boolean shootBy(Geschoss geschoss) {
        return (this.getX() - getImage().width / 2 < geschoss.getX() + geschoss.getImage().width / 2 &&
                this.getX() + getImage().width / 2 > geschoss.getX() - geschoss.getImage().width / 2 &&
                this.getY() + getImage().height / 2 > geschoss.getY() + geschoss.getImage().height / 2 &&
                this.getY() - getImage().height / 2 < geschoss.getY() - geschoss.getImage().height / 2);
    }

    @Override
    public void move() {
        setY(getY() + getSpeed());
    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        pApplet.image(Main.enemy, getX(), getY());
    }

    @Override
    public boolean outOfBounds() {
        return getY() - Main.enemy.height / 2 > Main.HEIGHT;
    }
}
