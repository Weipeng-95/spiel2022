package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screen.GameScreen;
import de.fhkiel.iue.oopming.screen.PauseScreen;
import processing.core.PApplet;


public class Main extends PApplet {
    public static int WIDTH = 500;
    public static int HEIGHT = 800;

    public static boolean isInGame = true;
    public static boolean isInPause;

    public GameScreen gameScreen = new GameScreen(this);
    public PauseScreen pauseScreen = new PauseScreen(this);


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


