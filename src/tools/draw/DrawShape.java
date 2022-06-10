package tools.draw;

import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;
import static global.Constants.DEFAULT_LINE_SIZE;
import static global.Constants.SHAPE_INTERSECT_HEIGHT;
import static global.Constants.SHAPE_INTERSECT_WIDTH;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Optional;
import tools.SerializableStroke;
import tools.anchor.Anchor;
import tools.anchor.AnchorList;
import utils.CustomAffineTransform;
import utils.dto.ScalingFactorDto;

public abstract class DrawShape implements Serializable {

  private static final long serialVersionUID = 1L;

  protected final AffineTransform affineTransform;
  protected final SerializableStroke serializableStroke;
  protected final AnchorList anchorList;

  protected Shape shape;
  protected Point startPoint;

  protected Color lineColor = DEFAULT_LINE_COLOR;
  protected Color fillColor = DEFAULT_FILL_COLOR;

  protected boolean selected;

  public DrawShape(Shape shape) {
    this.shape = shape;
    this.affineTransform = new CustomAffineTransform();
    this.serializableStroke = new SerializableStroke();
    this.anchorList = new AnchorList();

    setLineSize(DEFAULT_LINE_SIZE);
    setDashSize(DEFAULT_DASH_SIZE);
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

  public Rectangle getBounds() {
    return shape.getBounds();
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public Color getLineColor() {
    return this.lineColor;
  }

  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
  }

  public Color getFillColor() {
    return this.fillColor;
  }

  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  public int getLineSize() {
    return this.serializableStroke.getLineSize();
  }

  public void setLineSize(int lineSize) {
    this.serializableStroke.setLineSize(lineSize);
  }

  public int getDashSize() {
    return this.serializableStroke.getDashSize();
  }

  public void setDashSize(int dashSize) {
    this.serializableStroke.setDashSize(dashSize);
  }

  public boolean onShape(Point point) {
    return shape.intersects(point.x, point.y, SHAPE_INTERSECT_WIDTH, SHAPE_INTERSECT_HEIGHT)
        || onAnchor(point).isPresent();
  }

  public Optional<Anchor> onAnchor(Point point) {
    return Optional.ofNullable(this.anchorList.contains(point));
  }

  public void translateTo(Point changePoint) {
    affineTransform.setToTranslation(changePoint.getX(), changePoint.getY());
    shape = affineTransform.createTransformedShape(shape);
  }

  public void scaleTo(ScalingFactorDto scalingFactorDto) {
    affineTransform.setToTranslation(scalingFactorDto.getInitX(), scalingFactorDto.getInitY());
    affineTransform.scale(scalingFactorDto.getScaleX(), scalingFactorDto.getScaleY());
    affineTransform.translate(scalingFactorDto.getFinishX(), scalingFactorDto.getFinishY());
    shape = affineTransform.createTransformedShape(shape);
  }

  public void rotateTo(Point2D startPoint, Point2D currentPoint) {
    Point2D centerPoint = new Point2D.Double(shape.getBounds2D().getCenterX(),
        shape.getBounds2D().getCenterY());
    double rotateAngle = getRotateAngle(centerPoint, startPoint, currentPoint);
    affineTransform.setToRotation(Math.toRadians(rotateAngle), centerPoint.getX(),
        centerPoint.getY());
    shape = affineTransform.createTransformedShape(shape);
  }

  private double getRotateAngle(Point2D centerPoint, Point2D startPoint, Point2D currentPoint) {
    double startAngle = Math.toDegrees(
        Math.atan2(centerPoint.getX() - startPoint.getX(), centerPoint.getY() - startPoint.getY()));
    double endAngle = Math.toDegrees(Math.atan2(centerPoint.getX() - currentPoint.getX(),
        centerPoint.getY() - currentPoint.getY()));

    double rotationAngle = startAngle - endAngle;
    if (rotationAngle < 0) {
      rotationAngle += 360;
    }
    return rotationAngle;
  }

  private boolean isBrush() {
    return shape instanceof Path2D.Float;
  }

  public abstract void setStartPoint(Point startPoint);

  public abstract void setCurrentPoint(Point currentPoint);

  public abstract DrawShape clone();
}
