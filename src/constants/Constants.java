package constants;

import java.awt.Color;

public class Constants {
    // MainFrame
    public static final String TITLE_MAINFRAME = "Graphic Editor";
    public static final int WIDTH_MAINFRAME = 600;
    public static final int HEIGHT_MAINFRAME = 400;

    // MenuBar
    public enum EditMenuEnum {Undo, Redo, Cut, Copy, Paste, Group, Ungroup}
    public enum FileMenuEnum {New, Open, Close, Save, SaveAs, Print, Quit}
    public enum ColorMenuEnum {SetLineColor, SetFillColor}

    // ToolBar
    public static final String LINE_SIZE_SPINNER = "Line Size";
    public static final String DASH_SIZE_SPINNER = "Dash Size";

    // DrawingPanel
    public enum DrawingMode {IDLE, GENERAL, POLYGON}

    public static final Color DEFAULT_BACKGROUND_COLOR = Color.white;
    public static final Color DEFAULT_LINE_COLOR = Color.black;
    public static final Color DEFAULT_FILL_COLOR = Color.white;

    public static final String LINE_COLOR_TITLE = "Line Color Chooser";
    public static final String FILL_COLOR_TITLE = "Fill Color Chooser";

    public static final int DEFAULT_LINE_SIZE = 1;
    public static final int DEFAULT_DASH_SIZE = 0;
}
