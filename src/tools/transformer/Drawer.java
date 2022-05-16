package tools.transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import tools.draw.DrawShape;
import tools.draw.Polygon;
import tools.draw.Selection;

public class Drawer extends Transformer {

  public Drawer(DrawShape drawShape) {
    super(drawShape);
  }

  @Override
  public void init(Point startPoint) {
    drawShape.setStartPoint(startPoint);
  }

  @Override
  public void transform(Graphics2D g2D, Point currentPoint) {
    g2D.setXORMode(g2D.getBackground());
    drawShape.draw(g2D);
    drawShape.setCurrentPoint(currentPoint);
    drawShape.draw(g2D);
  }

  public void continueTransform(Point currentPoint) {
    ((Polygon) drawShape).continueDraw(currentPoint);
  }

  public void finish(List<DrawShape> shapes) {
    if (!(drawShape instanceof Selection)) {
      shapes.add(drawShape);
    }
  }
}