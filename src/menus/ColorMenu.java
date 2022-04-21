package menus;

import containers.DrawingPanel;
import enums.ColorMenuEnum;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import static global.Constants.FILL_COLOR_TITLE;
import static global.Constants.LINE_COLOR_TITLE;

public class ColorMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private DrawingPanel drawingPanel;

    public ColorMenu() {
        super("Color");
        ColorMenuHandler colorMenuHandler = new ColorMenuHandler();
        createMenuItems(colorMenuHandler);
    }

    private void createMenuItems(ColorMenuHandler colorMenuHandler){
        Arrays.stream(ColorMenuEnum.values()).forEach(value -> {
            String menuItemName = value.name();
            JMenuItem menuItem = new JMenuItem(menuItemName);

            menuItem.addActionListener(colorMenuHandler);
            menuItem.setActionCommand(menuItemName);

            this.add(menuItem);
        });
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    private void setLineColor() {
        Color lineColor = JColorChooser.showDialog(
                null, LINE_COLOR_TITLE, null);
        if(lineColor != null){
            drawingPanel.setLineColor(lineColor);
        }
    }

    private void setFillColor() {
        Color fillColor = JColorChooser.showDialog(
                null, FILL_COLOR_TITLE, null);
        if(fillColor != null){
            drawingPanel.setFillColor(fillColor);
        }
    }

    private class ColorMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (ColorMenuEnum.valueOf(e.getActionCommand())){
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
