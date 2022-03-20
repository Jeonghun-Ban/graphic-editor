package shapes;

import constants.Constants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class MetaShape{
    protected Shape shape;
    protected Point startP;
    protected Color lineColor = Constants.DEFAULT_LINE_COLOR;
    protected Color fillColor = Constants.DEFAULT_FILL_COLOR;

    public MetaShape(Shape shape){
        this.shape = shape;
    }
    public void draw(Graphics2D g2D){
        g2D.setColor(this.fillColor);
        g2D.fill(shape);
        g2D.setColor(this.lineColor);
        g2D.draw(shape);
    }
    public void setLineColor(Color color){
        this.lineColor = color;
    };
    public void setFillColor(Color color){
        this.fillColor = color;
    };
    abstract public void initDraw(Point startP);
    abstract public void setCoordinate(Point currentP);
    abstract public MetaShape clone();

}