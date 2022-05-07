package tools.draw;

import java.awt.Point;
import java.awt.geom.GeneralPath;

public class Brush extends DrawShape {

  private static final long serialVersionUID = 1L;

  private final GeneralPath brush;

  public Brush() {
    super(new GeneralPath());
    brush = (GeneralPath) shape;
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
  public DrawShape clone() {
    return new Brush();
  }

}
