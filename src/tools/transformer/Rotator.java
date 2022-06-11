package tools.transformer;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

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
    g2D.setXORMode(DEFAULT_BACKGROUND_COLOR);
    drawShape.rotateTo(startPoint, currentPoint);
    this.startPoint = currentPoint;
  }
}
