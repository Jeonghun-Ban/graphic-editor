package tools.transformer;

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
  public void transform(Graphics2D g2D, Point currentPoint) {
    g2D.setXORMode(g2D.getBackground());
    drawShape.draw(g2D);
    drawShape.setCurrentPoint(currentPoint);
    drawShape.draw(g2D);
  }

  public void continueTransform(Point currentPoint) {
    ((Polygon) drawShape).continueDraw(currentPoint);
  }

  @Override
  public void finish() {
    super.finish();
    List<DrawShape> shapes = drawingPanel.getDrawShapes();
    shapes.add(drawShape);
    drawingPanel.selectShape(drawShape);
  }
}