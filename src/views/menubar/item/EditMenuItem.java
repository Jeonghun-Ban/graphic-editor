package views.menubar.item;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import views.containers.DrawingPanel;

public enum EditMenuItem {
  Undo("Undo", KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().undo()),
  Redo("Redo", KeyStroke.getKeyStroke(KeyEvent.VK_Z,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().redo()),
  Cut("Cut", KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().cut()),
  Copy("Copy", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().copy()),
  Paste("Paste", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().paste()),
  Group("Group", KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().group()),
  Ungroup("Ungroup", KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().unGroup());

  private final String label;
  private final KeyStroke keyStroke;
  private final Runnable operator;

  EditMenuItem(String label, KeyStroke keyStroke, Runnable operator) {
    this.label = label;
    this.keyStroke = keyStroke;
    this.operator = operator;
  }

  @Override
  public String toString() {
    return this.label;
  }

  public KeyStroke getKeyStroke() {
    return keyStroke;
  }

  public void operate() {
    this.operator.run();
  }
}
