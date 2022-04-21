package tools.draw;

import java.awt.Point;

public class Polygon extends Draw {
    private java.awt.Polygon polygon;

    public Polygon() {
        super(new java.awt.Polygon());
        polygon = (java.awt.Polygon) shape;
    }

    public void initDraw(Point p) {
        polygon.addPoint(p.x, p.y);
    }

    public void setCoordinate(Point p) {
        polygon.xpoints[polygon.npoints - 1] = p.x;
        polygon.ypoints[polygon.npoints - 1] = p.y;
    }

    public void continueDrawing(Point p) {
        polygon.addPoint(p.x, p.y);
    }

    public Draw clone() {
        return new Polygon();
    }
}