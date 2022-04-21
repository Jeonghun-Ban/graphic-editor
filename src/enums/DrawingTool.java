package enums;

import tools.draw.Line;
import tools.draw.Draw;
import tools.draw.Oval;
import tools.draw.Polygon;
import tools.draw.Rectangle;

public enum DrawingTool {
    Rectangle(new Rectangle()),
    Oval(new Oval()),
    Line(new Line()),
    Polygon(new Polygon()),
    Clean(null);

    private final Draw shape;

    DrawingTool(Draw shape) {
        this.shape = shape;
    }

    public Draw getShape() {
        return this.shape;
    }
}
