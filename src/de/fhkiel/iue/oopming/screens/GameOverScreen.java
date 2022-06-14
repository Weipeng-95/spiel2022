package de.fhkiel.iue.oopming.screens;

import de.fhkiel.iue.oopming.Main;
import processing.core.PApplet;

public class GameOverScreen extends Screen {

    public GameOverScreen() {
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT / 2);
    }

    @Override
    public void setup(PApplet pApplet) {
        super.setup(pApplet);
        setImage(Main.background);
    }

    @Override
    public void showScreen(PApplet pApplet) {
        pApplet.image(getImage(), getX(), getY());
        pApplet.textFont(getGameFont());

        pApplet.textSize(50);
        pApplet.fill(255);
        pApplet.text("GAMEOVER", Main.WIDTH / 2, Main.HEIGHT / 2 - 150);
        pApplet.textSize(40);
        pApplet.fill(147, 207, 222);
        pApplet.text("you got " + GameScreen.showScore + " points", Main.WIDTH / 2, Main.HEIGHT / 2 - 50);

        GameScreen.resetGame();

        pApplet.textSize(35);
        if (isOnText(pApplet, "RESTART", Main.HEIGHT / 2 + 120, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                Main.isInStart = false;
                Main.isInGameOver = false;
                Main.isInGame = true;
            }
        } else {
            pApplet.fill(255);
        }
        pApplet.text("RESTART", Main.WIDTH / 2, Main.HEIGHT / 2 + 120);


        if (isOnText(pApplet, "EXIT", Main.HEIGHT / 2 + 180, 35)) {
            pApplet.fill(147, 207, 222);
            if (pApplet.mousePressed) {
                pApplet.exit();
            }
        } else pApplet.fill(255);
        pApplet.text("EXIT", Main.WIDTH / 2, Main.HEIGHT / 2 + 180);
    }
}
