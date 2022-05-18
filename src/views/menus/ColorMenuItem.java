package views.menus;

import views.dialogs.ColorDialog;

public enum ColorMenuItem {
  SetLineColor("Set Line Color", (() -> ColorDialog.getInstance().setLineColor())),
  SetFillColor("Set Fill Color", (() -> ColorDialog.getInstance().setFillColor()));

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
