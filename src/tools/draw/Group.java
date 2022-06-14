package tools.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import utils.dto.ScalingFactorDto;

public class Group extends DrawShape {

  private final List<DrawShape> childShapes;

  public Group() {
    super(new Rectangle());
    childShapes = new ArrayList<>();
  }

  @Override
  public void setStartPoint(Point startPoint) {
    childShapes.forEach(drawShape -> drawShape.setStartPoint(startPoint));
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    childShapes.forEach(drawShape -> drawShape.setCurrentPoint(currentPoint));
  }

  public void add(DrawShape drawShape) {
    childShapes.add(drawShape);
    if (childShapes.size() == 1) {
      shape = drawShape.getBounds();
    } else {
      shape = shape.getBounds().createUnion(drawShape.getBounds());
    }
  }

  public List<DrawShape> getChildShapes() {
    return childShapes;
  }

  @Override
  public void setLineColor(Color lineColor) {
    childShapes.forEach(drawShape -> drawShape.setLineColor(lineColor));
  }

  @Override
  public void setFillColor(Color fillColor) {
    childShapes.forEach(drawShape -> drawShape.setFillColor(fillColor));
  }

  @Override
  public void setLineSize(int lineSize) {
    super.setLineSize(lineSize);
    childShapes.forEach(drawShape -> drawShape.setLineSize(lineSize));
  }

  @Override
  public void setDashSize(int dashSize) {
    super.setDashSize(dashSize);
    childShapes.forEach(drawShape -> drawShape.setDashSize(dashSize));
  }

  @Override
  public void draw(Graphics2D g2D) {
    childShapes.forEach(drawShape -> drawShape.draw(g2D));
    if (isSelected()) {
      this.anchorList.setBound(this.getBounds());
      this.anchorList.draw(g2D);
    }
  }

  @Override
  public void translateTo(Point changePoint) {
    super.translateTo(changePoint);
    childShapes.forEach(drawShape -> drawShape.translateTo(changePoint));
  }

  @Override
  public void scaleTo(ScalingFactorDto scalingFactorDto) {
    super.scaleTo(scalingFactorDto);
    childShapes.forEach(drawShape -> drawShape.scaleTo(scalingFactorDto));
  }

  @Override
  public void rotateTo(DrawShape target, Point2D startPoint, Point2D currentPoint) {
    super.rotateTo(target, startPoint, currentPoint);
    childShapes.forEach(childShape -> childShape.rotateTo(target, startPoint, currentPoint));
  }
}
