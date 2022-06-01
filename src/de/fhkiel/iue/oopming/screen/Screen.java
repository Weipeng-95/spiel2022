package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;
import processing.core.PImage;

abstract public class Screen {
    private PImage image;
    private Position center;

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    abstract public void schowScreen(PApplet pApplet);
}
