package views.menus;

public enum EditMenuItem {
  Undo("Undo"),
  Redo("Redo"),
  Cut("Cut"),
  Copy("Copy"),
  Paste("Paste"),
  Group("Group"),
  Ungroup("Ungroup");

  private final String label;

  EditMenuItem(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return this.label;
  }
}
