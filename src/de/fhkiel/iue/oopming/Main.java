package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Main extends PApplet {

    public Player player = new Player();

    public static void main(String[] args) {
        PApplet.main("de.fhkiel.iue.oopming.Main");


    }

    public void setup() {
        noStroke();

    }

    @Override
    public void settings() {
        size((int) (displayHeight * 0.5), displayHeight - 150);
        player.setPosition(new Position(width / 2, height - 100));
        player.setColor(new Color(100, 100, 200));

    }

    @Override
    public void draw() {
        background(0);
        player.draw(this);
//        player.move(this);
        player.steuen(this);
        player.geschoss(this);
//        player.move+=4;
    }
}
