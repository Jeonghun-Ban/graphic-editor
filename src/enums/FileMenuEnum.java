package enums;

public enum FileMenuEnum {
    New("New"),
    Open("Open"),
    Save("Save"),
    SaveAs("Save As.."),
    Print("Print"),
    Quit("Quit");

    private String label;

    FileMenuEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
