package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.ExploAnimation;
import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.PlaySound;
import de.fhkiel.iue.oopming.character.Bullet;
import de.fhkiel.iue.oopming.character.Enemy;
import de.fhkiel.iue.oopming.character.Player;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends Screen {
    protected static Player player;
    private static List enemys;

    public static int score;
//    private List bullets;

    private int imageMoveSpeed;
    private ExploAnimation explosion;
    public static boolean isExploded;
    private boolean isRight, isLeft, isUp, isDown, isShooted;


    @Override
    public void setup(PApplet pApplet) {
        super.setup(pApplet);
        enemys = new ArrayList<Enemy>();

        player = new Player();
        pApplet.textFont(getGameFont());
        setImage(Main.background);

        //Erstelle 8 Instanzen des Gegners
        for (int i = 0; i < 8; i++) {
            enemys.add(new Enemy(5, 1, 1, Main.enemy));
        }

    }

    @Override
    public void showScreen(PApplet pApplet) {

        drawHintergrund(pApplet);


        drawPlayer(pApplet);
        playerEnemyCollision(pApplet);

        enemysGenerator(pApplet);
        enemyBulletCollision(pApplet);

        showScore(pApplet);
        showLife(pApplet);
    }

    private void drawHintergrund(PApplet pApplet) {
        imageMoveSpeed = Main.TIMER / 10;
        for (int i = -imageMoveSpeed; i < Main.HEIGHT; i += getImage().height) {
            pApplet.copy(getImage(), 0, 0, getImage().width, Main.HEIGHT,
                    0, -i, getImage().width, Main.HEIGHT);
        }
    }

    private void showScore(PApplet pApplet) {
        pApplet.fill(255);
        pApplet.textSize(25);
        pApplet.text("score: " + player.getScore(), Main.WIDTH / 2, 40);
    }

    private void showLife(PApplet pApplet) {
        pApplet.fill(255);
        pApplet.textSize(25);
        pApplet.text("life: " + player.getLife(), Main.WIDTH - 75, 40);
    }

    private void drawPlayer(PApplet pApplet) {
        player.drawCharacter(pApplet);
        if (!player.outOfBounds()) {
            if (player.getX() <= player.getImage().width / 2)
                player.setX(player.getImage().width / 2);
            if (player.getX() >= Main.WIDTH - player.getImage().width / 2)
                player.setX(Main.WIDTH - player.getImage().width / 2);
            if (player.getY() <= player.getImage().height / 2)
                player.setY(player.getImage().height / 2);
            if (player.getY() >= Main.HEIGHT - player.getImage().height / 2)
                player.setY(Main.HEIGHT - player.getImage().height / 2);
        }
        player.bulletsGenerator(pApplet);
    }

    private void playerEnemyCollision(PApplet pApplet) {
        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemyTemp = (Enemy) enemys.get(i);
            if (player.hit(enemyTemp)) {
                enemys.remove(enemyTemp);
//                player.setX(Main.WIDTH / 2);
//                player.setY(Main.HEIGHT - 100);
                player.setLife(player.getLife() - 1);
                new PlaySound("src/de/fhkiel/iue/oopming/Sound/explosionBgm.wav").start();
                enemys.add(randomEnemy());
            }
        }
        if (player.getLife() == 0) {
            score = player.getScore();
            Main.isGameover = true;
            Main.isInGame = false;
        }
    }

    private void enemysGenerator(PApplet pApplet) {
        for (int i = 0; i < enemys.size(); i++) {
            //get Instance von Gegner
            Enemy enemyTemp = (Enemy) enemys.get(i);
            enemyTemp.drawCharacter(pApplet);
            enemyTemp.move();
            if (enemyTemp.outOfBounds()) {
                enemys.remove(enemyTemp); // Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                enemys.add(randomEnemy()); // ein neuer Gegner herstellen
            }
        }
    }

    private void enemyBulletCollision(PApplet pApplet) {
        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemyTemp = (Enemy) enemys.get(i);
            for (int j = 0; j < player.getBullets().size(); j++) {
                Bullet bullet = (Bullet) player.getBullets().get(j);
                if (enemyTemp.shootBy(bullet)) {
                    enemyTemp.setResistance(enemyTemp.getResistance() - 1);
                    explosion = new ExploAnimation(enemyTemp);
                    isExploded = true;
                    player.getBullets().remove(bullet);
                    if (enemyTemp.getResistance() < 1) {
                        player.setScore(player.getScore() + enemyTemp.getAward());
                        enemys.remove(enemyTemp);
                        enemys.add(randomEnemy());
                    }
                }
            }
            if (isExploded) {
                explosion.drawExplosion(pApplet);
            }
        }
    }

    public static void resetGame() {
        player.setLife(3);
        player.setScore(0);
        player.setX(Main.WIDTH / 2);
        player.setY(Main.HEIGHT - 100);
        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemyTemp = (Enemy) enemys.get(i);
            enemys.remove(enemyTemp);
            enemys.add(randomEnemy());
        }
        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet tempBullet = (Bullet) player.getBullets().get(i);
            player.getBullets().remove(tempBullet);
        }
    }

    public static Enemy randomEnemy() {
        int type = (int) (Math.random() * 15); // [0,20)
        if (type == 0) {
            return new Enemy(2, 5, 40, Main.bossEnemy);
        } else {
            return new Enemy(5, 1, 1, Main.enemy);
        }
    }

    public void playerInput() {

        if (isRight) {
            player.setX(player.getX() + player.getSpeed());
        }
        if (isLeft) {
            player.setX(player.getX() - player.getSpeed());
        }
        if (isUp) {
            player.setY(player.getY() - player.getSpeed());
        }
        if (isDown) {
            player.setY(player.getY() + player.getSpeed());
        }
        if (isShooted) {
            player.shoot();
        }
    }

    public void playerInputControl(PApplet pApplet, boolean flag) {
        if (pApplet.keyCode == 39) isRight = flag;
        if (pApplet.keyCode == 37) isLeft = flag;
        if (pApplet.keyCode == 38) isUp = flag;
        if (pApplet.keyCode == 40) isDown = flag;
        if (pApplet.key == 'z' || pApplet.key == 'Z') isShooted = flag;
    }
}


