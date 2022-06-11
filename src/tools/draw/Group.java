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

  private List<DrawShape> drawShapes;

  public Group() {
    super(new Rectangle());
    drawShapes = new ArrayList<>();
  }

  @Override
  public void setStartPoint(Point startPoint) {
    drawShapes.forEach(drawShape -> drawShape.setStartPoint(startPoint));
  }

  @Override
  public void setCurrentPoint(Point currentPoint) {
    drawShapes.forEach(drawShape -> drawShape.setCurrentPoint(currentPoint));
  }

  public void add(DrawShape drawShape) {
    drawShapes.add(drawShape);
    if (drawShapes.size() == 1) {
      shape = drawShape.getBounds();
    } else {
      shape = shape.getBounds().createUnion(drawShape.getBounds());
    }
  }

  public List<DrawShape> getDrawShapes() {
    return drawShapes;
  }

  @Override
  public void setLineColor(Color lineColor) {
    drawShapes.forEach(drawShape -> drawShape.setLineColor(lineColor));
  }

  @Override
  public void setFillColor(Color fillColor) {
    drawShapes.forEach(drawShape -> drawShape.setFillColor(fillColor));
  }

  @Override
  public void setLineSize(int lineSize) {
    super.setLineSize(lineSize);
    drawShapes.forEach(drawShape -> drawShape.setLineSize(lineSize));
  }

  @Override
  public void setDashSize(int dashSize) {
    super.setDashSize(dashSize);
    drawShapes.forEach(drawShape -> drawShape.setDashSize(dashSize));
  }

  @Override
  public void draw(Graphics2D g2D) {
    drawShapes.forEach(drawShape -> drawShape.draw(g2D));
    if (isSelected()) {
      this.anchorList.setBound(this.getBounds());
      this.anchorList.draw(g2D);
    }
  }

  @Override
  public void translateTo(Point changePoint) {
    super.translateTo(changePoint);
    drawShapes.forEach(drawShape -> drawShape.translateTo(changePoint));
  }

  @Override
  public void scaleTo(ScalingFactorDto scalingFactorDto) {
    super.scaleTo(scalingFactorDto);
    drawShapes.forEach(drawShape -> drawShape.scaleTo(scalingFactorDto));
  }

  @Override
  public void rotateTo(Point2D startPoint, Point2D currentPoint) {
    super.rotateTo(startPoint, currentPoint);
    drawShapes.forEach(drawShape -> drawShape.rotateTo(startPoint, currentPoint));
  }
}
