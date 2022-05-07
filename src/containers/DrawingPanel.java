package containers;

import static global.Constants.CROSSHAIR_CURSOR;
import static global.Constants.DEFAULT_BACKGROUND_COLOR;
import static global.Constants.DEFAULT_CURSOR;
import static global.Constants.DEFAULT_DASH_SIZE;
import static global.Constants.DEFAULT_FILL_COLOR;
import static global.Constants.DEFAULT_LINE_COLOR;
import static global.Constants.DEFAULT_LINE_SIZE;
import static global.Constants.HAND_CURSOR;

import enums.DrawMode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tools.draw.DrawShape;
import tools.draw.Polygon;
import transformer.Drawer;
import transformer.Mover;
import transformer.Transformer;

public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;

  private boolean updated;

  private List<DrawShape> drawShapes;
  private DrawShape currentShape, selectedShape;
  private Transformer transformer;
  private Color lineColor, fillColor;
  private int lineSize, dashSize;

  private DrawMode drawMode;

  public DrawingPanel() {
    super();
    this.setBackground(DEFAULT_BACKGROUND_COLOR);

    drawShapes = new ArrayList<>();
    currentShape = null;
    selectedShape = null;

    this.transformer = null;
    this.setDrawMode(DrawMode.IDLE);
    this.setCursor(DEFAULT_CURSOR);

    lineColor = DEFAULT_LINE_COLOR;
    fillColor = DEFAULT_FILL_COLOR;
    lineSize = DEFAULT_LINE_SIZE;
    dashSize = DEFAULT_DASH_SIZE;

    MouseDrawingHandler drawingHandler = new MouseDrawingHandler();
    addMouseListener(drawingHandler);
    addMouseMotionListener(drawingHandler);
  }

  public boolean isUpdated() {
    return this.updated;
  }

  public boolean isCurrentShape(DrawShape currentShape) {
    return this.currentShape == currentShape;
  }

  public boolean isSelectedShape(DrawShape selectedShape) {
    return this.selectedShape == selectedShape;
  }

  public boolean isDrawMode(DrawMode drawMode) {
    return this.drawMode == drawMode;
  }

  public void setUpdated(boolean updated) {
    this.updated = updated;
  }

  private void setDrawMode(DrawMode drawMode) {
    this.drawMode = drawMode;
  }

  public Object getDrawShapes() {
    return this.drawShapes;
  }

  public void setDrawShapes(ArrayList<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    this.repaint();
  }

  public void setCurrentShape(DrawShape currentShape) {
    this.currentShape = currentShape;
    drawMode = DrawMode.IDLE;
  }

  public void setSelectedShape(DrawShape selectedShape) {
    this.selectedShape = selectedShape;
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

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    drawShapes.forEach(drawShape -> drawShape.draw(g2D));
  }

  @Override
  public void repaint() {
    super.repaint();
    setDrawMode(DrawMode.IDLE);
  }

  private void initDraw() {
    currentShape = currentShape.clone();
    currentShape.setStyleAttributes(lineColor, fillColor, lineSize, dashSize);
    setCursor(CROSSHAIR_CURSOR);
    deselectShapes();
  }

  private void finishDraw() {
    setUpdated(true);
    selectShape(currentShape);
    repaint();
  }

  private void finishMove() {
    this.setUpdated(true);
    this.setSelectedShape(null);
    repaint();
  }

  public void remove() {
    if (selectedShape!=null) {
      drawShapes.remove(selectedShape);
      setSelectedShape(null);
    }
    repaint();
  }

  private Optional<DrawShape> onShape(Point point) {
    List<DrawShape> reversedList = new ArrayList<>(List.copyOf(drawShapes));
    Collections.reverse(reversedList);
    return reversedList.stream().filter(drawShape -> drawShape.contains(point)).findFirst();
  }

  private void changeCursor(Point point) {
    Optional<DrawShape> drawShape = onShape(point);
    this.setCursor(drawShape.isPresent() ? HAND_CURSOR : DEFAULT_CURSOR);
  }

  private void selectShape(Point point) {
    Optional<DrawShape> drawShape = onShape(point);
    deselectShapes();
    if (drawShape.isPresent()) {
      selectShape(drawShape.get());
      drawShapes.remove(selectedShape);
      drawShapes.add(selectedShape);
    }
  }

  private void selectShape(DrawShape drawShape) {
    setSelectedShape(drawShape);
    this.selectedShape.setSelected(true);
  }

  private void deselectShapes() {
    drawShapes.forEach(drawShape -> {
      drawShape.setSelected(false);
    });
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return NO_SUCH_PAGE;
    }
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    drawShapes.forEach(drawShape -> drawShape.draw(graphics2D));
    return PAGE_EXISTS;
  }

  private class MouseDrawingHandler extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (isDrawMode(DrawMode.IDLE)) {
        if (!isCurrentShape(null)) {
          initDraw();
          transformer = new Drawer(currentShape);
          transformer.init(e.getPoint());
          drawMode = (currentShape instanceof Polygon) ? DrawMode.POLYGON : DrawMode.GENERAL;
        } else {
          selectShape(e.getPoint());
          if (!isSelectedShape(null)) {
            transformer = new Mover(selectedShape);
            transformer.init(e.getPoint());
            setDrawMode(DrawMode.MOVE);
          }
        }
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (!isDrawMode(DrawMode.IDLE)) {
        transformer.transform((Graphics2D) getGraphics(), e.getPoint());
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (isDrawMode(DrawMode.GENERAL)) {
        ((Drawer) transformer).finish(drawShapes);
        finishDraw();
      } else if (isDrawMode(DrawMode.MOVE)) {
        finishMove();
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (isDrawMode(DrawMode.POLYGON)) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          if (e.getClickCount() == 1) {
            ((Drawer) transformer).continueTransform(e.getPoint());
          } else if (e.getClickCount() == 2) {
            ((Drawer) transformer).finish(drawShapes);
            finishDraw();
          }
        }
      } else if (isDrawMode(DrawMode.IDLE) && isCurrentShape(null)) {
        selectShape(e.getPoint());
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      changeCursor(e.getPoint());
      if (isDrawMode(DrawMode.POLYGON)) {
        transformer.transform((Graphics2D) getGraphics(), e.getPoint());
      }
    }

  }
}
