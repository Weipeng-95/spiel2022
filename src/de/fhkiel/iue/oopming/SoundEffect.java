package de.fhkiel.iue.oopming;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {

    private Clip bgm;
    private AudioInputStream clip;

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

    // Audiodatei wird übergegeben
    public void soundDoc(String soundSource) {
        try {
            setBgm(AudioSystem.getClip());
            File file = new File(soundSource);
            setClip(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Audio wird einmal durchgeführt
    public void playNoLoop() {
        try {
            bgm.open(clip);
            bgm.start();
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Audio wird geloopt
    public void playLoop() {
        try {
            bgm.open(clip);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
