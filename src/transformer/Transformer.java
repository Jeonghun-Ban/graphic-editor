package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import tools.draw.DrawShape;

public abstract class Transformer {

  protected DrawShape drawShape;

  public Transformer(DrawShape drawShape) {
    this.drawShape = drawShape;
  }

  public abstract void init(Point startPoint);

  public abstract void transform(Graphics2D graphics2D, Point currentPoint);

  public abstract void finish(List<DrawShape> drawShapes);
}