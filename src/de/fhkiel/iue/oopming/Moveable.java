package de.fhkiel.iue.oopming;

import processing.core.PApplet;

abstract public class Moveable extends Thing {
//    private boolean inMovement = false;
    abstract public void move(PApplet pApplet);
}
