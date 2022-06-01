package de.fhkiel.iue.oopming.screen;

import processing.core.PApplet;

public class PauseScreen {
    public PauseScreen(PApplet pApplet) {
    }

    public void pauseScreen(PApplet pApplet) {
        pApplet.background(179, 134, 200);
        pApplet.textSize(30);
        pApplet.textMode(pApplet.CENTER);
        pApplet.text("in pause", pApplet.width / 2, pApplet.height / 2);
    }
}
