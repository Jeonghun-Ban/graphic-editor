package tools.draw;

import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends DrawTool {

  private static final long serialVersionUID = 1L;

  private Line2D line;

  public Line() {
    super(new Line2D.Double());
    line = (Line2D) shape;
  }

  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  public void setCurrentPoint(Point currentPoint) {
    line.setLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }

  public DrawTool clone() {
    return new Line();
  }
}