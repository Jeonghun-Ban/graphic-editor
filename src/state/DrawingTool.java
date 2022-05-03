package state;

import tools.draw.Brush;
import tools.draw.DrawTool;
import tools.draw.Line;
import tools.draw.Oval;
import tools.draw.Polygon;
import tools.draw.Rectangle;

public enum DrawingTool {
  Cursor(null),
  Brush(new Brush()),
  Rectangle(new Rectangle()),
  Oval(new Oval()),
  Line(new Line()),
  Polygon(new Polygon()),
  Clean(null);

  private final DrawTool drawTool;

  DrawingTool(DrawTool drawTool) {
    this.drawTool = drawTool;
  }

  public DrawTool getDrawTool() {
    return this.drawTool;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
