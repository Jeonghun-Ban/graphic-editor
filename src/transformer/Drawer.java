package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import tools.draw.DrawShape;
import tools.draw.Polygon;

public class Drawer extends Transformer {

  public Drawer(DrawShape drawShape) {
    super(drawShape);
  }

  @Override
  public void init(Point startPoint) {
    drawShape.setStartPoint(startPoint);
  }

  @Override
  public void transform(Graphics2D graphics2D, Point currentPoint) {
    graphics2D.setXORMode(graphics2D.getBackground());
    drawShape.draw(graphics2D);
    drawShape.setCurrentPoint(currentPoint);
    drawShape.draw(graphics2D);
  }

  public void continueTransform(Point currentPoint) {
    ((Polygon) drawShape).continueDrawing(currentPoint);
  }

  @Override
  public void finish(List<DrawShape> shapes) {
    shapes.add(drawShape);
  }
}