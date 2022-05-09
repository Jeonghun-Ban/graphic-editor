package tools;

import java.awt.Cursor;

public enum AnchorCursor {
  NW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
  NN(new Cursor(Cursor.N_RESIZE_CURSOR)),
  NE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
  EE(new Cursor(Cursor.E_RESIZE_CURSOR)),
  SE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
  SS(new Cursor(Cursor.S_RESIZE_CURSOR)),
  SW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
  WW(new Cursor(Cursor.W_RESIZE_CURSOR)),
  RR(new Cursor(Cursor.DEFAULT_CURSOR)),
  ;

  private final Cursor cursor;

  AnchorCursor(Cursor cursor) {
    this.cursor = cursor;
  }

  public Cursor getCursor() {
    return cursor;
  }
}
