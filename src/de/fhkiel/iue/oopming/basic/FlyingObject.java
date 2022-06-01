package de.fhkiel.iue.oopming.basic;

import processing.core.PApplet;
import processing.core.PImage;

abstract public class FlyingObject {
    private int leben;
    private Position center;
    private PImage image;

    public FlyingObject() {
    }

    public FlyingObject(Position center, PImage image) {
        this.center = center;
        this.image = image;
    }

    public int getLeben() {
        return leben;
    }

    public void setLeben(int leben) {
        this.leben = leben;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    abstract public void setupCharacter(PApplet pApplet);

    abstract public void drawCharacter(PApplet pApplet);

    abstract public boolean ausserhalbSpielFeld();
}
