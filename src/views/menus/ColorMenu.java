package views.menus;

import static global.Constants.COLOR_MENU_TITLE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ColorMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static ColorMenu colorMenu;

  private ColorMenu() {
    super(COLOR_MENU_TITLE);

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
      menuItem.setActionCommand(item.name());
      menuItem.setToolTipText(item.toString());
      this.add(menuItem);
    });
  }

  private class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Optional<ColorMenuItem> colorMenuItem = Arrays.stream(ColorMenuItem.values())
          .filter(item -> e.getActionCommand().equals(item.name())).findFirst();
      colorMenuItem.ifPresent(item -> item.operate());
    }
  }
}

