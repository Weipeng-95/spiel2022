package de.fhkiel.iue.oopming;

import processing.core.PImage;

abstract public class FlyingObject {
    private Position center;
    private Color color;

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    abstract public boolean ausserhalbSpielFeld();
}
