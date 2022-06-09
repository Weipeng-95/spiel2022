package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.PlaySound;
import de.fhkiel.iue.oopming.screen.GameScreen;
import processing.core.PApplet;

public class ExploAnimation {
    private float x;
    private float y;

    private int refreshIndex = 0;

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

    public ExploAnimation(FlyingObject object) {
        this.x = object.getX();
        this.y = object.getY();
    }

    public void drawExplosion(PApplet pApplet) {
        pApplet.image(Main.explosion[refreshIndex], getX(), getY());
        refreshIndex++;
        if (refreshIndex  == Main.explosion.length - 1) {
            refreshIndex = 0;
            GameScreen.isExploded = false;
            new PlaySound("src/de/fhkiel/iue/oopming/Sound/explosionBgm.wav").start();
        }
    }

}
