package views.menus;

public enum EditMenuEnum {
  Undo("Undo"),
  Redo("Redo"),
  Cut("Cut"),
  Copy("Copy"),
  Paste("Paste"),
  Group("Group"),
  Ungroup("Ungroup");

  private final String label;

  EditMenuEnum(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return this.label;
  }
}
