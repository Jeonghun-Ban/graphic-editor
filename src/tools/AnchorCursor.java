package tools;

import java.awt.Cursor;
import utils.CursorManager;

public enum AnchorCursor {
  NW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
  N(new Cursor(Cursor.N_RESIZE_CURSOR)),
  NE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
  E(new Cursor(Cursor.E_RESIZE_CURSOR)),
  SE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
  S(new Cursor(Cursor.S_RESIZE_CURSOR)),
  SW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
  W(new Cursor(Cursor.W_RESIZE_CURSOR)),
  Rotate(CursorManager.ROTATE_CURSOR),
  ;

  private final Cursor cursor;

  AnchorCursor(Cursor cursor) {
    this.cursor = cursor;
  }

  public Cursor getCursor() {
    return cursor;
  }
}
