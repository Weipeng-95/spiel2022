package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class PauseScreen extends Screen {
    public PauseScreen() {
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT / 2);
    }

    @Override
    public void setup(PApplet pApplet) {
        super.setup(pApplet);
        setImage(Main.background);
    }

    @Override
    public void showScreen(PApplet pApplet) {
        pApplet.image(getImage(), getX(), getY());

        pApplet.textFont(getGameFont());
        pApplet.textSize(35);

        if (isOnText(pApplet, "CONTINUE", Main.HEIGHT / 2 - 50, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInGame = true;
                Main.isInStart = false;
                Main.isInPause = false;
            }
        } else {
            pApplet.fill(255);
        }
        pApplet.text("CONTINUE", Main.WIDTH / 2, Main.HEIGHT / 2 - 50);

        if (isOnText(pApplet, "RESTART", Main.HEIGHT / 2 + 30, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInGame = false;
                Main.isInStart = true;
                Main.isInPause = false;
            }
        } else {
            pApplet.fill(255);
        }
        pApplet.text("RESTART", Main.WIDTH / 2, Main.HEIGHT / 2 + 30);


        if (isOnText(pApplet, "EXIT", Main.HEIGHT / 2 + 110, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                pApplet.exit();
            }
        } else pApplet.fill(255);
        pApplet.text("EXIT", Main.WIDTH / 2, Main.HEIGHT / 2 + 110);
    }
}
