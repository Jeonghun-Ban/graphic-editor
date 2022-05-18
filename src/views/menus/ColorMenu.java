package views.menus;

import static global.Constants.COLOR_MENU_TITLE;

import views.dialogs.ColorDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ColorMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static ColorMenu colorMenu;
  private final ColorDialog colorDialog;

  private ColorMenu() {
    super(COLOR_MENU_TITLE);
    colorDialog = new ColorDialog();

    ActionHandler actionHandler = new ActionHandler();
    createMenuItems(actionHandler);
  }

  public static ColorMenu getInstance() {
    if (colorMenu == null) {
      colorMenu = new ColorMenu();
    }
    return colorMenu;
  }

  private void createMenuItems(ActionHandler actionHandler) {
    Arrays.stream(ColorMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(item.toString());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(item.toString());
      menuItem.setToolTipText(item.toString());
      this.add(menuItem);
    });
  }

  private class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals(ColorMenuItem.SetLineColor.toString())) {
        colorDialog.setLineColor();
      } else if (e.getActionCommand().equals(ColorMenuItem.SetFillColor.toString())) {
        colorDialog.setFillColor();
      }
    }
  }
}

