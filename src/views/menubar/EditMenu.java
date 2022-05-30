package views.menubar;

import static global.Constants.EDIT_MENU_TITLE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import views.menubar.item.EditMenuItem;

public class EditMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static EditMenu editMenu;

  private EditMenu() {
    super(EDIT_MENU_TITLE);
    ActionHandler actionHandler = new ActionHandler();
    createMenuItems(actionHandler);
  }

  public static EditMenu getInstance() {
    if (editMenu == null) {
      editMenu = new EditMenu();
    }
    return editMenu;
  }

  private void createMenuItems(ActionHandler actionHandler) {
    Arrays.stream(EditMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem();
      menuItem.setText(item.toString());
      menuItem.addActionListener(actionHandler);
      menuItem.setActionCommand(item.name());
      menuItem.setToolTipText(item.toString());
      menuItem.setAccelerator(item.getKeyStroke());
      this.add(menuItem);
    });
  }

  private static class ActionHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Optional<EditMenuItem> editMenuItem = Arrays.stream(EditMenuItem.values())
          .filter(item -> e.getActionCommand().equals(item.name())).findFirst();
      editMenuItem.ifPresent(EditMenuItem::operate);
    }
  }
}