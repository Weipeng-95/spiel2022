package de.fhkiel.iue.oopming.screen;

import de.fhkiel.iue.oopming.Main;
import de.fhkiel.iue.oopming.basic.Position;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

abstract public class Screen {
    private PImage image;
    private Position center;
    private PFont gameFont;

    private Clip bgm;
    private AudioInputStream clip;

    protected void soundDoc(String soundSource) {
        try {
            setBgm(AudioSystem.getClip());
            File file = new File(soundSource);
            setClip(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Screen() {
    }

    public Clip getBgm() {
        return bgm;
    }

    public void setBgm(Clip bgm) {
        this.bgm = bgm;
    }

    public AudioInputStream getClip() {
        return clip;
    }

    public void setClip(AudioInputStream clip) {
        this.clip = clip;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
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
