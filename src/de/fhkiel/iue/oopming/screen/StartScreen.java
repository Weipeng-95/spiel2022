package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;

public class StartScreen extends Screen {
    public StartScreen(PApplet pApplet) {
        setCenter(new Position(Main.WIDTH / 2, Main.HEIGHT / 2));
    }

    @Override
    public void schowScreen(PApplet pApplet) {
        pApplet.image(getImage(), getCenter().getX(), getCenter().getY());
//        pApplet.background(179, 134, 200);
        pApplet.textSize(30);
        pApplet.text("Start", pApplet.width / 2, pApplet.height / 2);
    }
}
