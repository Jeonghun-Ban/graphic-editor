package tools.draw;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Oval extends DrawTool {

  private static final long serialVersionUID = 1L;

  private Ellipse2D ellipse;

  public Oval() {
    super(new Ellipse2D.Double());
    ellipse = (Ellipse2D) shape;
  }

  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  public void setCurrentPoint(Point currentPoint) {
    ellipse.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }

  public DrawTool clone() {
    return new Oval();
  }
}