package views.menus;

import static global.Constants.EDIT_MENU_TITLE;

import enums.EditMenuEnum;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static EditMenu editMenu;

  private EditMenu() {
    super(EDIT_MENU_TITLE);
    createMenuItems();
  }

  public static EditMenu getInstance() {
    if (editMenu == null) {
      editMenu = new EditMenu();
    }
    return editMenu;
  }

  private void createMenuItems() {
    Arrays.stream(EditMenuEnum.values()).forEach(value -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(value.getLabel());
      menuItem.setToolTipText(value.getLabel());
      this.add(menuItem);
    });
  }
}