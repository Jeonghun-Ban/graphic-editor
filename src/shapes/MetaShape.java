package shapes;

import global.Constants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;
import utils.CustomStroke;

public abstract class MetaShape implements Serializable {
    protected Shape shape;
    protected Point startP;

    protected Color lineColor;
    protected Color fillColor;

    protected CustomStroke stroke;
    protected int lineSize;
    protected int dashSize;

    public MetaShape(Shape shape){
        this.shape = shape;

        this.stroke = new CustomStroke();
        this.lineColor = Constants.DEFAULT_LINE_COLOR;
        this.fillColor = Constants.DEFAULT_FILL_COLOR;
        this.lineSize = Constants.DEFAULT_LINE_SIZE;
        this.dashSize = Constants.DEFAULT_DASH_SIZE;
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
            new CustomStroke(
                lineSize, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 10,
                new float[]{dashSize}, 0
            );
    }

    abstract public void initDraw(Point startP);
    abstract public void setCoordinate(Point currentP);
    abstract public MetaShape clone();
}