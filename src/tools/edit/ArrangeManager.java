package tools.edit;

import java.util.List;
import tools.draw.DrawShape;

public class ArrangeManager {

  private final List<DrawShape> drawShapes;

  public ArrangeManager(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
  }

  public void bringForward(List<DrawShape> selectedShapes) {
    drawShapes.removeAll(selectedShapes);
    drawShapes.addAll(selectedShapes);
  }

  public void sendBackward(List<DrawShape> selectedShapes) {
    drawShapes.removeAll(selectedShapes);
    drawShapes.addAll(0, selectedShapes);
  }

  public void bringToFront(List<DrawShape> selectedShapes) {
    DrawShape lastElement = selectedShapes.get(selectedShapes.size() - 1);
    int lastElementIndex = drawShapes.indexOf(lastElement);
    if (!isForward(lastElementIndex)) {
      drawShapes.removeAll(selectedShapes);
      drawShapes.addAll(lastElementIndex + 1, selectedShapes);
    }
  }

  public void sendToBack(List<DrawShape> selectedShapes) {
    int firstElementIndex = drawShapes.indexOf(selectedShapes.get(0));
    if (!isBackward(firstElementIndex)) {
      drawShapes.removeAll(selectedShapes);
      drawShapes.addAll(firstElementIndex - 1, selectedShapes);
    }
  }

  private boolean isBackward(int index) {
    return index == 0;
  }

  private boolean isForward(int index) {
    return index == drawShapes.size() - 1;
  }
}
