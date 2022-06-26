package de.fhkiel.iue.oopming.screens;

import de.fhkiel.iue.oopming.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class GameScreen extends Screen {
    private int backgroundMoveSpeed;
    public static boolean isExploded;
    protected static int showScore;
    protected static Player player;
    private ExploAnimation explosion;
    private static final ArrayList<Enemy> enemies = new ArrayList<>();


    @Override
    public void setup(PApplet pApplet) {
        super.setup(pApplet);
        player = new Player();
        pApplet.textFont(getGameFont());
        setImage(Main.background);

        for (int i = 0; i < 8; i++) {
            // 8 Gegner werden in Arraylist gespeichert
            enemies.add(new Enemy(5, 1, 1, Main.enemy1));
        }

    }

    @Override
    public void showScreen(PApplet pApplet) {
        // Game-Loop
        drawBackground(pApplet);

        drawPlayer(pApplet);
        playerEnemyCollision(pApplet);

        enemiesGenerator(pApplet);
        enemyBulletCollision(pApplet);

        showScore(pApplet);
        showLife(pApplet);
    }

    private void drawBackground(PApplet pApplet) {
        backgroundMoveSpeed++;
        for (int i = -backgroundMoveSpeed; i < Main.HEIGHT; i += getImage().height) {
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
        pApplet.image(player.getImage(), player.getX(), player.getY());
        player.move();
        player.animation();
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
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemyTemp = enemies.get(i);
            if (player.isHitBy(enemyTemp)) {
                explosion = new ExploAnimation(player);
                isExploded = true;
                enemies.remove(enemyTemp);
//                player.setX(Main.WIDTH / 2);
//                player.setY(Main.HEIGHT - 100);
                player.setLife(player.getLife() - 1);
                enemies.add(randomEnemy());
            }
        }
        if (isExploded) {
            explosion.drawExplosion(pApplet);
        }
        if (!ExploAnimation.isInDrawExplosion && player.getLife() <= 0) {
            showScore = player.getScore();
            Main.isInGameOver = true;
            Main.isInGame = false;

        }
//        System.out.println(ExploAnimation.isInDrawExplosion);
    }

    private void enemiesGenerator(PApplet pApplet) {
        for (int i = 0; i < enemies.size(); i++) {
            //get Instance von Gegner
            Enemy enemyTemp = enemies.get(i);
            pApplet.image(enemyTemp.getImage(), enemyTemp.getX(), enemyTemp.getY());
            enemyTemp.move();
            if (enemyTemp.outOfBounds()) {
                enemies.remove(enemyTemp); // Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                enemies.add(randomEnemy()); // ein neuer Gegner herstellen
            }
        }
    }

    private void enemyBulletCollision(PApplet pApplet) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemyTemp = enemies.get(i);
            for (int j = 0; j < player.getBullets().size(); j++) {
                Bullet bullet = (Bullet) player.getBullets().get(j);
                if (enemyTemp.shootBy(bullet)) {
                    enemyTemp.setResistance(enemyTemp.getResistance() - 1);
                    explosion = new ExploAnimation(enemyTemp);
                    isExploded = true;
                    player.getBullets().remove(bullet);
                    if (enemyTemp.getResistance() < 1) {
                        // Wenn Beständigkeit fortgeschrittener Gegner kleine als 1 ist, wird besiegt
                        player.setScore(player.getScore() + enemyTemp.getAward());
                        enemies.remove(enemyTemp);
                        enemies.add(randomEnemy());
                    }
                }
            }
            if (isExploded) {
                explosion.drawExplosion(pApplet);
            }
        }
    }

    public static void resetGame() {
        // Wenn Player kein Leben mehr, werden Spieldaten wiederhergestellt
        player.setLife(3);
        player.setScore(0);
        player.setX(Main.WIDTH / 2);
        player.setY(Main.HEIGHT - 100);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemyTemp = enemies.get(i);
            enemies.remove(enemyTemp);
            enemies.add(randomEnemy());
        }
        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet tempBullet = (Bullet) player.getBullets().get(i);
            player.getBullets().remove(tempBullet);
        }
    }

    public static Enemy randomEnemy() {
        // Verschiedene Gegner mit unterschiedliche Eingenschaften werden erzeugt, indem verschiedenen Parameter übergegeben werden
        int type = (int) (Math.random() * 15);
        if (type == 0) {
            // Fortgeschrittene Gegner werden mit niedriger Möglichkeit erzeugt
            return new Enemy(2, 5, 25, Main.bossEnemy);
        } else if (type == 1) return new Enemy(3, 2, 8, Main.enemy2);
        else {
            // Normale Gegner werden mit größter Möglichkeit erzeugt
            return new Enemy(5, 1, 1, Main.enemy1);
        }
    }

}


