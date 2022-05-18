package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Main extends PApplet {
    public static boolean isInGame = true;
    public static boolean isInPause;

     public GameScreen gameScreen = new GameScreen(this);
    public PauseScreen pauseScreen = new PauseScreen(this);


    static int width = 500;
    static int height = 800;





    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");
    }

    public void setup() {

        gameScreen.setup(this);
    }

    @Override
    public void settings() {
        // Spielfeld
        size(500, 800);

//        geschoss.setGeCenter(new Position(0,0));
    }

    @Override
    public void draw() {

        if (isInGame) {
            gameScreen.gameScreen(this);
        }
        if (isInPause) {
            pauseScreen.pauseScreen(this);
        }

    }
    public void keyPressed() {
        if (key == 's') {
            isInGame = true;
            isInPause = false;
        }
        if (key == 'p') {
            isInPause = true;
            isInGame = false;
        }
    }
}


