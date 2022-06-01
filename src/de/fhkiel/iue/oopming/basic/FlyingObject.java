package de.fhkiel.iue.oopming.basic;

import processing.core.PImage;

abstract public class FlyingObject {

    private Position center;
    private PImage image;

    public FlyingObject() {
    }

    public FlyingObject(Position center, PImage image) {
        this.center = center;
        this.image = image;
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

    abstract public boolean ausserhalbSpielFeld();
}
