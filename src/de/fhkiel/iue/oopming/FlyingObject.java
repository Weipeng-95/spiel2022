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
    public boolean istErschossen(Gegner g, Geschoss ge) {
        return (g.getGcenter().getX() - g.getGradius() / 2 < ge.getGeCenter().getX() - ge.getGeRadius() / 2 &&
                g.getGcenter().getX() + g.getGradius() / 2  > ge.getGeCenter().getX() + ge.getGeRadius() / 2 &&
                g.getGcenter().getY() + g.getGradius() / 2 > ge.getGeCenter().getY() + ge.getGeRadius() / 2);
    }
    abstract public boolean ausserhalbSpielFeld();
}
