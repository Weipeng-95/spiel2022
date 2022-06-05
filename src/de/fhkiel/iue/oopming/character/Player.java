package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;

public class Player extends FlyingObject {
    private int speed = 5;

    public Player() {
        setCenter(new Position(Main.WIDTH / 2, Main.HEIGHT - 100));
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        if (Main.TIMER % 2 == 0) setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Flugzeug-0.png"));
        else if (Main.TIMER % 2 == 1) setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Flugzeug-1.png"));
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
    }


    public void flightRange() {

//        return getCenter().getX() >= getImage().width / 2
//                && getCenter().getX() <= Main.WIDTH - getImage().width / 2
//                && getCenter().getY() >= getImage().height / 2
//                && getCenter().getY() <= Main.HEIGHT - getImage().height / 2;
        if (getCenter().getX() < getImage().width / 2)
            getCenter().setX(getImage().width / 2);
        if (getCenter().getX() > Main.WIDTH - getImage().width / 2)
            getCenter().setX(Main.WIDTH - getImage().width / 2);
        if (getCenter().getY() < getImage().height / 2)
            getCenter().setY(getImage().height / 2);
        if (getCenter().getY() > Main.HEIGHT - getImage().height / 2)
            getCenter().setY(Main.HEIGHT - getImage().height / 2);
    }

    public void schiessen() {

    }

    @Override
    public void setupCharacter(PApplet pApplet) {
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return false;
    }
}
