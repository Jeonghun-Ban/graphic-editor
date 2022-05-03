package tools.draw;

import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.Serializable;
import tools.Anchor;
import tools.SerializableStroke;

public abstract class DrawShape implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Shape shape;
  protected Anchor anchor;
  protected Point startPoint;

  protected Color lineColor;
  protected Color fillColor;

  protected SerializableStroke serializableStroke;

  protected boolean selected;

  public DrawShape(Shape shape) {
    this.shape = shape;
    this.anchor = new Anchor();

    this.serializableStroke = new SerializableStroke();
    this.lineColor = DEFAULT_LINE_COLOR;
    this.fillColor = DEFAULT_FILL_COLOR;

    this.selected = false;
  }

  public void draw(Graphics2D g2D) {
    g2D.setStroke(serializableStroke.getStroke());
    if (!isBrush()) {
      g2D.setColor(this.fillColor);
      g2D.fill(shape);
    }
    g2D.setColor(this.lineColor);
    g2D.draw(shape);

    if (selected) {
      Rectangle boundRectangle = shape.getBounds();
      anchor.draw(g2D, boundRectangle);
    }
  }

  public void setSelected() {
    selected = !selected;
  }

  public void setStyleAttributes(Color lineColor, Color fillColor, int lineSize, int dashSize) {
    this.lineColor = lineColor;
    this.fillColor = fillColor;
    this.serializableStroke.setDashSize(dashSize);
    this.serializableStroke.setLineSize(lineSize);
  }

  public boolean contains(Point point) {
    return this.shape.contains(point);
  }

  private boolean isBrush() {
    return shape instanceof Path2D.Double;
  }

  abstract public void setStartPoint(Point startPoint);

  abstract public void setCurrentPoint(Point currentPoint);

  abstract public DrawShape clone();
}
