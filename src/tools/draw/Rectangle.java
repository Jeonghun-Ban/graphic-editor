package tools.draw;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Draw {
    private Rectangle2D rectangle;

    public Rectangle() {
        super(new Rectangle2D.Double());
        rectangle = (Rectangle2D) shape;
    }

    public void initDraw(Point startP) {
        this.startP = startP;
    }

    public void setCoordinate(Point currentP) {
        rectangle.setFrameFromDiagonal(startP.x, startP.y, currentP.x, currentP.y);
    }

    public Draw clone() {
        return new Rectangle();
    }
}