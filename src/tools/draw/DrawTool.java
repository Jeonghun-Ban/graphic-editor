package tools.draw;

import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;
import static global.Constants.DEFAULT_LINE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;
import tools.CustomStroke;

public abstract class DrawTool implements Serializable {
    protected Shape shape;
    protected Point startP;

    protected Color lineColor;
    protected Color fillColor;

    protected CustomStroke stroke;
    protected int lineSize;
    protected int dashSize;

    public DrawTool(Shape shape){
        this.shape = shape;

        this.stroke = new CustomStroke();
        this.lineColor = DEFAULT_LINE_COLOR;
        this.fillColor = DEFAULT_FILL_COLOR;
        this.lineSize = DEFAULT_LINE_SIZE;
        this.dashSize = DEFAULT_DASH_SIZE;
    }

    public void draw(Graphics2D g2D){
        g2D.setStroke(stroke);
        g2D.setColor(this.fillColor);
        g2D.fill(shape);
        g2D.setColor(this.lineColor);
        g2D.draw(shape);
    }

    public void setLineColor(Color color){
        this.lineColor = color;
    }

    public void setFillColor(Color color){
        this.fillColor = color;
    }

    public void setDashSize(int dashSize){
        this.dashSize = dashSize;
        changeStroke();
    }

    public void setLineSize(int lineSize){
        this.lineSize = lineSize;
        changeStroke();
    }

    public void changeStroke(){
        stroke = (dashSize == 0) ?
            new CustomStroke(lineSize) :
            new CustomStroke(lineSize, dashSize);
    }

    public boolean contains(Point point) {
        return this.shape.contains(point);
    };

    abstract public void initDraw(Point startP);
    abstract public void setCoordinate(Point currentP);
    abstract public DrawTool clone();
}