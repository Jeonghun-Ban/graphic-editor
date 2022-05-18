package views.menus;

import views.dialogs.FileDialog;

public enum FileMenuItem {
  New("New", (() -> FileDialog.getInstance().newFile())),
  Open("Open", (() -> FileDialog.getInstance().openFile())),
  Save("Save", (() -> FileDialog.getInstance().saveFile())),
  SaveAs("Save As..", (() -> FileDialog.getInstance().saveFileAs())),
  Print("Print", (() -> FileDialog.getInstance().print())),
  Quit("Quit", (() -> FileDialog.getInstance().quit()));

  private final String label;
  private final Runnable operator;

  FileMenuItem(String label, Runnable operator) {
    this.label = label;
    this.operator = operator;
  }

  @Override
  public String toString() {
    return this.label;
  }

  public void operate() {
    this.operator.run();
  }
}
