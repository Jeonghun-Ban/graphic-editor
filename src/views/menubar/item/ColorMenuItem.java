package views.menubar.item;

import views.dialogs.ColorDialog;

public enum ColorMenuItem {
  SetLineColor("Set Line Color", (() -> ColorDialog.getInstance().updateLineColor())),
  SetFillColor("Set Fill Color", (() -> ColorDialog.getInstance().updateFillColor()));

  private final String label;
  private final Runnable operator;

  ColorMenuItem(String label, Runnable operator) {
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
