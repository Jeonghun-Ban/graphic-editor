package tools.edit;

import java.util.List;
import java.util.Stack;
import tools.draw.DrawShape;

public class UndoManager {

  private final List<DrawShape> drawShapes;
  private final Stack<DrawShape> undoStack;

  public UndoManager(List<DrawShape> drawShapes) {
    this.drawShapes = drawShapes;
    this.undoStack = new Stack<>();
  }

  public void undo() {
    if (undoable()) {
      DrawShape undoShape = drawShapes.remove(drawShapes.size() - 1);
      undoStack.push(undoShape);
    }
  }

  public void redo() {
    if (redoable()) {
      DrawShape redoShape = undoStack.pop();
      drawShapes.add(redoShape);
    }
  }

  public void reset() {
    undoStack.removeAllElements();
  }

  private boolean undoable() {
    return !drawShapes.isEmpty();
  }

  private boolean redoable() {
    return !undoStack.empty();
  }
}
