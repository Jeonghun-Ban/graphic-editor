package enums;

public enum ColorMenuEnum {
  SetLineColor("Set Line Color"),
  SetFillColor("Set Fill Color");

  private final String label;

  ColorMenuEnum(String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }
}
