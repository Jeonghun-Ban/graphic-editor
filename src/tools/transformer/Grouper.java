package tools.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import tools.draw.DrawShape;

public class Grouper extends Transformer {

  public Grouper(DrawShape drawShape) {
    super(drawShape);
  }

  @Override
  public void init(Point startPoint) {
    drawShape.setStartPoint(startPoint);
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    drawShape.setCurrentPoint(currentPoint);
    drawShape.draw(g2D);
  }

  public void finish(List<DrawShape> drawShapes) {
    super.finish();
    drawShapes.stream()
        .filter(shape -> drawShape.getBounds().contains(shape.getBounds()))
        .forEach(shape -> shape.setSelected(true));
  }
}
