package tools.draw;

import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Line2D line;

  public Line() {
    super(new Line2D.Double());
    line = (Line2D) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    line.setLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }

  @Override
  public DrawShape clone() {
    return new Line();
  }
}
