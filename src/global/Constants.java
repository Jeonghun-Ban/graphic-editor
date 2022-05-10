package global;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Constants {

  // MainFrame
  public static final String TITLE_MAINFRAME = "Graphic Editor";
  public static final int WIDTH_MAINFRAME = 600;
  public static final int HEIGHT_MAINFRAME = 400;

  // ToolBar
  public static final String LINE_SIZE_SPINNER = "Line Size";
  public static final String DASH_SIZE_SPINNER = "Dash Size";
  public static final SpinnerModel LiNE_SIZE_MODEL = new SpinnerNumberModel(1, 1, 10, 1);
  public static final SpinnerModel DASH_SIZE_MODEL = new SpinnerNumberModel(0, 0, 10, 1);
  public static final Color DEFAULT_BACKGROUND_COLOR = Color.white;
  public static final Color DEFAULT_LINE_COLOR = Color.black;
  public static final Color DEFAULT_FILL_COLOR = Color.white;
  public static final String LINE_COLOR_TITLE = "Line Color Chooser";
  public static final String FILL_COLOR_TITLE = "Fill Color Chooser";
  public static final int DEFAULT_LINE_SIZE = 1;
  public static final int DEFAULT_DASH_SIZE = 0;

  // Menu
  public static final String FILE_MENU_TITLE = "File";
  public static final String EDIT_MENU_TITLE = "Edit";
  public static final String COLOR_MENU_TITLE = "Color";

  // Cursor
  public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
  public static final Cursor CROSSHAIR_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
  public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

  // Anchor
  public static final int ANCHOR_WIDTH = 10;
  public static final int ANCHOR_HEIGHT = 10;
  public static final int ANCHOR_RADIUS = 5;
  public static final int ROTATE_ANCHOR_HEIGHT = 30;
  public static final BasicStroke ANCHOR_STROKE = new BasicStroke();
}
