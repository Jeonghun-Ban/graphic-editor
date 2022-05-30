package views.toolbar;

import static global.Constants.DASH_SIZE_MODEL;
import static global.Constants.DASH_SIZE_SPINNER;
import static global.Constants.LINE_SIZE_SPINNER;
import static global.Constants.LiNE_SIZE_MODEL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import views.containers.DrawingPanel;

public class ToolBar extends JToolBar {

  private static final long serialVersionUID = 1L;

  private static final JSpinner lineSizeSpinner = new JSpinner(LiNE_SIZE_MODEL);
  private static final JSpinner dashSizeSpinner = new JSpinner(DASH_SIZE_MODEL);

  private final ButtonGroup buttonGroup;
  private final DrawToolHandler drawToolHandler;
  private final SpinnerHandler spinnerHandler;

  private final DrawingPanel drawingPanel;

  private static ToolBar toolBar;

  private ToolBar() {
    buttonGroup = new ButtonGroup();
    drawToolHandler = new DrawToolHandler();
    spinnerHandler = new SpinnerHandler();

    createDrawTool();

    createSpinner(LINE_SIZE_SPINNER, lineSizeSpinner);
    createSpinner(DASH_SIZE_SPINNER, dashSizeSpinner);

    drawingPanel = DrawingPanel.getInstance();
  }

  public static ToolBar getInstance() {
    if (toolBar == null) {
      toolBar = new ToolBar();
    }
    return toolBar;
  }

  public void setLineSize(int lineSize) {
    lineSizeSpinner.setValue(lineSize);
  }

  public void setDashSize(int dashSize) {
    dashSizeSpinner.setValue(dashSize);
  }

  public void setDefaultButton() {
    JRadioButton defaultButton = (JRadioButton) this.getComponent(DrawTool.Selection.ordinal());
    defaultButton.doClick();
  }

  public void createDrawTool() {
    Arrays.stream(DrawTool.values()).forEach(item -> {
      JRadioButton button = new JRadioButton();
      button.setActionCommand(item.name());
      button.setIcon(item.getIcon(false));
      button.setSelectedIcon(item.getIcon(true));
      this.add(button);
      buttonGroup.add(button);
      button.addActionListener(drawToolHandler);
    });
  }

  public void createSpinner(String label, JSpinner spinner) {
    this.add(new JLabel(label));
    this.add(spinner);
    spinner.addChangeListener(spinnerHandler);
  }

  private class DrawToolHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JRadioButton button = (JRadioButton) e.getSource();
      String command = button.getActionCommand();
      try {
        DrawTool drawTool = DrawTool.valueOf(command);
        drawingPanel.setShapeClass(drawTool.getShapeClass());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  private class SpinnerHandler implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
      JSpinner spinner = (JSpinner) e.getSource();
      SpinnerModel spinnerModel = spinner.getModel();
      int spinnerValue = (int) spinner.getValue();

      if (spinnerModel == LiNE_SIZE_MODEL) {
        drawingPanel.setLineSize(spinnerValue);
      } else if (spinnerModel == DASH_SIZE_MODEL) {
        drawingPanel.setDashSize(spinnerValue);
      }
    }
  }
}

