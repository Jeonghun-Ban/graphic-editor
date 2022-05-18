package views.menus;

public enum ColorMenuItem {
  SetLineColor("Set Line Color"),
  SetFillColor("Set Fill Color");

  private final String label;

  ColorMenuItem(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return this.label;
  }
}
