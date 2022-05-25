package utils;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class CursorManager {

  public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
  public static final Cursor CROSSHAIR_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);

  public static final Cursor ROTATE_CURSOR = createCustomCursor("rotate",
      "resources/static/image/cursor/rotate.png", 8);

  public static final Cursor MOVE_CURSOR = createCustomCursor("move",
      "resources/static/image/cursor/move.png", 16);

  private static Cursor createCustomCursor(String cursorName, String iconPath, int hotSpotSize) {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage(iconPath);
    return toolkit.createCustomCursor(image, new Point(hotSpotSize, hotSpotSize), cursorName);
  }
}
