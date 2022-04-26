package tools.draw;

import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends DrawTool {

  private static final long serialVersionUID = 1L;

  private Line2D line;

  public Line() {
    super(new Line2D.Double());
    line = (Line2D) shape;
  }

  public void initDraw(Point startP) {
    this.startP = startP;
  }

  public void setCoordinate(Point currentP) {
    line.setLine(startP.x, startP.y, currentP.x, currentP.y);
  }

  public DrawTool clone() {
    return new Line();
  }
}