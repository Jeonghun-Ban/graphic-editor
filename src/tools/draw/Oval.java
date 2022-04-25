package tools.draw;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Oval extends DrawTool {
    private Ellipse2D ellipse;

    public Oval() {
        super(new Ellipse2D.Double());
        ellipse = (Ellipse2D) shape;
    }

    public void initDraw(Point startP) {
        this.startP = startP;
    }

    public void setCoordinate(Point currentP) {
        ellipse.setFrameFromDiagonal(startP.x, startP.y, currentP.x, currentP.y);
    }

    public DrawTool clone() {
        return new Oval();
    }
}