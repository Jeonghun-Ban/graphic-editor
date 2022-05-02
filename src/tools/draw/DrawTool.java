package tools.draw;

import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.Serializable;
import tools.SerializableStroke;

public abstract class DrawTool implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Shape shape;
  protected Point startPoint;

  protected Color lineColor;
  protected Color fillColor;

  protected SerializableStroke serializableStroke;

  public DrawTool(Shape shape) {
    this.shape = shape;

    this.serializableStroke = new SerializableStroke();
    this.lineColor = DEFAULT_LINE_COLOR;
    this.fillColor = DEFAULT_FILL_COLOR;
  }

  public void draw(Graphics2D g2D) {
    g2D.setStroke(serializableStroke.getStroke());
    if (!isBrush()) {
      g2D.setColor(this.fillColor);
      g2D.fill(shape);
    }
    g2D.setColor(this.lineColor);
    g2D.draw(shape);
  }

  public void setLineColor(Color color) {
    this.lineColor = color;
  }

  public void setFillColor(Color color) {
    this.fillColor = color;
  }

  public void setDashSize(int dashSize) {
    serializableStroke.setDashSize(dashSize);
  }

  public void setLineSize(int lineSize) {
    serializableStroke.setLineSize(lineSize);
  }

  public boolean contains(Point point) {
    return this.shape.contains(point);
  }

  private boolean isBrush() {
    return shape instanceof Path2D.Double;
  }

  abstract public void setStartPoint(Point startPoint);
  abstract public void setCurrentPoint(Point currentPoint);
  abstract public DrawTool clone();
}