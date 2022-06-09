package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.PlaySound;
import processing.core.PApplet;
import processing.core.PConstants;

public class StartScreen extends Screen {


    public StartScreen() {
        super();
        setImage(Main.startBackground);
        new PlaySound("src/de/fhkiel/iue/oopming/Sound/startBgm.wav").start();
    }

    @Override
    public void setup(PApplet pApplet) {
        setGameFont(pApplet.createFont("de/fhkiel/iue/oopming/font/thunderstrikehalf.ttf", 50));
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT / 2);
    }

    @Override
    public void showScreen(PApplet pApplet) {
        pApplet.textFont(getGameFont());
        pApplet.image(Main.startBackground, getX(), getY());
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
