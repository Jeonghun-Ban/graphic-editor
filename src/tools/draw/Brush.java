package tools.draw;

import java.awt.Point;
import java.awt.geom.Path2D;

public class Brush extends DrawTool {

  private static final long serialVersionUID = 1L;

  private Path2D brush;

  public Brush() {
    super(new Path2D.Double());
    brush = (Path2D) shape;
  }

  @Override
  public void setStartPoint(Point startPoint) {
    brush.moveTo(startPoint.x, startPoint.y);
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    brush.lineTo(currentPoint.x, currentPoint.y);
  }

  @Override
  public DrawTool clone() {
    return new Brush();
  }

}