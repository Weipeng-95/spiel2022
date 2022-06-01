package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;

public class Gegner extends FlyingObject {

    private float gradius = 30;

    public Gegner() {
        float x = (float) (Math.random() * ((Main.WIDTH - gradius) - gradius + 1) + gradius);
        float y = -(float) (Math.random() * (Main.WIDTH - gradius / 2));
        this.setCenter(new Position(x, y));
    }

    public void draw(PApplet pApplet) {
        pApplet.fill(200);
        pApplet.circle(getCenter().getX(), getCenter().getY(), gradius);
    }

    public float getGradius() {
        return gradius;
    }

    public void setGradius(int gradius) {
        this.gradius = gradius;
    }


    public void move() {
        this.getCenter().setY(getCenter().getY() + 5);
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return getCenter().getY() + gradius / 2 > Main.HEIGHT;
    }
}
