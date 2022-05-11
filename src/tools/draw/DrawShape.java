package tools.draw;

import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;
import static global.Constants.DEFAULT_LINE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.util.Optional;
import tools.SerializableStroke;
import tools.anchor.Anchor;
import tools.anchor.AnchorList;

public abstract class DrawShape implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Shape shape;
  protected AnchorList anchorList;
  protected Point startPoint;

  protected Color lineColor;
  protected Color fillColor;

  protected AffineTransform affineTransform;
  protected SerializableStroke serializableStroke;

  protected boolean selected;

  public DrawShape(Shape shape) {
    this.shape = shape;
    this.anchorList = new AnchorList();

    this.affineTransform = new AffineTransform();
    this.serializableStroke = new SerializableStroke();

    setDefaultStyle();
    setSelected(false);
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
      anchorList.draw(g2D, boundRectangle);
    }
  }

  public void setDefaultStyle() {
    setLineColor(DEFAULT_LINE_COLOR);
    setFillColor(DEFAULT_FILL_COLOR);
    setLineSize(DEFAULT_LINE_SIZE);
    setDashSize(DEFAULT_DASH_SIZE);
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
  }

  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  public void setLineSize(int lineSize) {
    this.serializableStroke.setLineSize(lineSize);
  }

  public void setDashSize(int dashSize) {
    this.serializableStroke.setDashSize(dashSize);
  }

  public boolean onShape(Point point) {
    return this.shape.contains(point);
  }

  public Optional<Anchor> onAnchor(Point point) {
    return this.anchorList.contains(point);
  }

  public void moveTo(Point changePoint) {
    affineTransform.setToTranslation(changePoint.getX(), changePoint.getY());
    shape = affineTransform.createTransformedShape(shape);
  }

  private boolean isBrush() {
    return shape instanceof GeneralPath;
  }

  abstract public void setStartPoint(Point startPoint);

  abstract public void setCurrentPoint(Point currentPoint);

  abstract public DrawShape clone();
}
