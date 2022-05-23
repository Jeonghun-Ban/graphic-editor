package tools.transformer;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import java.awt.Graphics2D;
import java.awt.Point;
import tools.draw.DrawShape;

public class Translator extends Transformer {

  private Point startPoint;

  public Translator(DrawShape drawShape) {
    super(drawShape);
    this.startPoint = new Point();
  }

  @Override
  public void init(Point startPoint) {
    this.startPoint = startPoint;
    drawShape.setSelected(false);
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    Point changePoint = new Point(currentPoint.x - this.startPoint.x,
        currentPoint.y - this.startPoint.y);
    g2D.setXORMode(DEFAULT_BACKGROUND_COLOR);
    drawShape.translateTo(changePoint);
    this.startPoint = currentPoint;
  }

  @Override
  public void finish() {
    super.finish();
    drawShape.setSelected(true);
  }
}
