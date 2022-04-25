package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Color {
    private int r;
    private int g;
    private int b;
    private int alpha;

    public Color(int r, int g, int b, int alpha) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = 255;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void fill(PApplet pApplet) {
        pApplet.fill(r, g, b, alpha);
    }
}
