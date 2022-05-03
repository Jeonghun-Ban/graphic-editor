package containers;

import static global.Constants.CROSSHAIR_CURSOR;
import static global.Constants.DEFAULT_BACKGROUND_COLOR;
import static global.Constants.DEFAULT_CURSOR;
import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;
import static global.Constants.DEFAULT_LINE_SIZE;

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
import state.DrawingMode;
import tools.draw.DrawTool;
import tools.draw.Polygon;

public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;

  private boolean updated;

  private ArrayList<DrawTool> drawTools;
  private DrawTool drawTool;
  private Cursor cursor;
  private Color lineColor, fillColor;
  private int lineSize, dashSize;

  private DrawingMode drawingMode;

  public DrawingPanel() {
    super();
    this.setBackground(DEFAULT_BACKGROUND_COLOR);

    drawTools = new ArrayList<>();
    this.setCursor(DEFAULT_CURSOR);
    lineColor = DEFAULT_LINE_COLOR;
    fillColor = DEFAULT_FILL_COLOR;
    lineSize = DEFAULT_LINE_SIZE;
    dashSize = DEFAULT_DASH_SIZE;

    drawingMode = DrawingMode.IDLE;

    MouseDrawingHandler drawingHandler = new MouseDrawingHandler();
    addMouseListener(drawingHandler);
    addMouseMotionListener(drawingHandler);
  }

  public boolean isUpdated() {
    return this.updated;
  }

  public boolean isDrawTool(DrawTool drawTool) {
    return this.drawTool == drawTool;
  }

  public boolean isDrawingMode(DrawingMode drawingMode) {
    return this.drawingMode == drawingMode;
  }

  public void setUpdated(boolean updated) {
    this.updated = updated;
  }

  public Object getDrawTools() {
    return this.drawTools;
  }

  public void setDrawTools(ArrayList<DrawTool> drawTools) {
    this.drawTools = drawTools;
    this.repaint();
  }

  public void setDrawTool(DrawTool drawTool) {
    this.drawTool = drawTool;
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
    drawTools.forEach(drawTool -> drawTool.draw(g2D));
  }

  private void initDraw(Point point) {
    drawTool = drawTool.clone();
    drawTool.setStartPoint(point);
    drawTool.setLineColor(lineColor);
    drawTool.setFillColor(fillColor);
    drawTool.setLineSize(lineSize);
    drawTool.setDashSize(dashSize);
  }

  private void draw(Point point) {
    Graphics2D g2D = (Graphics2D) getGraphics();
    g2D.setXORMode(g2D.getBackground());
    drawTool.draw(g2D);
    drawTool.setCurrentPoint(point);
    drawTool.draw(g2D);
  }

  private void continueDraw(Point p) {
    ((Polygon) drawTool).continueDrawing(p);
  }

  private void finish(DrawTool drawTool) {
    drawTools.add(drawTool);
    drawingMode = DrawingMode.IDLE;
    repaint();
    this.setUpdated(true);
  }

  public void clean() {
    drawTools.clear();
    repaint();
  }

  private void changeCursor(Point point) {
    cursor = onShape(point) ? CROSSHAIR_CURSOR : DEFAULT_CURSOR;
    this.setCursor(cursor);
  }

  private boolean onShape(Point point) {
    for (DrawTool drawTool : drawTools) {
      if (drawTool.contains(point)) {
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
    drawTools.forEach(drawTool -> drawTool.draw(graphics2D));
    return PAGE_EXISTS;
  }

  private class MouseDrawingHandler extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (isDrawingMode(DrawingMode.IDLE) && !isDrawTool(null)) {
        initDraw(e.getPoint());
        if (drawTool instanceof Polygon) {
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

      if (isDrawingMode(DrawingMode.POLYGON)) {
        draw(point);
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if(isDrawingMode(DrawingMode.GENERAL)) {
        draw(e.getPoint());
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (isDrawingMode(DrawingMode.GENERAL)) {
        finish(drawTool);
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (isDrawingMode(DrawingMode.POLYGON)) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          if (e.getClickCount() == 1) {
            continueDraw(e.getPoint());
          } else if (e.getClickCount() == 2) {
            finish(drawTool);
          }
        }
      }
    }
  }
}
