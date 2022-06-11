package de.fhkiel.iue.oopming;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Player extends FlyingObject {
    private PImage[] images;
    private int index = 0;
    private int life;
    private int score = 0;
    private int bulletsIntervall;
            private ArrayList<Bullet> bullets;

    public Player() {
        setLife(3);
        setSpeed(5);
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT - 100);
        images = new PImage[]{Main.player0, Main.player1};
        setImage(Main.player0);
        bullets = new ArrayList<Bullet>();
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

    public ArrayList getBullets() {
        return bullets;
    }

    public void shoot() {
        if (bulletsIntervall % 4 == 0) {
            Bullet bullet = new Bullet(getX(), getY() - getImage().height / 2);
            bullets.add(bullet);
            new PlaySound("src/de/fhkiel/iue/oopming/Sound/shootBgm.wav").start();
        }

    }

    public void bulletsGenerator(PApplet pApplet) {
        bulletsIntervall++;
        for (int i = 0; i < bullets.size(); i++) {
            Bullet tempBullet = (Bullet) bullets.get(i);
            pApplet.image(tempBullet.getImage(), tempBullet.getX(), tempBullet.getY());
            tempBullet.move();
            if (tempBullet.outOfBounds()) {
                bullets.remove(tempBullet);
            }
        }
    }

    public boolean hitBy(Enemy enemy) {
        return (getX() - getImage().width / 2 < enemy.getX() + enemy.getImage().width / 2 &&
                getX() + getImage().width / 2 > enemy.getX() - enemy.getImage().width / 2 &&
                getY() + getImage().height / 2 > enemy.getY() - enemy.getImage().height / 2 &&
                getY() - getImage().height / 2 < enemy.getY() + enemy.getImage().height / 2);
    }


    @Override
    public void move() {
        if (images.length > 0) {
            setImage(images[index++ / 5 % images.length]);
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