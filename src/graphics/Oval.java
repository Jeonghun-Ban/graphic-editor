package graphics;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Oval extends MetaShape {
    private Ellipse2D tempEllipse;
    
    public Oval(){
        super(new Ellipse2D.Double());
        tempEllipse = (Ellipse2D) shape;
    }

    public void initDraw(Point startP){
        this.startP = startP;
    }
    public void setCoordinate(Point currentP){
        tempEllipse.setFrameFromDiagonal(startP.x, startP.y, currentP.x, currentP.y);
    }
    public MetaShape clone(){
        return new Oval();
    }
}