package tools.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import tools.draw.DrawShape;

public abstract class Transformer {

  protected final DrawShape drawShape;

  public Transformer(DrawShape drawShape) {
    this.drawShape = drawShape;
  }

  public abstract void init(Point startPoint);

  public abstract void transform(Graphics2D g2D, Point currentPoint);
}