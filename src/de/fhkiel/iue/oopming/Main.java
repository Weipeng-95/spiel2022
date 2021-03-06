package de.fhkiel.iue.oopming;

import de.fhkiel.iue.oopming.screens.GameOverScreen;
import de.fhkiel.iue.oopming.screens.GameScreen;
import de.fhkiel.iue.oopming.screens.PauseScreen;
import de.fhkiel.iue.oopming.screens.StartScreen;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    public static boolean isInGame, isInPause, isInStart, isInGameOver;

    public static PImage player0;
    public static PImage player1;
    public static PImage bullet;
    public static PImage enemy1;
    public static PImage enemy2;
    public static PImage bossEnemy;
    public static PImage background;
    public static PImage startBackground;
    public static PImage[] explosion = new PImage[40];

    GameScreen gameScreen = new GameScreen();
    PauseScreen pauseScreen = new PauseScreen();
    StartScreen startScreen = new StartScreen();
    GameOverScreen gameOverScreen = new GameOverScreen();

    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");
    }

    @Override
    public void setup() {
        isInStart = true;
        player0 = loadImage("de/fhkiel/iue/oopming/resources/images/Flugzeug-0.png");
        player1 = loadImage("de/fhkiel/iue/oopming/resources/images/Flugzeug-1.png");
        enemy1 = loadImage("de/fhkiel/iue/oopming/resources/images/Gegner-1.png");
        enemy2 = loadImage("de/fhkiel/iue/oopming/resources/images/Gegner-2.png");
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
        // Gro??e des Spielfelds wird festgestellt
        size(WIDTH, HEIGHT);
    }

    @Override
    public void draw() {
        //Screen-Controller
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

// mit den Funktionen keyPressed() und keyReleased() k??nnen Charakterbewegung und Geschossschie??en gleichzeitig durchgef??hrt werden.
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


