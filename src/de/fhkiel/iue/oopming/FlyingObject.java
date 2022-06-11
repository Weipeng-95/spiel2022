package de.fhkiel.iue.oopming;

import processing.core.PImage;

abstract public class FlyingObject {
    private float x;
    private float y;
    private int speed;
    private PImage image;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    abstract public void move();

    abstract public boolean outOfBounds();
}
