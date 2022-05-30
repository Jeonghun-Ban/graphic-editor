package views.toolbar;

import static global.Constants.ICON_RESOURCE_FORMAT;
import static global.Constants.ICON_RESOURCE_GENERAL_PATH;
import static global.Constants.ICON_RESOURCE_ROOT_PATH;

import javax.swing.ImageIcon;
import views.containers.DrawingPanel;
import views.dialogs.ColorDialog;

public enum EditTool {
  Remove("remove", () -> DrawingPanel.getInstance().remove()),
  LineColor("line_color", () -> ColorDialog.getInstance().updateLineColor()),
  FillColor("fill_color", () -> ColorDialog.getInstance().updateFillColor()),
  ;

  private final String fileName;
  private final Runnable operator;

  EditTool(String fileName, Runnable operator) {
    this.fileName = fileName;
    this.operator = operator;
  }

  public ImageIcon getIcon() {
    return new ImageIcon(ICON_RESOURCE_ROOT_PATH.concat(ICON_RESOURCE_GENERAL_PATH)
        .concat(getFileName().toLowerCase())
        .concat(ICON_RESOURCE_FORMAT));
  }

  public String getFileName() {
    return this.fileName;
  }

  public void operate() {
    this.operator.run();
  }
}
