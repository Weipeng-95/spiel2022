package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

abstract public class Screen {

    private float x;
    private float y;
    private PImage image;
    private PFont gameFont;


    public Screen() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }


    public PFont getGameFont() {
        return gameFont;
    }

    public void setGameFont(PFont gameFont) {
        this.gameFont = gameFont;
    }

    protected boolean isOnText(PApplet pApplet, String text, int textHeightPos, int textSize) {
        if (pApplet.mouseX > Main.WIDTH / 2 - pApplet.textWidth(text) / 2 &&
                pApplet.mouseX < Main.WIDTH / 2 + pApplet.textWidth(text) / 2 &&
                pApplet.mouseY > textHeightPos - textSize + 10 &&
                pApplet.mouseY < textHeightPos + 10) return true;
        else return false;
    }

    abstract public void setup(PApplet pApplet);

    abstract public void showScreen(PApplet pApplet);
}
