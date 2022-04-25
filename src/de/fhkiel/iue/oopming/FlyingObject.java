package de.fhkiel.iue.oopming;

abstract public class FlyingObject {
    //    protected PImage image;
//    protected float imageWidth;
//    protected float imageHeight;
    protected Position center;
    protected Color color;

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    //    public boolean istErschossen(Geschoss geschoss) {
//        float x1 = this.center.getX();
//        float x2 = this.center.getX() + this.imageWidth;
//        float y1 = this.center.getY();
//        float y2 = this.center.getY() + this.imageHeight;
//        float x = geschoss.x;
//        float y = geschoss.y;
//        return x > x1 && x < x2 && y > y1 && y < y2;
//    }

    abstract public boolean ausserhalbSpielFeld();
}
