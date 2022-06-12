package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screen.GameScreen;
import de.fhkiel.iue.oopming.screen.GameOverScreen;
import de.fhkiel.iue.oopming.screen.PauseScreen;
import de.fhkiel.iue.oopming.screen.StartScreen;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;

    GameScreen gameScreen = new GameScreen();
    PauseScreen pauseScreen = new PauseScreen();
    StartScreen startScreen = new StartScreen();
    GameOverScreen gameOverScreen = new GameOverScreen();

    public static boolean isInGame, isInPause, isInStart, isInGameOver;
    public static PImage player0;
    public static PImage player1;
    public static PImage bullet;
    public static PImage enemy;
    public static PImage bossEnemy;
    public static PImage background;
    public static PImage startBackground;
    public static PImage[] explosion = new PImage[40];


    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");
    }

    @Override
    public void setup() {
        isInStart = true;
        player0 = loadImage("de/fhkiel/iue/oopming/resources/images/Flugzeug-0.png");
        player1 = loadImage("de/fhkiel/iue/oopming/resources/images/Flugzeug-1.png");
        enemy = loadImage("de/fhkiel/iue/oopming/resources/images/Gegner-1.png");
        bossEnemy = loadImage("de/fhkiel/iue/oopming/resources/images/bossEnemyBgm.png");
        bullet = loadImage("de/fhkiel/iue/oopming/resources/images/Geschoss.png");
        startBackground = loadImage("de/fhkiel/iue/oopming/resources/images/Startscreen.png");
        background = loadImage("de/fhkiel/iue/oopming/resources/images/hintergrundbild.png");
        for (int i = 0; i < explosion.length; i++) {
            explosion[i] = loadImage("de/fhkiel/iue/oopming/resources/images/explosion/explosion_" + i + ".png");
        }

        gameScreen.setup(this);
        startScreen.setup(this);
        pauseScreen.setup(this);
        gameOverScreen.setup(this);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void draw() {
        if (isInStart) {
            startScreen.showScreen(this);
        }
        if (isInGame) {
            gameScreen.showScreen(this);
        }
        if (isInPause) {
            pauseScreen.showScreen(this);
        }
        if (isInGameOver) {
            gameOverScreen.showScreen(this);
        }
    }


    public void keyPressed() {
        Player.inputControl(this, true);
        if (keyCode == ENTER) {
            isInGame = true;
            isInStart = false;
            isInPause = false;
            isInGameOver = false;
        }
        if (key == 'p' || key == 'P') {
            isInPause = true;
            isInGame = false;
        }
    }

    public void keyReleased() {
        Player.inputControl(this, false);
    }

}


