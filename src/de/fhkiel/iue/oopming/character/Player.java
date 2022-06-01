package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;

public class Player extends FlyingObject {

    private int leben = 3;
    private int speed = 6;
    private int radius = 80;

    public Player() {
    }

    public void setup(PApplet pApplet) {
        setCenter(new Position(Main.WIDTH / 2, Main.HEIGHT - 100));
        setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Flugzeug.png"));
    }

    public void draw(PApplet pApplet) {
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
    }


    public void steuen(PApplet pApplet) {
        if (pApplet.keyPressed) {
            if (getCenter().getX() >= getImage().width / 2
                    && getCenter().getX() <= Main.WIDTH - getImage().width / 2
                    && getCenter().getY() >= getImage().height / 2
                    && getCenter().getY() <= Main.HEIGHT - getImage().height / 2) {
                if (pApplet.keyCode == pApplet.RIGHT) {
                    getCenter().setX(getCenter().getX() + speed);
                } else if (pApplet.keyCode == pApplet.LEFT) {
                    getCenter().setX(getCenter().getX() - speed);
                } else if (pApplet.keyCode == pApplet.UP) {
                    getCenter().setY(getCenter().getY() - speed);
                } else if (pApplet.keyCode == pApplet.DOWN) {
                    getCenter().setY(getCenter().getY() + speed);
                }
            } else if (getCenter().getX() < getImage().width / 2) getCenter().setX(getImage().width / 2);
            else if (getCenter().getX() > Main.WIDTH - getImage().width / 2)
                getCenter().setX(Main.WIDTH - getImage().width / 2);
            else if (getCenter().getY() < getImage().height / 2) getCenter().setY(getImage().height / 2);
            else if (getCenter().getY() > Main.HEIGHT - getImage().height / 2)
                getCenter().setY(Main.HEIGHT - getImage().height / 2);

        }
    }


    @Override
    public boolean ausserhalbSpielFeld() {
        return false;
    }
}
