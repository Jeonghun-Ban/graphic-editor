package views.dialogs;

import static global.Constants.FILL_COLOR_TITLE;
import static global.Constants.LINE_COLOR_TITLE;

import views.containers.DrawingPanel;
import java.awt.Color;
import javax.swing.JColorChooser;

public class ColorDialog {

  private DrawingPanel drawingPanel;
  private static ColorDialog colorDialog;

  private ColorDialog() {
    drawingPanel = DrawingPanel.getInstance();
  }

  public static ColorDialog getInstance() {
    if (colorDialog == null) {
      colorDialog = new ColorDialog();
    }
    return colorDialog;
  }

  public void setLineColor() {
    Color lineColor = JColorChooser.showDialog(null, LINE_COLOR_TITLE, null);
    if (lineColor != null) {
      drawingPanel.setLineColor(lineColor);
    }
  }

  public void setFillColor() {
    Color fillColor = JColorChooser.showDialog(null, FILL_COLOR_TITLE, null);
    if (fillColor != null) {
      drawingPanel.setFillColor(fillColor);
    }
  }
}
