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
import enums.DrawTool;

public class ToolBar extends JToolBar {

  private static final long serialVersionUID = 1L;

  private static final JSpinner lineSizeSpinner = new JSpinner(LiNE_SIZE_MODEL);
  private static final JSpinner dashSizeSpinner = new JSpinner(DASH_SIZE_MODEL);

  private final ButtonGroup buttonGroup;
  private final ToolBarHandler toolBarHandler;
  private final SpinnerHandler spinnerHandler;

  private DrawingPanel drawingPanel;

  public ToolBar() {
    buttonGroup = new ButtonGroup();
    toolBarHandler = new ToolBarHandler();
    spinnerHandler = new SpinnerHandler();

    createToolButtons();
    createSpinner(LINE_SIZE_SPINNER, lineSizeSpinner);
    createSpinner(DASH_SIZE_SPINNER, dashSizeSpinner);
  }

  public void associate(DrawingPanel drawingPanel) {
    this.drawingPanel = drawingPanel;
  }

  public void setDefaultButton() {
    JRadioButton defaultButton = (JRadioButton) this.getComponent(DrawTool.Selection.ordinal());
    defaultButton.doClick();
  }

  public void createToolButtons() {
    Arrays.stream(DrawTool.values()).forEach(value -> {
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

  public void createSpinner(String label, JSpinner spinner) {
    this.add(new JLabel(label));
    this.add(spinner);
    spinner.addChangeListener(spinnerHandler);
  }

  private class ToolBarHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JRadioButton button = (JRadioButton) e.getSource();
      String command = button.getActionCommand();
      try {
        if (command.equals(DrawTool.Remove.toString())) {
          drawingPanel.remove();
        } else {
          DrawTool drawTool = DrawTool.valueOf(command);
          drawingPanel.setCurrentShape(drawTool.getDrawShape());
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

