package views.menu;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.util.Arrays;
import views.menu.EditMenu.ActionHandler;
import views.menu.item.EditMenuItem;

public class PopMenu extends PopupMenu {

  private static PopMenu popMenu;

  public static PopMenu getInstance() {
    if (popMenu == null) {
      popMenu = new PopMenu();
    }
    return popMenu;
  }

  private PopMenu() {
    super();
    ActionHandler actionHandler = new ActionHandler();
    createMenuItems(actionHandler);
  }

  private void createMenuItems(ActionHandler actionHandler) {
    Arrays.stream(EditMenuItem.values()).forEach(item -> {
      MenuItem menuItem = new MenuItem(item.toString());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(item.name());
      this.add(menuItem);
    });
  }
}
