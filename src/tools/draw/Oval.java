package tools.draw;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Oval extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Ellipse2D ellipse;

  public Oval() {
    super(new Ellipse2D.Double());
    ellipse = (Ellipse2D) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    ellipse.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }

  @Override
  public DrawShape clone() {
    return new Oval();
  }
}
