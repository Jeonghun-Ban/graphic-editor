package tools.draw;

import java.awt.Point;

public class Polygon extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final java.awt.Polygon polygon;

  public Polygon() {
    super(new java.awt.Polygon());
    polygon = (java.awt.Polygon) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    polygon.addPoint(startPoint.x, startPoint.y);
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    polygon.xpoints[polygon.npoints - 1] = currentPoint.x;
    polygon.ypoints[polygon.npoints - 1] = currentPoint.y;
  }

  public void continueDrawing(Point point) {
    polygon.addPoint(point.x, point.y);
  }

  @Override
  public DrawShape clone() {
    return new Polygon();
  }
}
