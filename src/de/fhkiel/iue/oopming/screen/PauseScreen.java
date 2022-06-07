package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;
import processing.core.PConstants;

public class PauseScreen extends Screen{
    public PauseScreen() {
        setCenter(new Position(Main.WIDTH / 2, Main.HEIGHT / 2));
    }

    @Override
    public void setup(PApplet pApplet) {
        setImage(pApplet.loadImage("de/fhkiel/iue/oopming/images/hintergrundbild.png"));
        setGameFont(pApplet.createFont("de/fhkiel/iue/oopming/font/thunderstrike3d.ttf", 50));
        pApplet.textAlign(PConstants.CENTER);
    }

    @Override
    public void showScreen(PApplet pApplet) {
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
//        pApplet.background(179, 134, 200);
        pApplet.textSize(30);
        pApplet.text("in pause", pApplet.width / 2, pApplet.height / 2);
    }
}
