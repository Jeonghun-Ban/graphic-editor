package views.containers;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import global.DrawMode;
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
import tools.transformer.Rotator;
import tools.transformer.Transformer;
import tools.transformer.Translator;
import utils.CursorManager;
import views.dialogs.ColorDialog;
import views.toolbar.ToolBar;


public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;

  private static DrawingPanel drawingPanel;

  private Class<? extends DrawShape> shapeClass;
  private boolean updated;
  private List<DrawShape> drawShapes;
  private DrawShape currentShape;
  private DrawShape selectedShape;
  private Transformer transformer;
  private DrawMode drawMode;

  private DrawingPanel() {
    super();
    this.setBackground(DEFAULT_BACKGROUND_COLOR);

    shapeClass = null;
    drawShapes = new ArrayList<>();
    transformer = null;

    setDrawMode(DrawMode.IDLE);

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

  public void setShapeClass(Class<? extends DrawShape> shapeClass) {
    drawMode = DrawMode.IDLE;
    this.shapeClass = shapeClass;
  }

  public void setDrawMode(DrawMode drawMode) {
    this.drawMode = drawMode;
  }

  public List<DrawShape> getDrawShapes() {
    return this.drawShapes;
  }

  public void setDrawShapes(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    repaint();
  }

  private Optional<DrawShape> getSelectedShape() {
    return Optional.ofNullable(this.selectedShape);
  }

  public void setSelectedShape(DrawShape selectedShape) {
    this.selectedShape = selectedShape;
  }

  public void setTransformer(Transformer transformer) {
    this.transformer = transformer;
  }

  public Optional<Transformer> getTransformer() {
    return Optional.ofNullable(this.transformer);
  }

  public void setLineColor(Color color) {
    getSelectedShape().ifPresent(shape -> {
      shape.setLineColor(color);
      setUpdated(true);
      repaint();
    });
  }

  public void setFillColor(Color color) {
    getSelectedShape().ifPresent(shape -> {
      shape.setFillColor(color);
      setUpdated(true);
      repaint();
    });
  }

  public void setLineSize(int lineSize) {
    getSelectedShape().ifPresent(shape -> {
      shape.setLineSize(lineSize);
      setUpdated(true);
      repaint();
    });
  }

  public void setDashSize(int dashSize) {
    getSelectedShape().ifPresent(shape -> {
      shape.setDashSize(dashSize);
      setUpdated(true);
      repaint();
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    drawShapes.forEach(shape -> shape.draw(g2D));
  }

  private void initDraw() {
    unselectShapes();
    try {
      currentShape = shapeClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void clear() {
    drawShapes.clear();
    repaint();
  }

  public void remove() {
    getSelectedShape().ifPresent(shape -> {
      getDrawShapes().remove(shape);
      repaint();
    });
  }

  private Optional<DrawShape> onShape(Point point) {
    List<DrawShape> reversedList = new ArrayList<>(List.copyOf(drawShapes));
    Collections.reverse(reversedList);
    return reversedList.stream().filter(shape -> shape.onShape(point)).findFirst();
  }

  private void changeCursor(Point point) {
    if (shapeClass.equals(Selection.class)) {
      setCursor(
          onShape(point).isPresent() ? CursorManager.MOVE_CURSOR : CursorManager.DEFAULT_CURSOR);
      getSelectedShape().flatMap(shape -> shape.onAnchor(point))
          .ifPresent(anchor -> setCursor(AnchorCursor.valueOf(anchor.name()).getCursor()));
    } else {
      setCursor(CursorManager.CROSSHAIR_CURSOR);
    }
  }

  private Optional<DrawShape> selectShape(Point point) {
    unselectShapes();
    Optional<DrawShape> selectedShape = onShape(point);
    selectedShape.ifPresent(shape -> {
      selectShape(shape);
      getDrawShapes().remove(shape);
      getDrawShapes().add(shape);
    });
    return selectedShape;
  }

  public void selectShape(DrawShape drawShape) {
    setSelectedShape(drawShape);
    drawShape.setSelected(true);

    ToolBar.getInstance().setDashSize(drawShape.getDashSize());
    ToolBar.getInstance().setLineSize(drawShape.getLineSize());
    ColorDialog.getInstance().setLineColor(drawShape.getLineColor());
    ColorDialog.getInstance().setFillColor(drawShape.getFillColor());

    repaint();
  }

  private void unselectShapes() {
    setSelectedShape(null);
    getDrawShapes().forEach(shape -> shape.setSelected(false));
    repaint();
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return NO_SUCH_PAGE;
    }
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    drawShapes.forEach(shape -> shape.draw(graphics2D));
    return PAGE_EXISTS;
  }

  private class MouseDrawingHandler extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (isDrawMode(DrawMode.IDLE)) {
        if (shapeClass.equals(Selection.class)) {
          selectShape(e.getPoint()).ifPresent(
              shape -> shape.onAnchor(e.getPoint()).ifPresentOrElse(anchor -> {
                if (anchor == Anchor.Rotate) {
                  setTransformer(new Rotator(shape));
                  setDrawMode(DrawMode.ROTATE);
                } else {
                  setTransformer(new Resizer(shape));
                  setDrawMode(DrawMode.RESIZE);
                }
              }, () -> {
                if (shape.onShape(e.getPoint())) {
                  setTransformer(new Translator(shape));
                  setDrawMode(DrawMode.TRANSLATE);
                }
              }));
        } else {
          initDraw();
          setTransformer(new Drawer(currentShape));
          setDrawMode((currentShape instanceof Polygon) ? DrawMode.POLYGON : DrawMode.GENERAL);
        }
      }
      getTransformer().ifPresent(transformer -> transformer.init(e.getPoint()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (!isDrawMode(DrawMode.IDLE)) {
        getTransformer().ifPresent(
            transformer -> transformer.transform((Graphics2D) getGraphics(), e.getPoint()));
      }
      if (isDrawMode(DrawMode.TRANSLATE) || isDrawMode(DrawMode.RESIZE) || isDrawMode(
          DrawMode.ROTATE)) {
        repaint();
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (!isDrawMode(DrawMode.POLYGON)) {
        getTransformer().ifPresent(Transformer::finish);
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (isDrawMode(DrawMode.POLYGON)) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          if (e.getClickCount() == 1) {
            ((Drawer) transformer).continueTransform(e.getPoint());
          } else if (e.getClickCount() == 2) {
            getTransformer().ifPresent(Transformer::finish);
          }
        }
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      changeCursor(e.getPoint());
      if (isDrawMode(DrawMode.POLYGON)) {
        getTransformer().ifPresent(
            transformer -> transformer.transform((Graphics2D) getGraphics(), e.getPoint()));
      }
    }
  }
}
