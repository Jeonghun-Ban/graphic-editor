package tools.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import tools.draw.DrawShape;

public class Rotator extends Transformer{

  private Point startPoint;

  public Rotator(DrawShape drawShape) {
    super(drawShape);
    this.startPoint = new Point();
  }

  @Override
  public void init(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    drawShape.rotateTo(this.drawShape, startPoint, currentPoint);
    this.startPoint = currentPoint;
  }
}
