package containers;

import static global.Constants.CROSSHAIR_CURSOR;
import static global.Constants.DEFAULT_BACKGROUND_COLOR;
import static global.Constants.DEFAULT_CURSOR;
import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;
import static global.Constants.DEFAULT_LINE_SIZE;

import enums.DrawingMode;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tools.draw.Draw;
import tools.draw.Polygon;

public class DrawingPanel extends JPanel implements Printable {

  private DrawingMode drawingMode;
  private ArrayList<Draw> shapeList;
  private Draw currentShape;
  private Color lineColor, fillColor;
  private int lineSize, dashSize;
  private Cursor cursor;

  public DrawingPanel() {
    super();
    this.setBackground(DEFAULT_BACKGROUND_COLOR);

    drawingMode = DrawingMode.IDLE;

    lineColor = DEFAULT_LINE_COLOR;
    fillColor = DEFAULT_FILL_COLOR;
    lineSize = DEFAULT_LINE_SIZE;
    dashSize = DEFAULT_DASH_SIZE;

    this.setCursor(DEFAULT_CURSOR);

    shapeList = new ArrayList<>();

    MouseDrawingHandler drawingHandler = new MouseDrawingHandler();
    addMouseListener(drawingHandler);
    addMouseMotionListener(drawingHandler);
  }

  public Object getShapeList() {
    return this.shapeList;
  }

  public void setShapeList(ArrayList shapeList) {
    this.shapeList = shapeList;
    this.repaint();
  }

  public void setCurrentShape(Draw currentShape) {
    this.currentShape = currentShape;
    drawingMode = DrawingMode.IDLE;
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
    shapeList.forEach(shape -> shape.draw(g2D));
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
    drawingMode = DrawingMode.IDLE;
    repaint();
  }

  public void clean() {
    shapeList.clear();
    repaint();
  }

  private void changeCursor(Point point) {
    cursor = onShape(point) ? CROSSHAIR_CURSOR : DEFAULT_CURSOR;
    this.setCursor(cursor);
  }

  private boolean onShape(Point point) {
    for (Draw shape : shapeList) {
      if (shape.contains(point)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return NO_SUCH_PAGE;
    }
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    shapeList.forEach(shape -> shape.draw(graphics2D));
    return PAGE_EXISTS;
  }

  private class MouseDrawingHandler extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (drawingMode == DrawingMode.IDLE) {
        initDraw(e.getPoint());
        if (currentShape instanceof Polygon) {
          drawingMode = DrawingMode.POLYGON;
        } else {
          drawingMode = DrawingMode.GENERAL;
        }
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      Point point = e.getPoint();
      changeCursor(point);

      if (drawingMode == DrawingMode.POLYGON) {
        draw(point);
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      draw(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (drawingMode == DrawingMode.GENERAL) {
        finish(currentShape);
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (drawingMode == DrawingMode.POLYGON) {
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
