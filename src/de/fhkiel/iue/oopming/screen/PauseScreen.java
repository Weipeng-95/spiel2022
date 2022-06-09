package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import processing.core.PApplet;
import processing.core.PConstants;

public class PauseScreen extends Screen {
    public PauseScreen() {
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT / 2);
        setImage(Main.background);

    }

    @Override
    public void setup(PApplet pApplet) {
        setGameFont(pApplet.createFont("de/fhkiel/iue/oopming/font/thunderstrike3d.ttf", 50));
        pApplet.textAlign(PConstants.CENTER);
    }

    @Override
    public void showScreen(PApplet pApplet) {
        pApplet.image(Main.background, getX(), getY());
        pApplet.textFont(getGameFont());
        pApplet.textSize(35);

        if (isOnText(pApplet, "START", 590, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInGame = true;
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
