package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screens.GameScreen;
import processing.core.PApplet;

public class ExploAnimation {
    private float x;
    private float y;
    public static boolean isInDrawExplosion = false;
    private int refreshIndex = 0;

    private SoundEffect sound = new SoundEffect();


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

    public void drawExplosion(PApplet pApplet) {
        isInDrawExplosion = true;
        int index = refreshIndex / 2;
        pApplet.image(Main.explosion[index], getX(), getY()); // Dadruch werden alle Bilder geladen, damit die Explosionsanimation erzeugt werden kann
        refreshIndex++;
        if (index == Main.explosion.length - 1) {
            GameScreen.isExploded = false;
            sound.soundDoc("src/de/fhkiel/iue/oopming/resources/sounds/explosionBgm.wav");
            sound.playNoLoop();

            isInDrawExplosion = false;
        }
    }

}
