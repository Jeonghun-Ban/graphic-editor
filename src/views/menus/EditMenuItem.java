package views.menus;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum EditMenuItem {
  Undo("Undo", KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK),
      () -> {}),
  Redo("Redo", KeyStroke.getKeyStroke(KeyEvent.VK_Z,
      InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK), () -> {}),
  Cut("Cut", KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_DOWN_MASK),
      () -> {}),
  Copy("Copy", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK),
      () -> {}),
  Paste("Paste", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_DOWN_MASK),
      () -> {}),
  Group("Group", KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.META_DOWN_MASK),
      () -> {}),
  Ungroup("Ungroup", KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.META_DOWN_MASK),
      () -> {});

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
