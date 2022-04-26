package tools.draw;

import java.awt.Point;

public class Polygon extends DrawTool {

  private static final long serialVersionUID = 1L;

  private java.awt.Polygon polygon;

  public Polygon() {
    super(new java.awt.Polygon());
    polygon = (java.awt.Polygon) shape;
  }

  public void setStartPoint(Point startPoint) {
    polygon.addPoint(startPoint.x, startPoint.y);
  }

  public void setCurrentPoint(Point currentPoint) {
    polygon.xpoints[polygon.npoints - 1] = currentPoint.x;
    polygon.ypoints[polygon.npoints - 1] = currentPoint.y;
  }

  public void continueDrawing(Point point) {
    polygon.addPoint(point.x, point.y);
  }

  public DrawTool clone() {
    return new Polygon();
  }
}
