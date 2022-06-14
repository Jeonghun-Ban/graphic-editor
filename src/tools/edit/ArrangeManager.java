package tools.edit;

import java.util.List;
import tools.draw.DrawShape;

public class ArrangeManager {

  private final List<DrawShape> drawShapes;

  public ArrangeManager(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
  }

  public void bringForward(List<DrawShape> selectedShapes) {
    if (!selectedShapes.isEmpty()) {
      drawShapes.removeAll(selectedShapes);
      drawShapes.addAll(selectedShapes);
    }
  }

  public void sendBackward(List<DrawShape> selectedShapes) {
    if (!selectedShapes.isEmpty()) {
      drawShapes.removeAll(selectedShapes);
      drawShapes.addAll(0, selectedShapes);
    }
  }

  public void bringToFront(List<DrawShape> selectedShapes) {
    if (!selectedShapes.isEmpty() && !isForward(selectedShapes)) {
      int firstElementIndex = drawShapes.indexOf(selectedShapes.get(0));
      drawShapes.removeAll(selectedShapes);
      drawShapes.addAll(firstElementIndex + 1, selectedShapes);
    }
  }

  public void sendToBack(List<DrawShape> selectedShapes) {
    if (!selectedShapes.isEmpty() && !isBackward(selectedShapes)) {
      int firstElementIndex = drawShapes.indexOf(selectedShapes.get(0));
      drawShapes.removeAll(selectedShapes);
      drawShapes.addAll(firstElementIndex - 1, selectedShapes);
    }
  }

  private boolean isForward(List<DrawShape> selectedShapes) {
    DrawShape lastElement = selectedShapes.get(selectedShapes.size() - 1);
    int lastElementIndex = drawShapes.indexOf(lastElement);
    return lastElementIndex == drawShapes.size() - 1;
  }

  private boolean isBackward(List<DrawShape> selectedShapes) {
    int firstElementIndex = drawShapes.indexOf(selectedShapes.get(0));
    return firstElementIndex == 0;
  }
}
