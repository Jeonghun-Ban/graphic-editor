package containers;

import global.Constants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tools.draw.Draw;
import tools.draw.Polygon;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Draw> shapeList;
    private Draw currentShape;
    private Constants.DrawingMode drawingMode;
    private Color lineColor, fillColor;
    private int lineSize, dashSize;

    public DrawingPanel() {
        super();
        this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
        lineColor = Constants.DEFAULT_LINE_COLOR;
        fillColor = Constants.DEFAULT_FILL_COLOR;
        lineSize = Constants.DEFAULT_LINE_SIZE;
        dashSize = Constants.DEFAULT_DASH_SIZE;

        shapeList = new ArrayList<>();
        MouseDrawingHandler drawingHandler = new MouseDrawingHandler();
        drawingMode = Constants.DrawingMode.IDLE;

        addMouseListener(drawingHandler);
        addMouseMotionListener(drawingHandler);
    }

    public void setCurrentShape(Draw currentShape) {
        this.currentShape = currentShape;
        drawingMode = Constants.DrawingMode.IDLE;
    }

    public void setLineColor(Color color) {
        this.lineColor = color;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }

    public void setDashSize(int dashSize) {
        this.dashSize = dashSize;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for (Draw shape : shapeList) {
            shape.draw(g2D);
        }
    }

    private void initDraw(Point point) {
        currentShape = currentShape.clone();
        currentShape.initDraw(point);

        currentShape.setLineColor(lineColor);
        currentShape.setFillColor(fillColor);
        currentShape.setLineSize(lineSize);
        currentShape.setDashSize(dashSize);
    }

    private void draw(Point currentP) {
        Graphics2D g2D = (Graphics2D) getGraphics();
        g2D.setXORMode(g2D.getBackground());
        currentShape.draw(g2D);
        currentShape.setCoordinate(currentP);
        currentShape.draw(g2D);
    }

    private void continueDraw(Point p) {
        ((Polygon) currentShape).continueDrawing(p);
    }

    private void finish(Draw shape) {
        shapeList.add(shape);
        drawingMode = Constants.DrawingMode.IDLE;
        repaint();
    }

    public void clean() {
        shapeList.clear();
        repaint();
    }

    public Object getShapeList() {
        return this.shapeList;
    }

    public void setShapeList(ArrayList shapeList) {
        this.shapeList = shapeList;
        this.repaint();
    }

    private class MouseDrawingHandler extends MouseInputAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (drawingMode == Constants.DrawingMode.IDLE && currentShape != null) {
                initDraw(e.getPoint());
                if (currentShape instanceof Polygon) {
                    drawingMode = Constants.DrawingMode.POLYGON;
                } else {
                    drawingMode = Constants.DrawingMode.GENERAL;
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (drawingMode == Constants.DrawingMode.POLYGON) {
                draw(e.getPoint());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape != null) draw(e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (drawingMode == Constants.DrawingMode.GENERAL) {
                finish(currentShape);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (drawingMode == Constants.DrawingMode.POLYGON) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() == 1) {
                        continueDraw(e.getPoint());
                    } else if (e.getClickCount() == 2) {
                        finish(currentShape);
                    }
                }
            }
        }
    }
}