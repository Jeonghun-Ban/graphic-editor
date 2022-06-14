package views.containers;

import static global.Constants.DEFAULT_BACKGROUND_COLOR;

import global.DrawMode;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tools.AnchorCursor;
import tools.anchor.Anchor;
import tools.draw.DrawShape;
import tools.draw.Group;
import tools.draw.Polygon;
import tools.draw.Selection;
import tools.edit.CopyManager;
import tools.edit.UndoManager;
import tools.transformer.Drawer;
import tools.transformer.Grouper;
import tools.transformer.Resizer;
import tools.transformer.Rotator;
import tools.transformer.Transformer;
import tools.transformer.Translator;
import utils.CursorManager;
import views.dialogs.ColorDialog;
import views.menu.PopMenu;
import views.toolbar.ToolBar;


public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;

  private static DrawingPanel drawingPanel;

  private List<DrawShape> drawShapes;
  private BufferedImage bufferedImage;
  private Graphics2D graphicsBufferedImage;

  private boolean updated;
  private DrawMode drawMode;
  private Class<? extends DrawShape> shapeClass;
  private DrawShape currentShape;
  private List<DrawShape> selectedShapes;

  private Transformer transformer;
  private UndoManager undoManager;
  private CopyManager copyManager;

  private DrawingPanel() {
    super();

    this.drawShapes = new ArrayList<>();
    this.selectedShapes = new ArrayList<>();
    this.shapeClass = null;

    this.transformer = null;
    this.undoManager = new UndoManager(drawShapes);
    this.copyManager = new CopyManager(drawShapes);

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

  public void initialize() {
    this.bufferedImage = (BufferedImage) this.createImage(this.getWidth(), this.getHeight());
    this.graphicsBufferedImage = this.bufferedImage.createGraphics();
    this.graphicsBufferedImage.setBackground(DEFAULT_BACKGROUND_COLOR);
    this.add(PopMenu.getInstance());
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

  public void setSelectedShapes(List<DrawShape> selectedShapes) {
    this.selectedShapes = selectedShapes;
  }

  public void addSelectedShape(DrawShape selectedShape) {
    this.selectedShapes.add(selectedShape);
  }

  public void setTransformer(Transformer transformer) {
    this.undoManager.reset();
    this.transformer = transformer;
  }

  public Optional<Transformer> getTransformer() {
    return Optional.ofNullable(this.transformer);
  }

  public void updateLineColor(Color color) {
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(selectedShape -> selectedShape.setLineColor(color));
      setUpdated(true);
      repaint();
    }
  }

  public void updateFillColor(Color color) {
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(selectedShape -> selectedShape.setFillColor(color));
      setUpdated(true);
      repaint();
    }
  }

  public void updateLineSize(int lineSize) {
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(selectedShape -> selectedShape.setLineSize(lineSize));
      setUpdated(true);
      repaint();
    }
  }

  public void updateDashSize(int dashSize) {
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(selectedShape -> selectedShape.setDashSize(dashSize));
      setUpdated(true);
      repaint();
    }
  }

  public void undo() {
    undoManager.undo();
    repaint();
  }

  public void redo() {
    undoManager.redo();
    repaint();
  }

  public void copy() {
    copyManager.copy(selectedShapes);
    repaint();
  }

  public void cut() {
    copyManager.copy(selectedShapes);
    remove();
    repaint();
  }

  public void paste() {
    copyManager.paste();
    repaint();
  }

  public void group() {
    Group group = new Group();
    boolean groupAble = false;

    Iterator<DrawShape> iterator = drawShapes.listIterator();
    while (iterator.hasNext()) {
      DrawShape drawShape = iterator.next();
      if (drawShape.isSelected()) {
        drawShape.setSelected(false);
        group.add(drawShape);
        iterator.remove();
        groupAble = true;
      }
    }
    if (groupAble) {
      drawShapes.add(group);
      selectedShapes.add(group);
      group.setSelected(true);
    }
    repaint();
  }

  public void unGroup() {
    List<DrawShape> tmpList = new ArrayList<>();
    Iterator<DrawShape> iterator = drawShapes.listIterator();
    while (iterator.hasNext()) {
      DrawShape drawShape = iterator.next();
      if (drawShape instanceof Group && drawShape.isSelected()) {
        ((Group) drawShape).getChildShapes().forEach(childShape -> {
          childShape.setSelected(true);
          tmpList.add(childShape);
        });
        iterator.remove();
      }
    }
    drawShapes.addAll(tmpList);
    repaint();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    this.graphicsBufferedImage.clearRect(0, 0, this.bufferedImage.getWidth(),
        this.bufferedImage.getHeight());
    drawShapes.forEach(shape -> shape.draw(graphicsBufferedImage));
    g.drawImage(this.bufferedImage, 0, 0, this);
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
    Iterator<DrawShape> iter = drawShapes.listIterator();
    while (iter.hasNext()) {
      DrawShape drawShape = iter.next();
      if (selectedShapes.contains(drawShape)) {
        iter.remove();
      }
    }
    unselectShapes();
  }

  private Optional<DrawShape> onShape(Point point) {
    List<DrawShape> reversedList = new ArrayList<>(List.copyOf(drawShapes));
    Collections.reverse(reversedList);
    return reversedList.stream().filter(shape -> shape.onShape(point)).findFirst();
  }

  private Optional<Anchor> onAnchor(Point point) {
    Optional<Anchor> anchor = selectedShapes.stream()
        .filter(selectedShape -> selectedShape.onAnchor(point).isPresent()).findFirst()
        .flatMap(selectedShape -> selectedShape.onAnchor(point));
    return anchor;
  }

  private void changeCursor(Point point) {
    if (shapeClass.equals(Selection.class)) {
      setCursor(onAnchor(point).isPresent() ? getAnchorCursor(point)
          : onShape(point).isPresent() ? CursorManager.MOVE_CURSOR : CursorManager.DEFAULT_CURSOR);
    } else {
      setCursor(CursorManager.CROSSHAIR_CURSOR);
    }
  }

  private Cursor getAnchorCursor(Point point) {
    Optional<Anchor> anchor = onAnchor(point);
    if (anchor.isPresent()) {
      return AnchorCursor.valueOf(anchor.get().name()).getCursor();
    }
    return null;
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

  public void selectShape(DrawShape selectedShape) {
    selectedShapes.add(selectedShape);
    selectedShape.setSelected(true);

    ToolBar.getInstance().setDashSize(selectedShape.getDashSize());
    ToolBar.getInstance().setLineSize(selectedShape.getLineSize());
    ColorDialog.getInstance().setLineColor(selectedShape.getLineColor());
    ColorDialog.getInstance().setFillColor(selectedShape.getFillColor());

    repaint();
  }

  private void unselectShapes() {
    selectedShapes.forEach(selectedShape -> selectedShape.setSelected(false));
    selectedShapes.clear();
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
          if (e.getButton() == MouseEvent.BUTTON3) {
            PopMenu.getInstance().show(DrawingPanel.getInstance(), e.getX(), e.getY());
          } else {
            selectShape(e.getPoint()).ifPresentOrElse(
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
                }), () -> {
                  Selection selection = new Selection();
                  setTransformer(new Grouper(selection));
                  setDrawMode(DrawMode.GROUP);
                });
          }
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
        getTransformer().ifPresent(transformer -> {
          transformer.transform(graphicsBufferedImage, e.getPoint());
          DrawingPanel.getInstance().graphicsBufferedImage.setPaintMode();
          getGraphics().drawImage(bufferedImage, 0, 0, DrawingPanel.getInstance());
        });
      }
      if (isDrawMode(DrawMode.TRANSLATE) || isDrawMode(DrawMode.RESIZE) || isDrawMode(
          DrawMode.ROTATE)) {
        repaint();
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (isDrawMode(DrawMode.GROUP)) {
        ((Grouper) transformer).finish(drawShapes);
      } else if (!isDrawMode(DrawMode.POLYGON)) {
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
        getTransformer().ifPresent(transformer -> {
          transformer.transform(graphicsBufferedImage, e.getPoint());
          DrawingPanel.getInstance().graphicsBufferedImage.setPaintMode();
          getGraphics().drawImage(bufferedImage, 0, 0, DrawingPanel.getInstance());
        });
      }
    }
  }
}
