package tools.edit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tools.draw.DrawShape;
import views.containers.DrawingPanel;

public class CopyManager {

  private final List<DrawShape> drawShapes;
  private final List<DrawShape> copyShapes;

  public CopyManager(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    this.copyShapes = new ArrayList<>();
  }

  public void copy(List<DrawShape> selectedShapes) {
    if(!selectedShapes.isEmpty()) {
      copyShapes.clear();
      Iterator<DrawShape> iter = selectedShapes.listIterator();
      while (iter.hasNext()) {
        DrawShape copyShape = iter.next().clone();
        copyShapes.add(copyShape);
      }
    }
  }

  public void paste() {
    if (!copyShapes.isEmpty()) {
      copyShapes.forEach(copyShape -> {
        drawShapes.add(copyShape);
        DrawingPanel.getInstance().addSelectedShape(copyShape);
      });
    }
  }
}
