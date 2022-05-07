package enums;

import tools.draw.Brush;
import tools.draw.DrawShape;
import tools.draw.Line;
import tools.draw.Oval;
import tools.draw.Polygon;
import tools.draw.Rectangle;

public enum DrawTool {
  Cursor(null),
  Brush(new Brush()),
  Rectangle(new Rectangle()),
  Oval(new Oval()),
  Line(new Line()),
  Polygon(new Polygon()),
  Remove(null);

  private final DrawShape drawShape;

  DrawTool(DrawShape drawShape) {
    this.drawShape = drawShape;
  }

  public DrawShape getDrawShape() {
    return this.drawShape;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
