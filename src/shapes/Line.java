package shapes;

import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends MetaShape {
    public Line(){
        super(new Line2D.Double());
    }
    public void initDraw(Point startP){
        this.startP = startP;
    }
    public void setCoordinate(Point currentP){
        Line2D tempLine = (Line2D) shape;
        tempLine.setLine(startP.x, startP.y, currentP.x, currentP.y);
    }
    public MetaShape clone(){
        return new Line();
    }
}