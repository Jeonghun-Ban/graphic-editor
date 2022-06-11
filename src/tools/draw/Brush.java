package tools.draw;

import java.awt.Point;
import java.awt.geom.Path2D;

public class Brush extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final Path2D.Float brush;

  public Brush() {
    super(new Path2D.Float());
    brush = (Path2D.Float) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    brush.moveTo(startPoint.x, startPoint.y);
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    brush.lineTo(currentPoint.x, currentPoint.y);
  }
}
