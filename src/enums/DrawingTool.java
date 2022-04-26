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

    private final DrawTool drawTool;

    DrawingTool(DrawTool drawTool) {
        this.drawTool = drawTool;
    }

    public DrawTool getDrawTool() {
        return this.drawTool;
    }
}
