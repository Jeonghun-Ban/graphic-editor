package tools.transformer;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import tools.draw.DrawShape;
import views.containers.DrawingPanel;

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
    g2D.setXORMode(DEFAULT_BACKGROUND_COLOR);
    drawShape.draw(g2D);
    drawShape.setCurrentPoint(currentPoint);
    drawShape.draw(g2D);
  }

  public void finish(List<DrawShape> drawShapes) {
    super.finish();
    drawShapes.stream()
        .filter(shape -> drawShape.getBounds().contains(shape.getBounds()))
        .forEach(shape -> {
          shape.setSelected(true);
          DrawingPanel.getInstance().addSelectedShape(shape);
        });

  }
}
