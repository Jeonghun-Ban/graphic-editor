package tools.edit;

import java.util.List;
import tools.draw.DrawShape;

public class CopyManager {

  private List<DrawShape> drawShapes;
  private DrawShape temp;

  public CopyManager(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    this.temp = null;
  }

  public void copy(DrawShape copyShape) {
    this.temp = copyShape.clone();
  }

  public void cut(DrawShape copyShape) {
    this.temp = copyShape;
    drawShapes.remove(copyShape);
  }

  public void paste() {
    drawShapes.add(temp);
  }
}
