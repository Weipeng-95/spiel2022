package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Gegner extends FlyingObject {
    private Position gcenter;
    private Color gcolor;
    private int gradius = 30;

    public Gegner() {
        int feld = (int) (Math.random() * ((Main.width - gradius) - gradius + 1) + gradius);
        gcenter = new Position(feld, -(int) (Math.random() * (Main.width - gradius / 2)));
    }

    public void draw(PApplet pApplet) {
        pApplet.fill(120);
        pApplet.circle(gcenter.getX(), gcenter.getY(), gradius);
    }

    public int getGradius() {
        return gradius;
    }

    public void setGradius(int gradius) {
        this.gradius = gradius;
    }

    public Position getGcenter() {
        return gcenter;
    }

    public void setGcenter(Position gcenter) {
        this.gcenter = gcenter;
    }

    public void move() {
        this.gcenter.setY(gcenter.getY() + 5);
    }

    @Override
    public boolean ausserhalbSpielFeld() {
        return gcenter.getY() + gradius / 2 > Main.height;
    }
}
