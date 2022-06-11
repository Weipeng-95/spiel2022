package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screen.GameScreen;
import processing.core.PApplet;

public class ExploAnimation {
    private float x;
    private float y;
    public static boolean isInDrawExplosion = false;
    private int refreshIndex = 0;

    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ExploAnimation(FlyingObject object) {
        this.x = object.getX();
        this.y = object.getY();
    }

    public void drawExplosion(PApplet pApplet, String soundFiel) {
        isInDrawExplosion = true;
        int index = refreshIndex / 2;
        pApplet.image(Main.explosion[index], getX(), getY());
        refreshIndex++;
        if (index == Main.explosion.length - 1) {
            GameScreen.isExploded = false;
            new PlaySound(soundFiel).start();
            isInDrawExplosion = false;
        }
    }

}
