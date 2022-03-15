package components;

import graphics.MetaShape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanel extends JPanel{

    private MouseDrawingHandler drawingHandler;
    private MetaShape currentShape;
    private ArrayList<MetaShape> shapeList;

    public DrawingPanel() {
        super();
        shapeList = new ArrayList<MetaShape>();
        drawingHandler = new MouseDrawingHandler();

        addMouseListener(drawingHandler);
        addMouseMotionListener(drawingHandler);
    }

    public void setCurrentShape(MetaShape currentShape) {
        this.currentShape = currentShape;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        for(MetaShape shape :shapeList){
            shape.draw(g2D);
        }
    }

    private void animateDraw(Point currentP) {
        Graphics2D g2D = (Graphics2D) getGraphics();
        g2D.setXORMode(g2D.getBackground());
        currentShape.draw(g2D);
        currentShape.setCoordinate(currentP);
        currentShape.draw(g2D);
    }

    private class MouseDrawingHandler extends MouseInputAdapter {
        public void mousePressed(MouseEvent e){
            currentShape = currentShape.clone();
            currentShape.initDraw(e.getPoint());
        }
        public void mouseDragged(MouseEvent e){
            animateDraw(e.getPoint());
        }
        public void mouseReleased(MouseEvent e){
            shapeList.add(currentShape);
        }
    }
}
