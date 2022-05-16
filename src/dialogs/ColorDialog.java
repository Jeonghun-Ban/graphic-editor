package dialogs;

import static global.Constants.FILL_COLOR_TITLE;
import static global.Constants.LINE_COLOR_TITLE;

import containers.DrawingPanel;
import java.awt.Color;
import javax.swing.JColorChooser;

public class ColorDialog {

  private DrawingPanel drawingPanel;

  public ColorDialog() {
    drawingPanel = DrawingPanel.getInstance();
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
