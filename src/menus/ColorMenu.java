package menus;

import components.DrawingPanel;
import global.Constants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ColorMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private DrawingPanel drawingPanel;

    public ColorMenu() {
        super("Color");
        ColorMenuHandler colorMenuHandler = new ColorMenuHandler();
        createMenuItems(colorMenuHandler);
    }

    private void createMenuItems(ColorMenuHandler colorMenuHandler){
        Arrays.stream(Constants.ColorMenuEnum.values()).forEach(value -> {
            String menuItemName = value.name();
            JMenuItem menuItem = new JMenuItem(menuItemName);

            menuItem.addActionListener(colorMenuHandler);
            menuItem.setActionCommand(menuItemName);

            this.add(menuItem);
        });
    }

    public void linkPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    private void setLineColor() {
        Color lineColor = JColorChooser.showDialog(
                null, Constants.LINE_COLOR_TITLE, null);
        if(lineColor != null){
            drawingPanel.setLineColor(lineColor);
        }
    }

    private void setFillColor() {
        Color fillColor = JColorChooser.showDialog(
                null, Constants.FILL_COLOR_TITLE, null);
        if(fillColor != null){
            drawingPanel.setFillColor(fillColor);
        }
    }

    private class ColorMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (Constants.ColorMenuEnum.valueOf(e.getActionCommand())){
                case SetLineColor:
                    setLineColor();
                    break;
                case SetFillColor:
                    setFillColor();
                    break;
            }
        }
    }
}
