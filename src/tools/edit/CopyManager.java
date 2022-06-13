package tools.edit;

import java.util.List;
import tools.draw.DrawShape;

public class CopyManager {

  private final List<DrawShape> drawShapes;
  private final List<DrawShape> temp;

  public CopyManager(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    this.temp = null;
  }

  public void copy(List<DrawShape> copyShapes) {
    copyShapes.forEach(copyShape -> temp.add(copyShape.clone()));
  }

  public void cut(List<DrawShape> copyShapes) {
    copyShapes.forEach(copyShape -> {
      temp.add(copyShape.clone());
      drawShapes.remove(copyShape);
    });
  }

  public void paste() {
    drawShapes.addAll(temp);
  }
}
