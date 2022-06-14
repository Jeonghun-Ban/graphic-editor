package views.menu.item;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import views.containers.DrawingPanel;

public enum EditMenuItem {
  Undo("Undo", KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().undo(), false),
  Redo("Redo", KeyStroke.getKeyStroke(KeyEvent.VK_Z,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().redo(), true),
  Cut("Cut", KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().cut(), false),
  Copy("Copy", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().copy(), false),
  Paste("Paste", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().paste(), true),
  Group("Group", KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().group(), false),
  Ungroup("Ungroup", KeyStroke.getKeyStroke(KeyEvent.VK_G,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().unGroup(), true),
  BringToFront("Bring to front", KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().bringToFront(), false),
  BringForward("Bring forward", KeyStroke.getKeyStroke(KeyEvent.VK_F,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().bringForward(), false),
  SendToBack("Send to back", KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().sendToBack(), false),
  SendBackward("Send backward", KeyStroke.getKeyStroke(KeyEvent.VK_B,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> DrawingPanel.getInstance().sendBackward(), false);

  private final String label;
  private final KeyStroke keyStroke;
  private final Runnable operator;
  private final boolean separator;

  EditMenuItem(String label, KeyStroke keyStroke, Runnable operator, boolean separator) {
    this.label = label;
    this.keyStroke = keyStroke;
    this.operator = operator;
    this.separator = separator;
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

  public boolean isSeparator() {
    return this.separator;
  }
}
