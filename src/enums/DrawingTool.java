package enums;

import tools.draw.Line;
import tools.draw.DrawTool;
import tools.draw.Oval;
import tools.draw.Polygon;
import tools.draw.Rectangle;

public enum DrawingTool {
    Rectangle(new Rectangle()),
    Oval(new Oval()),
    Line(new Line()),
    Polygon(new Polygon()),
    Clean(null);

    private final DrawTool shape;

    DrawingTool(DrawTool shape) {
        this.shape = shape;
    }

    public DrawTool getShape() {
        return this.shape;
    }
}
