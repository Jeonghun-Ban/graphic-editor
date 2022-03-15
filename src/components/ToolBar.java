package components;

import enums.DrawingTool;
import graphics.MetaShape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
    private ButtonGroup buttonGroup;
    private ToolBarHandler toolBarHandler;
    private DrawingPanel drawingPanel;

    public ToolBar () {
        buttonGroup = new ButtonGroup();
        toolBarHandler = new ToolBarHandler();

        Arrays.stream(DrawingTool.values()).forEach( value -> {
            JRadioButton radioBtn = new JRadioButton(value.name());
            this.add(radioBtn);
            buttonGroup.add(radioBtn);
            radioBtn.addActionListener(toolBarHandler);

        });
    }

    public void init(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    private class ToolBarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JRadioButton button = (JRadioButton)e.getSource();
                String packageName = "graphics.";
                Class<?> clazz = Class.forName(packageName.concat(button.getActionCommand()));
                MetaShape shape = (MetaShape) clazz.getConstructor().newInstance();
                drawingPanel.setCurrentShape(shape);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
