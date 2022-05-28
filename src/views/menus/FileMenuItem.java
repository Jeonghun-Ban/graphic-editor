package views.menus;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import views.dialogs.FileDialog;

public enum FileMenuItem {
  New("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_DOWN_MASK),
      () -> FileDialog.getInstance().newFile()),
  Open("Open", KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_DOWN_MASK),
      () -> FileDialog.getInstance().openFile()),
  Save("Save", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK),
      () -> FileDialog.getInstance().saveFile()),
  SaveAs("Save As..",
      KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK | InputEvent.META_DOWN_MASK),
      () -> FileDialog.getInstance().saveFileAs()),
  Print("Print", KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.META_DOWN_MASK),
      (() -> FileDialog.getInstance().print())),
  Quit("Quit", KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_DOWN_MASK),
      (() -> FileDialog.getInstance().quit()));

  private final String label;
  private final KeyStroke keyStroke;
  private final Runnable operator;

  FileMenuItem(String label, KeyStroke keyStroke, Runnable operator) {
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
