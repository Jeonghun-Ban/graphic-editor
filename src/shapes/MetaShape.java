package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class MetaShape{
    protected Shape shape;
    protected Point startP;

    public MetaShape(Shape shape){
        this.shape = shape;
    }
    public void draw(Graphics2D g2D){
        g2D.draw(shape);
    }
    abstract public void initDraw(Point startP);
    abstract public void setCoordinate(Point currentP);
    abstract public MetaShape clone();
}