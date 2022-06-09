package de.fhkiel.iue.oopming.character;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.FlyingObject;
import processing.core.PApplet;
import processing.core.PImage;

public class Player extends FlyingObject {
    private PImage[] images;
    private int life;
    private int score = 0;
    private int index = 0;

    public Player() {
        setSpeed(5);
        setX(Main.WIDTH / 2);
        setY(Main.HEIGHT - 100);
        setImage(Main.player0);
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

    public void subtractLife() {
        life--;
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

//    public Geschoss[] shoot(){
//
//
//    }

    public boolean hit(FlyingObject other) {

        float x1 = other.getX() - this.getImage().width / 2;                 //x坐标最小距离
        float x2 = other.getX() + this.getImage().width / 2 + other.getImage().width;   //x坐标最大距离
        float y1 = other.getY() - this.getImage().height / 2;                //y坐标最小距离
        float y2 = other.getY() + this.getImage().height / 2 + other.getImage().height; //y坐标最大距离

        float herox = this.getX() + this.getImage().width / 2;               //英雄机x坐标中心点距离
        float heroy = this.getX() + this.getImage().height / 2;              //英雄机y坐标中心点距离

        return herox > x1 && herox < x2 && heroy > y1 && heroy < y2;   //区间范围内为撞上了
    }

    @Override
    public void drawCharacter(PApplet pApplet) {
        pApplet.image(getImage(), getX(), getY());
    }

    @Override
    public void move() {

    }

    @Override
    public boolean outOfBounds() {
        return getX() >= getImage().width / 2 &&
                getX() <= Main.WIDTH - getImage().width / 2 &&
                getY() >= getImage().height / 2 &&
                getY() <= Main.HEIGHT - getImage().height / 2;
    }
}
