package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.PlaySound;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;
import processing.core.PConstants;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class StartScreen extends Screen {
    {

//        new PlaySound("src/de/fhkiel/iue/oopming/Sound/startBgm.wav").start();

        soundDoc("src/de/fhkiel/iue/oopming/Sound/startBgm.wav");
        try {
            getBgm().open(getClip());
            getBgm().loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StartScreen() {
    }

    @Override
    public void setup(PApplet pApplet) {
        setCenter(new Position(Main.WIDTH / 2, Main.HEIGHT / 2));
        setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/Startscreen.png"));
        setGameFont(pApplet.createFont("de/fhkiel/iue/oopming/font/thunderstrikehalf.ttf", 50));
        pApplet.textAlign(PConstants.CENTER);

    }

    @Override
    public void showScreen(PApplet pApplet) {

        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
        pApplet.textFont(getGameFont());
        pApplet.textSize(35);
        soundDoc("src/de/fhkiel/iue/oopming/Sound/buttomBgm.wav");


        if (isOnText(pApplet, "START", 590, 35)) {

            try {
                getBgm().open(getClip());
                getBgm().start();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInGame = true;
                Main.isInStart = false;
            }
        } else {
            pApplet.fill(255);
            getBgm().stop();
        }
        pApplet.text("START", Main.WIDTH / 2, 590);


        if (isOnText(pApplet, "EXIT", 650, 35)) {
            try {
                getBgm().open(getClip());
                getBgm().start();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                pApplet.exit();
            }
        } else pApplet.fill(255);
        pApplet.text("EXIT", Main.WIDTH / 2, 650);

    }

}
