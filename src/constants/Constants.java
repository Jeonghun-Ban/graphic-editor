package constants;

public class Constants {
    public static final String TITLE_MAINFRAME = "Graphic Editor";
    public static final int WIDTH_MAINFRAME = 600;
    public static final int HEIGHT_MAINFRAME = 400;

    public enum EditMenuEnum {
        Undo, Redo, Cut, Copy, Paste, Group, Ungroup
    };

    public enum FileMenuEnum {
        New, Open, Close, Save, SaveAs, Print, Quit
    };

    public enum ColorMenuEnum {
        SetLineColor, SetFillColor
    };
}
