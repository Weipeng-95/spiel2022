package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import de.fhkiel.iue.oopming.basic.PlaySound;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Player extends FlyingObject {
    private PImage[] images;
    private int life;
    public int score = 0;
    private int index = 0;
    private int bulletsIntervall;
    private ArrayList bullets;

    public Player() {
        setLife(3);
        setSpeed(5);
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT - 100);
        images = new PImage[]{Main.player0, Main.player1};
        setImage(Main.player0);
        bullets = new ArrayList<Bullet>();
    }

    public PImage[] getImages() {
        return images;
    }

    public void setImages(PImage[] images) {
        this.images = images;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
            tempBullet.drawCharacter(pApplet);
            tempBullet.move();
            if (tempBullet.outOfBounds()) {
                bullets.remove(tempBullet);
            }
        }
    }

    public boolean hit(FlyingObject object) {
        return (getX() - getImage().width / 2 < object.getX() + object.getImage().width / 2 &&
                getX() + getImage().width / 2 > object.getX() - object.getImage().width / 2 &&
                getY() + getImage().height / 2 > object.getY() - object.getImage().height / 2 &&
                getY() - getImage().height / 2 < object.getY() + object.getImage().height / 2);
    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        pApplet.image(getImage(), getX(), getY());
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