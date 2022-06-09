package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screen.GameScreen;
import de.fhkiel.iue.oopming.screen.PauseScreen;
import de.fhkiel.iue.oopming.screen.StartScreen;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.IntList;


public class Main extends PApplet {
    public static int WIDTH = 500;
    public static int HEIGHT = 800;
    public static int TIMER;
    IntList pKeys = new IntList();
    public static boolean isInGame;
    public static boolean isInPause;
    public static boolean isInStart;

    public static PImage player0;
    public static PImage player1;
    public static PImage bullet;
    public static PImage enemy;
    public static PImage background;
    public static PImage startBackground;
    public static PImage[] explosion;


    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");
    }

    @Override
    public void setup() {
        isInStart = true;
        player0 = loadImage("de/fhkiel/iue/oopming/images/Flugzeug-0.png");
        player1 = loadImage("de/fhkiel/iue/oopming/images/Flugzeug-1.png");
        enemy = loadImage("de/fhkiel/iue/oopming/images/Gegner-1.png");
        bullet = loadImage("de/fhkiel/iue/oopming/images/Geschoss.png");
        startBackground = loadImage("de/fhkiel/iue/oopming/images/Startscreen.png");
        background = loadImage("de/fhkiel/iue/oopming/images/hintergrundbild.png");
        explosion = new PImage[12];
        for (int i = 0; i < explosion.length; i++) {
            explosion[i] = loadImage("de/fhkiel/iue/oopming/images/explosion1/explosion_" + i + ".png");
        }


        gameScreen.setup(this);
        startScreen.setup(this);
        pauseScreen.setup(this);
    }

    GameScreen gameScreen = new GameScreen();
    PauseScreen pauseScreen = new PauseScreen();
    StartScreen startScreen = new StartScreen();

    @Override
    public void settings() {
        // Spielfeld
        size(WIDTH, HEIGHT);
    }

    @Override
    public void draw() {
        TIMER = millis();

        if (isInStart) {
            startScreen.showScreen(this);
        }
        if (isInGame) {
            gameScreen.showScreen(this);
            gameScreen.playerInput(this);
        }
        if (isInPause) {
            pauseScreen.showScreen(this);
        }

    }


    public void keyPressed() {
        gameScreen.playerInputControl(this, true);

        if (key == 's') {
            isInGame = true;
            isInPause = false;
        }
        if (key == 'p') {
            isInPause = true;
            isInGame = false;
        }
    }

    public void keyReleased() {
        gameScreen.playerInputControl(this, false);
    }

}


