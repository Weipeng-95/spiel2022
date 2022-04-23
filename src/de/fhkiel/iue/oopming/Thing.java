package de.fhkiel.iue.oopming;

import processing.core.PApplet;

abstract public class Thing {
    private Position position;
    private Color color;

    public Thing() {
        position = new Position(0, 0);
        color = new Color(0, 0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    abstract public void draw(PApplet pApplet);
}
