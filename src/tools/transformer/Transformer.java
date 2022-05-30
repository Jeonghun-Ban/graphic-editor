package tools.transformer;

import global.DrawMode;
import java.awt.Graphics2D;
import java.awt.Point;
import tools.draw.DrawShape;
import views.containers.DrawingPanel;

public abstract class Transformer {

  protected final DrawShape drawShape;
  protected final DrawingPanel drawingPanel;

  public Transformer(DrawShape drawShape) {
    this.drawShape = drawShape;
    drawingPanel = DrawingPanel.getInstance();
  }

  public abstract void init(Point startPoint);

  public abstract void transform(Graphics2D g2D, Point currentPoint);

  public void finish() {
    drawingPanel.setTransformer(null);
    drawingPanel.setUpdated(true);
    drawingPanel.setDrawMode(DrawMode.IDLE);
    drawingPanel.repaint();
  }
}
