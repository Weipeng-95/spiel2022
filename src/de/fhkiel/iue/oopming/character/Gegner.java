package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;

import java.util.List;

public class Gegner extends FlyingObject {

    public Gegner() {
        // Initialisierung der zufälligen Position des Gegners
        // Zufallszahlen in einem bestimmten Bereich: int num = min + (int) (Math.random() * (max - min + 1));
        float x = 50 / 2 + (float) (Math.random() * ((Main.WIDTH - 50 / 2) - 50 / 2 + 1));
        float y = -(float) (Math.random() * (Main.WIDTH - 50 / 2));
        setCenter(new Position(x, y));
    }


    public void move() {
        this.getCenter().setY(getCenter().getY() + 5);
    }


    @Override
    public void setupCharacter(PApplet pApplet) {

    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        if (Main.TIMER % 2 == 0) setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Gegner-0.png"));
        else if (Main.TIMER % 2 == 1) setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Gegner-1.png"));
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return getCenter().getY() + getImage().height / 2 > Main.HEIGHT;
    }
}
