package containers;

import static global.Constants.DASH_SIZE_MODEL;
import static global.Constants.DASH_SIZE_SPINNER;
import static global.Constants.LINE_SIZE_SPINNER;
import static global.Constants.LiNE_SIZE_MODEL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import state.DrawingTool;

public class ToolBar extends JToolBar {
	
private static final long serialVersionUID = 1L;

private final ButtonGroup buttonGroup;
  private final ToolBarHandler toolBarHandler;
  private final SpinnerHandler spinnerHandler;

  private DrawingPanel drawingPanel;

  public ToolBar() {
    buttonGroup = new ButtonGroup();
    toolBarHandler = new ToolBarHandler();
    spinnerHandler = new SpinnerHandler();

    createToolButtons();
    createLineSizeSpinner();
    createDashSizeSpinner();
  }

  public void associate(DrawingPanel drawingPanel) {
    this.drawingPanel = drawingPanel;
  }

  public void setDefaultButton() {
    JRadioButton defaultButton = (JRadioButton) this.getComponent(DrawingTool.Cursor.ordinal());
    defaultButton.doClick();
  }

  public void createToolButtons() {
    Arrays.stream(DrawingTool.values()).forEach(value -> {
      JRadioButton button = new JRadioButton();
      button.setActionCommand(value.name());
      button.setIcon(getIcon(value.name().toLowerCase(), false));
      button.setSelectedIcon(getIcon(value.name().toLowerCase(), true));
      this.add(button);
      buttonGroup.add(button);
      button.addActionListener(toolBarHandler);
    });
  }

  private ImageIcon getIcon(String iconName, boolean isPressed) {
    return isPressed ? new ImageIcon("src/image/pressed-button/".concat(iconName).concat(".png"))
        : new ImageIcon("src/image/general-button/".concat(iconName).concat(".png"));
  }

  public void createLineSizeSpinner() {
    this.add(new JLabel(LINE_SIZE_SPINNER));
    JSpinner lineSizeSpinner = new JSpinner(LiNE_SIZE_MODEL);
    this.add(lineSizeSpinner);
    lineSizeSpinner.addChangeListener(spinnerHandler);
  }

  public void createDashSizeSpinner() {
    this.add(new JLabel(DASH_SIZE_SPINNER));
    JSpinner dashSizeSpinner = new JSpinner(DASH_SIZE_MODEL);
    this.add(dashSizeSpinner);
    dashSizeSpinner.addChangeListener(spinnerHandler);
  }

  private class ToolBarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JRadioButton button = (JRadioButton) e.getSource();
      String command = button.getActionCommand();
      try {
        if (command.equals(DrawingTool.Clean.toString())) {
          drawingPanel.clean();
        } else {
          DrawingTool drawingTool = DrawingTool.valueOf(command);
          drawingPanel.setDrawTool(drawingTool.getDrawTool());
        }
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

