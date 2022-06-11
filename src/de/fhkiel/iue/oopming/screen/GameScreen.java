package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.ExploAnimation;
import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.Bullet;
import de.fhkiel.iue.oopming.Enemy;
import de.fhkiel.iue.oopming.Player;
import processing.core.PApplet;

import java.util.ArrayList;

public class GameScreen extends Screen {
    protected static Player player;
    private static ArrayList<Enemy> enemys = new ArrayList<>();
    protected static int showScore;
    private ExploAnimation explosion;
    public static boolean isExploded;
    private boolean isRight, isLeft, isUp, isDown, isShooted;


    @Override
    public void setup(PApplet pApplet) {
        super.setup(pApplet);
        player = new Player();
        pApplet.textFont(getGameFont());
        setImage(Main.background);

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
        int imageMoveSpeed = Main.TIMER / 10;
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
        pApplet.image(player.getImage(), player.getX(), player.getY());
        player.move();
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
            Enemy enemyTemp = enemys.get(i);
            if (player.hitBy(enemyTemp)) {
                explosion = new ExploAnimation(player);
                isExploded = true;
                enemys.remove(enemyTemp);
//                player.setX(Main.WIDTH / 2);
//                player.setY(Main.HEIGHT - 100);
                player.setLife(player.getLife() - 1);
                enemys.add(randomEnemy());
            }
        }
        if (isExploded) {
            explosion.drawExplosion(pApplet, "src/de/fhkiel/iue/oopming/Sound/explosionBgm.wav");
        }
        if (!ExploAnimation.isInDrawExplosion && player.getLife() <= 0) {
            showScore = player.getScore();
            Main.isGameover = true;
            Main.isInGame = false;

        }
//        System.out.println(ExploAnimation.isInDrawExplosion);
    }

    private void enemysGenerator(PApplet pApplet) {
        for (int i = 0; i < enemys.size(); i++) {
            //get Instance von Gegner
            Enemy enemyTemp = enemys.get(i);
            pApplet.image(enemyTemp.getImage(), enemyTemp.getX(), enemyTemp.getY());
            enemyTemp.move();
            if (enemyTemp.outOfBounds()) {
                enemys.remove(enemyTemp); // Wenn Gegner außerhalber dem Spielfeld, löscht der Gegner
                enemys.add(randomEnemy()); // ein neuer Gegner herstellen
            }
        }
    }

    private void enemyBulletCollision(PApplet pApplet) {
        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemyTemp = enemys.get(i);
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
                explosion.drawExplosion(pApplet, "src/de/fhkiel/iue/oopming/Sound/explosionBgm.wav");
            }
        }
    }

    public static void resetGame() {
        player.setLife(3);
        player.setScore(0);
        player.setX(Main.WIDTH / 2);
        player.setY(Main.HEIGHT - 100);
        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemyTemp = enemys.get(i);
            enemys.remove(enemyTemp);
            enemys.add(randomEnemy());
        }
        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet tempBullet = (Bullet) player.getBullets().get(i);
            player.getBullets().remove(tempBullet);
        }
    }

    public static Enemy randomEnemy() {
        int type = (int) (Math.random() * 15);
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


