package transformer;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import java.awt.Graphics2D;
import java.awt.Point;
import tools.draw.DrawShape;

public class Mover extends Transformer {

  private Point startPoint;

  public Mover(DrawShape drawShape) {
    super(drawShape);
    this.startPoint = new Point();
  }

  @Override
  public void init(Point startPoint) {
    this.startPoint = startPoint;
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    Point changePoint = new Point(currentPoint.x - this.startPoint.x,
        currentPoint.y - this.startPoint.y);
    g2D.setXORMode(DEFAULT_BACKGROUND_COLOR);
    drawShape.draw(g2D);
    drawShape.moveTo(changePoint);
    drawShape.draw(g2D);
    this.startPoint = currentPoint;
  }
}
