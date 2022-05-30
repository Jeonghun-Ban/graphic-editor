package views.dialogs;

import static global.Constants.FILL_COLOR_TITLE;
import static global.Constants.LINE_COLOR_TITLE;

import java.awt.Color;
import javax.swing.JColorChooser;
import views.containers.DrawingPanel;

public class ColorDialog {

  private final DrawingPanel drawingPanel;

  private static ColorDialog colorDialog;
  private Color lineColor;
  private Color fillColor;

  private ColorDialog() {
    drawingPanel = DrawingPanel.getInstance();
    lineColor = null;
    fillColor = null;
  }

  public static ColorDialog getInstance() {
    if (colorDialog == null) {
      colorDialog = new ColorDialog();
    }
    return colorDialog;
  }

  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
  }

  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  public void updateLineColor() {
    lineColor = JColorChooser.showDialog(null, LINE_COLOR_TITLE, lineColor);
    if (lineColor != null) {
      drawingPanel.setLineColor(lineColor);
    }
  }

  public void updateFillColor() {
    fillColor = JColorChooser.showDialog(null, FILL_COLOR_TITLE, fillColor);
    if (fillColor != null) {
      drawingPanel.setFillColor(fillColor);
    }
  }
}
