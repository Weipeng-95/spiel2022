package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Player extends FlyingObject {
    private int index = 0;
    private int life;
    private int score = 0;
    private int bulletsIntervall;
    private static boolean isRight, isLeft, isUp, isDown, isShooted;
    private final PImage[] images;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private SoundEffect sound = new SoundEffect();

    public Player() {
        setLife(3);
        setSpeed(5);
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT - 100);
        images = new PImage[]{Main.player0, Main.player1};
        setImage(Main.player0);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void shoot() {

        if (bulletsIntervall % 4 == 0) {
            Bullet bullet = new Bullet(getX(), getY() - getImage().height / 2);
            bullets.add(bullet);
            sound.soundDoc("src/de/fhkiel/iue/oopming/resources/sounds/shootBgm.wav");
            sound.playNoLoop();
        }

    }

    public void bulletsGenerator(PApplet pApplet) {
        bulletsIntervall++;
        for (int i = 0; i < bullets.size(); i++) {
            Bullet tempBullet = bullets.get(i);
            pApplet.image(tempBullet.getImage(), tempBullet.getX(), tempBullet.getY());
            tempBullet.move();
            if (tempBullet.outOfBounds()) {
                bullets.remove(tempBullet);
            }
        }
    }

    public void animation() {
        if (images.length > 0) {
            setImage(images[index++ / 5 % images.length]);
        }
    }

    public boolean isHitBy(Enemy enemy) {
        return (getX() - getImage().width / 2 < enemy.getX() + enemy.getImage().width / 2 &&
                getX() + getImage().width / 2 > enemy.getX() - enemy.getImage().width / 2 &&
                getY() + getImage().height / 2 > enemy.getY() - enemy.getImage().height / 2 &&
                getY() - getImage().height / 2 < enemy.getY() + enemy.getImage().height / 2);
    }

    public static void inputControl(PApplet pApplet, boolean flag) {
        if (pApplet.keyCode == 39) isRight = flag;
        if (pApplet.keyCode == 37) isLeft = flag;
        if (pApplet.keyCode == 38) isUp = flag;
        if (pApplet.keyCode == 40) isDown = flag;
        if (pApplet.key == 'z' || pApplet.key == 'Z') isShooted = flag;
    }


    @Override
    public void move() {
        if (isRight) {
            setX(getX() + getSpeed());
        }
        if (isLeft) {
            setX(getX() - getSpeed());
        }
        if (isUp) {
            setY(getY() - getSpeed());
        }
        if (isDown) {
            setY(getY() + getSpeed());
        }
        if (isShooted) {
            shoot();
        }
    }

    @Override
    public boolean outOfBounds() {
        return getX() >= getImage().width / 2 &&
                getX() <= Main.WIDTH - getImage().width / 2 &&
                getY() >= getImage().height / 2 &&
                getY() <= Main.HEIGHT - getImage().height / 2;
    }
}