package graphics;

import java.awt.Point;

public class Polygon extends MetaShape {
    private java.awt.Polygon tempPolygon;

    public Polygon() {
        super(new java.awt.Polygon());
        tempPolygon = (java.awt.Polygon) shape;
    }

    public void initDraw(Point p) {
        tempPolygon.addPoint(p.x, p.y);
    }

    public void setCoordinate(Point p) {
        tempPolygon.xpoints[tempPolygon.npoints-1] = p.x;
        tempPolygon.ypoints[tempPolygon.npoints-1] = p.y;
    }

    public void continueDrawing(Point p) {
        tempPolygon.addPoint(p.x, p.y);
    }

    public MetaShape clone() {
        return new Polygon();
    }
}
