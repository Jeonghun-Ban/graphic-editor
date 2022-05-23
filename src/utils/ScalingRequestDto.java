package utils;

import java.awt.Point;
import java.awt.Rectangle;

public class ScalingRequestDto {

  final Point startPoint;
  final Point currentPoint;
  final Rectangle bounds;

  public ScalingRequestDto(Point startPoint, Point currentPoint, Rectangle bounds) {
    this.startPoint = startPoint;
    this.currentPoint = currentPoint;
    this.bounds = bounds;
  }

  public Point getStartPoint() {
    return startPoint;
  }

  public Point getCurrentPoint() {
    return currentPoint;
  }

  public Rectangle getBounds() {
    return bounds;
  }
}
