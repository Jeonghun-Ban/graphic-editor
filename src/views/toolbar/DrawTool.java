package views.toolbar;

import static global.Constants.ICON_RESOURCE_FORMAT;
import static global.Constants.ICON_RESOURCE_GENERAL_PATH;
import static global.Constants.ICON_RESOURCE_PRESSED_PATH;
import static global.Constants.ICON_RESOURCE_ROOT_PATH;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

  public ImageIcon getIcon(boolean isPressed) {
    return new ImageIcon(ICON_RESOURCE_ROOT_PATH.concat(
            isPressed ? ICON_RESOURCE_PRESSED_PATH : ICON_RESOURCE_GENERAL_PATH)
        .concat(toString().toLowerCase())
        .concat(ICON_RESOURCE_FORMAT));
  }

  public Icon getIcon() {
    return this.getIcon();
  }
}
