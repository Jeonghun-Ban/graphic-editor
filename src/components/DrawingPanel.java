package components;

import constants.DrawingMode;
import shapes.MetaShape;
import shapes.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanel extends JPanel {

    private MouseDrawingHandler drawingHandler;
    private MetaShape currentShape;
    private ArrayList<MetaShape> shapeList;
    private DrawingMode drawingMode;

    public DrawingPanel() {
        super();
        shapeList = new ArrayList<>();
        drawingHandler = new MouseDrawingHandler();
        drawingMode = DrawingMode.IDLE;

        addMouseListener(drawingHandler);
        addMouseMotionListener(drawingHandler);
    }

    public void setCurrentShape(MetaShape currentShape) {
        this.currentShape = currentShape;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        for(MetaShape shape:shapeList){
            shape.draw(g2D);
        }
    }

    private void initDraw(Point point) {
        currentShape = currentShape.clone();
        currentShape.initDraw(point);
    }

    private void draw(Point currentP) {
        Graphics2D g2D = (Graphics2D) getGraphics();
        g2D.setXORMode(g2D.getBackground());
        currentShape.draw(g2D);
        currentShape.setCoordinate(currentP);
        currentShape.draw(g2D);
    }

    private void continueDraw(Point p){
        ((Polygon) currentShape).continueDrawing(p);
    }

    private void finish(MetaShape shape) {
        shapeList.add(shape);
        drawingMode = DrawingMode.IDLE;
        repaint();
    }


    private class MouseDrawingHandler extends MouseInputAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(drawingMode == DrawingMode.IDLE){
                initDraw(e.getPoint());
                if(currentShape instanceof Polygon){
                    drawingMode = DrawingMode.POLYGON;
                } else {
                    drawingMode = DrawingMode.GENERAL;
                }
            }
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            if(drawingMode == DrawingMode.POLYGON){
                draw(e.getPoint());
            }
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            draw(e.getPoint());
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if(drawingMode == DrawingMode.GENERAL){
                finish(currentShape);
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(drawingMode == DrawingMode.POLYGON){
                if(e.getButton() == MouseEvent.BUTTON1){
                    continueDraw(e.getPoint());
                } else if(e.getButton() == MouseEvent.BUTTON3){
                    finish(currentShape);
                }
            }
        }
    }
}
