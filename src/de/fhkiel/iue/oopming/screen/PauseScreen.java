package de.fhkiel.iue.oopming.screen;

import processing.core.PApplet;

public class PauseScreen extends Screen{
    public PauseScreen() {
    }
    @Override
    public void schowScreen(PApplet pApplet) {
        pApplet.background(179, 134, 200);
        pApplet.textSize(30);
        pApplet.text("in pause", pApplet.width / 2, pApplet.height / 2);
    }
}
