package tools.transformer;

import enums.DrawMode;
import java.awt.Graphics2D;
import java.awt.Point;
import tools.draw.DrawShape;
import views.containers.DrawingPanel;

public abstract class Transformer {

  protected final DrawShape drawShape;
  DrawingPanel drawingPanel = DrawingPanel.getInstance();

  public Transformer(DrawShape drawShape) {
    this.drawShape = drawShape;
  }

  public abstract void init(Point startPoint);

  public abstract void transform(Graphics2D g2D, Point currentPoint);

  public void finish() {
    drawingPanel.setUpdated(true);
    drawingPanel.setDrawMode(DrawMode.IDLE);
    drawingPanel.repaint();
  }
}
