package components;

import constants.Constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import shapes.MetaShape;

public class ToolBar extends JToolBar {
    private ButtonGroup buttonGroup;
    private ToolBarHandler toolBarHandler;
    private SpinnerHandler spinnerHandler;
    private DrawingPanel drawingPanel;
    private SpinnerModel lineSizeModel;
    private SpinnerModel dashSizeModel;

    public ToolBar () {
        buttonGroup = new ButtonGroup();
        toolBarHandler = new ToolBarHandler();
        spinnerHandler = new SpinnerHandler();

        createToolButtons();
        createSpinnerModel();
        createStyleSpinner();
    }

    public void createToolButtons(){
        Arrays.stream(Constants.DrawingTool.values()).forEach(value -> {
            JRadioButton radioBtn = new JRadioButton(value.name());
            this.add(radioBtn);
            buttonGroup.add(radioBtn);
            radioBtn.addActionListener(toolBarHandler);
        });
    }

    public void createSpinnerModel(){
        lineSizeModel = new SpinnerNumberModel(1, 1, 10, 1);
        dashSizeModel = new SpinnerNumberModel(0, 0, 10, 1);
    }

    public void createStyleSpinner(){
        this.add(new JLabel(Constants.LINE_SIZE_SPINNER));
        JSpinner lineSizeSpinner = new JSpinner(lineSizeModel);
        this.add(lineSizeSpinner);
        lineSizeSpinner.addChangeListener(spinnerHandler);

        this.add(new JLabel(Constants.DASH_SIZE_SPINNER));
        JSpinner dashSizeSpinner = new JSpinner(dashSizeModel);
        this.add(dashSizeSpinner);
        dashSizeSpinner.addChangeListener(spinnerHandler);
    }

    public void linkPanel(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    private class ToolBarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JRadioButton button = (JRadioButton)e.getSource();
                String buttonName = button.getActionCommand();
                String packageName = "shapes.";
                Class<?> clazz = Class.forName(packageName.concat(buttonName));
                MetaShape shape = (MetaShape) clazz.getConstructor().newInstance();
                drawingPanel.setCurrentShape(shape);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class SpinnerHandler implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner)e.getSource();
            SpinnerModel spinnerModel = spinner.getModel();
            int spinnerValue = (int)spinner.getValue();

            if (spinnerModel == lineSizeModel){
                drawingPanel.setLineSize(spinnerValue);
            } else if(spinnerModel == dashSizeModel){
                drawingPanel.setDashSize(spinnerValue);
            }
        }
    }
}
