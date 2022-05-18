package views.menus;

public enum ColorMenuEnum {
  SetLineColor("Set Line Color"),
  SetFillColor("Set Fill Color");

  private final String label;

  ColorMenuEnum(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return this.label;
  }
}
