package views.menus;

public enum FileMenuEnum {
  New("New"),
  Open("Open"),
  Save("Save"),
  SaveAs("Save As.."),
  Print("Print"),
  Quit("Quit");

  private final String label;

  FileMenuEnum(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return this.label;
  }
}
