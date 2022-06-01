package de.fhkiel.iue.oopming.screen;

import processing.core.PApplet;

public class StartScreen extends Screen{
    public StartScreen() {
    }
    @Override
    public void schowScreen(PApplet pApplet) {
        pApplet.background(179, 134, 200);
        pApplet.textSize(30);
        pApplet.text("Start", pApplet.width / 2, pApplet.height / 2);
    }
}
