package menus;

import static global.Constants.COLOR_MENU_TITLE;

import containers.DrawingPanel;
import dialogs.ColorDialog;
import enums.ColorMenuEnum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ColorMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private final ColorDialog colorDialog;

  public ColorMenu() {
    super(COLOR_MENU_TITLE);
    colorDialog = new ColorDialog();

    ActionHandler actionHandler = new ActionHandler();
    createMenuItems(actionHandler);
  }

  private void createMenuItems(ActionHandler actionHandler) {
    Arrays.stream(ColorMenuEnum.values()).forEach(value -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(value.getLabel());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(value.getLabel());
      menuItem.setToolTipText(value.getLabel());
      this.add(menuItem);
    });
  }

  public void associate(DrawingPanel drawingPanel) {
    this.colorDialog.associate(drawingPanel);
  }

  private class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(ColorMenuEnum.SetLineColor.getLabel())) {
        colorDialog.setLineColor();
      } else if (e.getActionCommand().equals(ColorMenuEnum.SetLineColor.getLabel())) {
        colorDialog.setFillColor();
      }
    }
  }
}

