package graphics;

import java.awt.Point;

public class Rectangle extends MetaShape {
    private java.awt.Rectangle tempRectangle;
    
    public Rectangle(){
        super(new java.awt.Rectangle());
        tempRectangle = (java.awt.Rectangle) shape;
    }
    public void initDraw(Point startP){
        this.startP = startP;
    }
    public void setCoordinate(Point currentP){
        tempRectangle.setFrameFromDiagonal(startP.x, startP.y, currentP.x, currentP.y);
    }
    public MetaShape clone(){
        return new Rectangle();
    }
}