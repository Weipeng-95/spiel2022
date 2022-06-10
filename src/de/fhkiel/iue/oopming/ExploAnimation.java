package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.PlaySound;
import de.fhkiel.iue.oopming.screen.GameScreen;
import processing.core.PApplet;

public class ExploAnimation {
    private float x;
    private float y;

    public static boolean isInDrawExplosion = false;

    private int index = 0;
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

    public void drawExplosion(PApplet pApplet, String soundFiel) {
        isInDrawExplosion = true;
        index = refreshIndex / 2;
        pApplet.image(Main.explosion[index], getX(), getY());
        refreshIndex++;
        if (index == Main.explosion.length - 1) {
            index = 0;
            GameScreen.isExploded = false;
            new PlaySound(soundFiel).start();
            isInDrawExplosion = false;
        }
    }

}
