package enums;

import tools.draw.Brush;
import tools.draw.DrawShape;
import tools.draw.Line;
import tools.draw.Oval;
import tools.draw.Polygon;
import tools.draw.Rectangle;
import tools.draw.Selection;

public enum DrawTool {
  Selection(Selection.class),
  Brush(Brush.class),
  Rectangle(Rectangle.class),
  Oval(Oval.class),
  Line(Line.class),
  Polygon(Polygon.class),
  ;

  private final Class<? extends DrawShape> shapeClass;

  DrawTool(Class<? extends DrawShape> shapeClass) {
    this.shapeClass = shapeClass;
  }

  public Class<? extends DrawShape> getShapeClass() {
    return this.shapeClass;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
