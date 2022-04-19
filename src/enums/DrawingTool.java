package enums;

import shapes.Line;
import shapes.MetaShape;
import shapes.Oval;
import shapes.Polygon;
import shapes.Rectangle;

public enum DrawingTool {
    Rectangle(new Rectangle()),
    Oval(new Oval()),
    Line(new Line()),
    Polygon(new Polygon()),
    Clean(null);

    private final MetaShape shape;

    DrawingTool(MetaShape shape) {
        this.shape = shape;
    }

    public MetaShape getShape() {
        return this.shape;
    }
}
