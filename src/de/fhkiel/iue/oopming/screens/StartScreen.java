package de.fhkiel.iue.oopming.screens;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.SoundEffect;
import processing.core.PApplet;

public class StartScreen extends Screen {
    private SoundEffect sound = new SoundEffect();

    public StartScreen() {
        // Hintergrundmusik wir geloopt
        sound.soundDoc("src/de/fhkiel/iue/oopming/resources/sounds/startBgm.wav");
        sound.playLoop();
    }

    @Override
    public void setup(PApplet pApplet) {
        super.setup(pApplet);
        setImage(Main.startBackground);
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT / 2);
    }

    @Override
    public void showScreen(PApplet pApplet) {
        pApplet.textFont(getGameFont());
        pApplet.image(getImage(), getX(), getY());
        pApplet.textSize(35);

        if (isOnText(pApplet, "START", 530, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInGame = true;
                Main.isInGameOver = false;
                Main.isInStart = false;
            }
        } else {
            pApplet.fill(255);
        }
        pApplet.text("START", Main.WIDTH / 2, 530);

        if (isOnText(pApplet, "EXIT", 585, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                pApplet.exit();
            }
        } else pApplet.fill(255);
        pApplet.text("EXIT", Main.WIDTH / 2, 585);

    }

}
