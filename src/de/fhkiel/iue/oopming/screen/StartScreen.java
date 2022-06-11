package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.PlaySound;
import processing.core.PApplet;

public class StartScreen extends Screen {
    public StartScreen() {
        new PlaySound("src/de/fhkiel/iue/oopming/Sound/startBgm.wav").start();
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

        if (isOnText(pApplet, "START", 590, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInGame = true;
                Main.isGameover = false;
                Main.isInStart = false;
            }
        } else {
            pApplet.fill(255);
        }
        pApplet.text("START", Main.WIDTH / 2, 590);

        if (isOnText(pApplet, "EXIT", 650, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                pApplet.exit();
            }
        } else pApplet.fill(255);
        pApplet.text("EXIT", Main.WIDTH / 2, 650);

    }

}
