package enums;

import static global.Constants.ANCHOR_HEIGHT;
import static global.Constants.ANCHOR_RADIUS;
import static global.Constants.ANCHOR_WIDTH;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.function.Function;

public enum AnchorEnum {
  NW((Rectangle r) -> new Point(r.x, r.y)),
  NN((Rectangle r) -> new Point(r.x + r.width / 2, r.y)),
  NE((Rectangle r) -> new Point(r.x + r.width, r.y)),
  EE((Rectangle r) -> new Point(r.x + r.width, r.y + r.height / 2)),
  SE((Rectangle r) -> new Point(r.x + r.width, r.y + r.height)),
  SS((Rectangle r) -> new Point(r.x + r.width / 2, r.y + r.height)),
  SW((Rectangle r) -> new Point(r.x, r.y + r.height)),
  WW((Rectangle r) -> new Point(r.x, r.y + r.height / 2)),
  RR((Rectangle r) -> new Point(r.x + r.width / 2, r.y - 50)),
  ;

  private final Function<Rectangle, Point> operator;

  AnchorEnum(Function<Rectangle, Point> operator) {
    this.operator = operator;
  }

  public Ellipse2D getBoundAnchor(Ellipse2D anchor, Rectangle rectangle) {
    Point point = getBoundPoint(rectangle);
    anchor.setFrame(point.x, point.y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
    return anchor;
  }

  private Point getBoundPoint(Rectangle rectangle) {
    Point point = this.operator.apply(rectangle);
    point.x = point.x - ANCHOR_RADIUS;
    point.y = point.y - ANCHOR_RADIUS;
    return point;
  }
}
