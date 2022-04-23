package de.fhkiel.iue.oopming;

import processing.core.PApplet;

public class Player extends Moveable {

    private int direction = 1;   // 1 => nach rechts

    public void draw(PApplet pApplet) {
        pApplet.fill(getColor().getR(), getColor().getG(), getColor().getB());
        pApplet.circle(getPosition().getX(), getPosition().getY(), 50);
    }

    public void move(PApplet pApplet) {
        getPosition().setX(getPosition().getX() + (direction * 5));
        if (getPosition().getX() > (int) (pApplet.displayHeight * 0.5) - 25 || getPosition().getX() < 25)
            direction = -direction;
    }

    public void steuen(PApplet pApplet) {
        if (pApplet.keyPressed) {
            if (pApplet.keyCode == pApplet.RIGHT) {
                getPosition().setX(getPosition().getX() + 4);
            } else if (pApplet.keyCode == pApplet.LEFT) {
                getPosition().setX(getPosition().getX() - 4);
            } else if (pApplet.keyCode == pApplet.UP) {
                getPosition().setY(getPosition().getY() - 4);
            } else if (pApplet.keyCode == pApplet.DOWN) {
                getPosition().setY(getPosition().getY() + 4);
            }
        }
    }
//    int move = 0;
//    public void schießen(PApplet pApplet) {
//
//        if (pApplet.keyPressed) {
//            if (pApplet.key == 'z') {
//                int Schalter = (pApplet.frameCount / 10) % 3;
//                if (Schalter == 1) {
//                    pApplet.fill(255);
//                    pApplet.ellipse(pApplet.width /2, 400 - move, 5, 5);
//                }
//                }
//            }
//
//        }
//
//    }
int speed = 0;
    public void geschoss(PApplet pApplet) {
//        if (pApplet.keyPressed) {
//            if (pApplet.key == 'z') {
//
//            }
//        }
        pApplet.fill(255);
        pApplet.ellipse(this.getPosition().getX(), this.getPosition().getY() + speed, 5, 5);
        speed-=3;
    }
}

