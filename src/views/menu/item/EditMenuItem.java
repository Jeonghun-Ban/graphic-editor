package views.menu.item;

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
  Ungroup("Ungroup", KeyStroke.getKeyStroke(KeyEvent.VK_G,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().unGroup()),
  BringForward("Bring forward", KeyStroke.getKeyStroke(KeyEvent.VK_F,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().bringForward()),
  SendBackward("Send backward", KeyStroke.getKeyStroke(KeyEvent.VK_B,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().sendBackward()),
  BringToFront("Bring to front", KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().bringToFront()),
  SendToBack("Send to back", KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().sendToBack());

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
