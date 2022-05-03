package tools.draw;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle extends DrawTool {

  private static final long serialVersionUID = 1L;

  private Rectangle2D rectangle;

  public Rectangle() {
    super(new Rectangle2D.Double());
    rectangle = (Rectangle2D) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    rectangle.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
  }

  @Override
  public DrawTool clone() {
    return new Rectangle();
  }
}
