package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screen.GameScreen;
import de.fhkiel.iue.oopming.screen.PauseScreen;
import de.fhkiel.iue.oopming.screen.StartScreen;
import processing.core.PApplet;


public class Main extends PApplet {
    public static int WIDTH = 500;
    public static int HEIGHT = 800;
    public static int TIMER;

    public static boolean isInGame;
    public static boolean isInPause;
    public static boolean isInStart;

    GameScreen gameScreen = new GameScreen();
    PauseScreen pauseScreen = new PauseScreen();
    StartScreen startScreen = new StartScreen(this);

    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");
    }

    public void setup() {
        isInStart = true;
        gameScreen.setup(this);
        startScreen.setImage(loadImage("de/fhkiel/iue/oopming/images/hintergrundbild.png"));
        pauseScreen.setImage(loadImage("de/fhkiel/iue/oopming/images/hintergrundbild.png"));
    }

    @Override
    public void settings() {
        // Spielfeld
        size(500, 800);
    }

    @Override
    public void draw() {
        TIMER = millis();

        if (isInStart) {
            startScreen.showScreen(this);
        }
        if (isInGame) {
            gameScreen.showScreen(this);
        }
        if (isInPause) {
            pauseScreen.showScreen(this);
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


