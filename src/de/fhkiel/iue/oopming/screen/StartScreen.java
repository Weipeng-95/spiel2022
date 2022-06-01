package de.fhkiel.iue.oopming.screen;

import processing.core.PApplet;

public class StartScreen {
    public StartScreen(PApplet pApplet) {

    }
    public void startScreen(PApplet pApplet){
        pApplet.background(179, 134, 200);
        pApplet.textSize(30);
        pApplet.textMode(pApplet.CENTER);
        pApplet.text("Start", pApplet.width / 2, pApplet.height / 2);
    }
}
