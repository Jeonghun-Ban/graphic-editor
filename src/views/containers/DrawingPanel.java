package views.containers;

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
import tools.AnchorCursor;
import tools.anchor.Anchor;
import tools.draw.DrawShape;
import tools.draw.Polygon;
import tools.draw.Selection;
import tools.transformer.Drawer;
import tools.transformer.Resizer;
import tools.transformer.Transformer;
import tools.transformer.Translator;


public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;
  private static DrawingPanel drawingPanel;

  private boolean updated;
  private List<DrawShape> drawShapes;
  private DrawShape currentShape;
  private DrawShape selectedShape;
  private Transformer transformer;
  private Color lineColor;
  private Color fillColor;
  private int lineSize;
  private int dashSize;
  private DrawMode drawMode;

  private DrawingPanel() {
    super();
    this.setBackground(DEFAULT_BACKGROUND_COLOR);

    drawShapes = new ArrayList<>();
    transformer = null;

    setDrawMode(DrawMode.IDLE);
    setCursor(DEFAULT_CURSOR);
    setDefaultStyle();

    MouseDrawingHandler drawingHandler = new MouseDrawingHandler();
    addMouseListener(drawingHandler);
    addMouseMotionListener(drawingHandler);
  }

  public static DrawingPanel getInstance() {
    if (drawingPanel == null) {
      drawingPanel = new DrawingPanel();
    }
    return drawingPanel;
  }

  public boolean isUpdated() {
    return this.updated;
  }

  public void setUpdated(boolean updated) {
    this.updated = updated;
  }

  public boolean isDrawMode(DrawMode drawMode) {
    return this.drawMode == drawMode;
  }

  private void setDrawMode(DrawMode drawMode) {
    this.drawMode = drawMode;
  }

  public Object getDrawShapes() {
    return this.drawShapes;
  }

  public void setDrawShapes(ArrayList<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    repaint();
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

  public void setDefaultStyle() {
    setLineColor(DEFAULT_LINE_COLOR);
    setFillColor(DEFAULT_FILL_COLOR);
    setLineSize(DEFAULT_LINE_SIZE);
    setDashSize(DEFAULT_DASH_SIZE);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    drawShapes.forEach(drawShape -> drawShape.draw(g2D));
  }

  private void initDraw() {
    currentShape = currentShape.clone();
    currentShape.setLineSize(lineSize);
    currentShape.setDashSize(dashSize);
    currentShape.setLineColor(lineColor);
    currentShape.setFillColor(fillColor);
    deselectShapes();
  }

  private void finish() {
    this.setUpdated(true);
    setDrawMode(DrawMode.IDLE);
    repaint();
  }

  public void clear() {
    drawShapes.clear();
    repaint();
  }

  public void remove() {
    if (selectedShape != null) {
      drawShapes.remove(selectedShape);
      setSelectedShape(null);
      setDrawMode(DrawMode.IDLE);
      repaint();
    }
  }

  private Optional<DrawShape> onShape(Point point) {
    List<DrawShape> reversedList = new ArrayList<>(List.copyOf(drawShapes));
    Collections.reverse(reversedList);
    return reversedList.stream().filter(drawShape -> drawShape.onShape(point)).findFirst();
  }

  private void changeCursor(Point point) {
    if (currentShape instanceof Selection) {
      setCursor(DEFAULT_CURSOR);

      Optional<DrawShape> drawShape = onShape(point);
      drawShape.ifPresent(shape -> setCursor(HAND_CURSOR));

      if (selectedShape != null) {
        Anchor selectedAnchor = selectedShape.onAnchor(point);
        if (selectedAnchor != null) {
          AnchorCursor anchorCursor = AnchorCursor.valueOf(selectedAnchor.name());
          setCursor(anchorCursor.getCursor());
        }
      }

    } else {
      setCursor(CROSSHAIR_CURSOR);
    }
  }

  private void selectShape(Point point) {
    deselectShapes();

    Optional<DrawShape> drawShape = onShape(point);
    drawShape.ifPresent(shape -> {
      selectShape(shape);
      drawShapes.remove(selectedShape);
      drawShapes.add(selectedShape);
    });
    setDrawMode(DrawMode.IDLE);
    repaint();
  }

  private void selectShape(DrawShape drawShape) {
    this.setSelectedShape(drawShape);
    this.selectedShape.setSelected(true);
  }

  private void deselectShapes() {
    drawShapes.forEach(drawShape -> drawShape.setSelected(false));
    this.setSelectedShape(null);
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
        if (currentShape instanceof Selection && selectedShape != null) {
          if (selectedShape.onAnchor(e.getPoint()) == null) {
            if (selectedShape.onShape(e.getPoint())){
              transformer = new Translator(selectedShape);
              transformer.init(e.getPoint());
              setDrawMode(DrawMode.TRANSLATE);
            }
          } else {
            transformer = new Resizer(selectedShape);
            transformer.init(e.getPoint());
            setDrawMode(DrawMode.RESIZE);
          }
        } else {
          initDraw();
          transformer = new Drawer(currentShape);
          transformer.init(e.getPoint());
          drawMode = (currentShape instanceof Polygon) ? DrawMode.POLYGON : DrawMode.GENERAL;
        }
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (!isDrawMode(DrawMode.IDLE)) {
        transformer.transform((Graphics2D) getGraphics(), e.getPoint());
      }
      if (isDrawMode(DrawMode.TRANSLATE) || isDrawMode(DrawMode.RESIZE)) {
        repaint();
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (isDrawMode(DrawMode.GENERAL)) {
        ((Drawer) transformer).finish(drawShapes);
        selectShape(currentShape);
        finish();
      } else if (isDrawMode(DrawMode.TRANSLATE)) {
        ((Translator) transformer).finish();
        finish();
      } else if (isDrawMode(DrawMode.RESIZE)) {
        finish();
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
            finish();
          }
        }
      } else if (currentShape instanceof Selection) {
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
